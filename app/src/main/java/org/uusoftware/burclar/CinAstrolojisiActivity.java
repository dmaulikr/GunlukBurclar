package org.uusoftware.burclar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class CinAstrolojisiActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    Window window;
    Toolbar toolbar;
    Context mContext;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String burc, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinastrolojisi);

        /* Colored bars */
        mContext = this.getApplicationContext();

        //StatusBar
        window = this.getWindow();

        //Collapsing Toolbar
        collapsingToolbarLayout = findViewById(R.id.collapsing_header);
        collapsingToolbarLayout.setTitle("Çin astrolojisi");
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Dynamic bar colors
        AppBarLayout appBarLayout = findViewById(R.id.Appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    coloredBars(ContextCompat.getColor(mContext, R.color.colorCinDark), ContextCompat.getColor(mContext, R.color.colorCinPrimary));
                } else if (verticalOffset == 0) {
                    coloredBars(Color.TRANSPARENT, Color.TRANSPARENT);
                } else {
                    coloredBars(Color.argb(255 - verticalOffset / 2, 56, 142, 60), Color.argb(255 - verticalOffset / 2, 76, 175, 80));
                }
            }
        });

        // Analytics
        Tracker t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Çin astrolojisi - Sonuç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        Bundle extras = getIntent().getExtras();
        burc = extras.getString("burc");

        ImageView image = findViewById(R.id.cin_header);
        WebView myWebView = findViewById(R.id.webViewGeneral);

        link = "file:///android_asset/cinastrolojisi/" + burc + ".html";

        if (burc.contains("fare")) {
            image.setImageResource(R.drawable.cin_fare);
        } else if (burc.contains("okuz")) {
            image.setImageResource(R.drawable.cin_okuz);
        } else if (burc.contains("kaplan")) {
            image.setImageResource(R.drawable.cin_kaplan);
        } else if (burc.contains("tavsan")) {
            image.setImageResource(R.drawable.cin_tavsan);
        } else if (burc.contains("ejderha")) {
            image.setImageResource(R.drawable.cin_ejdarha);
        } else if (burc.contains("yilan")) {
            image.setImageResource(R.drawable.cin_yilan);
        } else if (burc.contains("at")) {
            image.setImageResource(R.drawable.cin_at);
        } else if (burc.contains("keci")) {
            image.setImageResource(R.drawable.cin_keci);
        } else if (burc.contains("maymun")) {
            image.setImageResource(R.drawable.cin_maymun);
        } else if (burc.contains("horoz")) {
            image.setImageResource(R.drawable.cin_horoz);
        } else if (burc.contains("kopek")) {
            image.setImageResource(R.drawable.cin_kopek);
        } else if (burc.contains("domuz")) {
            image.setImageResource(R.drawable.cin_domuz);
        } else {
            image.setImageResource(R.drawable.cin_unknown);
        }

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(link);

        //Floating action button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyStoragePermissions("share");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_yorum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_fav:
                verifyStoragePermissions("addfavorite");
                return true;
            case R.id.action_share:
                verifyStoragePermissions("share");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void verifyStoragePermissions(String operation) {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                MainActivity.createFolder();
                saveBitmap(operation);
            }
        } else {
            MainActivity.createFolder();
            saveBitmap(operation);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Ayarlarınız kaydedildi...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Bir hata oluştu! Lütfen daha sonra tekrar deneyiniz...", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void saveBitmap(String operation) {
        CharSequence now = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm", new Date());
        String fileName = now + ".jpg";

        try {
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile;
            if (operation.contains("addfavorite")) {
                imageFile = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar/Favoriler", fileName);
            } else {
                imageFile = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar/Paylaşılanlar", fileName);
            }

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            outputStream.flush();
            outputStream.close();

            if (operation.contains("share")) {
                shareIt(imageFile);
            } else {
                Toast.makeText(this, "Favorilerinize eklendi...", Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    public void shareIt(File newFile) {
        Uri myUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", newFile);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(intent, "Paylaş..."));
    }

    public void coloredBars(int color1, int color2) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color1);
            toolbar.setBackgroundColor(color2);
        } else {
            toolbar.setBackgroundColor(color2);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}