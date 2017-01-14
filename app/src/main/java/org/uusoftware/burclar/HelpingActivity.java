package org.uusoftware.burclar;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class HelpingActivity extends AppCompatActivity {

    Window window;
    ActionBar bar;
    Tracker t;

    //Facebook Audience Network
    private com.facebook.ads.AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helping);

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
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAboutDark));

            bar = this.getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorAboutPrimary)));
        } else {
            bar = this.getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorAboutPrimary)));
        }

        // Analytics
        t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Yardım");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}