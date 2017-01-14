package org.uusoftware.burclar;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.uusoftware.burclar.adapter.GridAdapter;
import org.uusoftware.burclar.model.GridItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    GridLayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    Tracker t;
    int color = Color.parseColor("#424242");
    int color2 = Color.parseColor("#757575");
    Window window;
    ActionBar bar;
    GridItem item;
    BitmapFactory.Options options;
    private List<GridItem> feedsList;

    //Facebook Audience Network
    private com.facebook.ads.AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Premium & Facebook Audience Network
        boolean premium = MainActivity.premium;
        if (premium) {
            //Do nothing
        } else {
            RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adFacebook);
            adView = new com.facebook.ads.AdView(this, "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("90ff5bfeac54391d98cc2bb9ff05ebb7");
            adViewContainer.addView(adView);
            adView.loadAd();
        }

        // Colored bars
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);

            bar = this.getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color2));
        } else {
            bar = this.getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color2));
        }

        // Analytics
        t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Favoriler");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        feedsList = new ArrayList<>();

        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;
        options.inPreferredConfig = Config.RGB_565;
        options.inDither = true;

        String path = Environment.getExternalStorageDirectory().toString() + "/Günlük Burçlar";
        File f = new File(path);
        File file[] = f.listFiles();
        if (file == null || file.length == 0) {
            Toast.makeText(this, "Henüz favorilerinize hiç yorum eklememişsiniz.", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < file.length; i++) {
                item = new GridItem();
                item.setThumbnail(BitmapFactory.decodeFile(file[i].getAbsolutePath(), options));
                item.setTitle(file[i].getName());
                feedsList.add(item);
            }
        }

        // Adapter
        mAdapter = new GridAdapter(this, feedsList);
        mRecyclerView.setAdapter(mAdapter);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(this, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(final int position) {
                if ((position % 3) == 0) {
                    return (2);
                } else {
                    return (1);
                }
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}