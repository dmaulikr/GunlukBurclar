package org.uusoftware.burclar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
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
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
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
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_header);
        collapsingToolbarLayout.setTitle("Çin astrolojisi");
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Dynamic bar colors
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.Appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    coloredBars(ContextCompat.getColor(mContext, R.color.colorCinDark), ContextCompat.getColor(mContext, R.color.colorCinPrimary));
                } else if (verticalOffset == 0) {
                    coloredBars(Color.TRANSPARENT, Color.TRANSPARENT);
                } else {
                    coloredBars(Color.argb(255 - verticalOffset / 2, 56, 142, 60), Color.argb(255 - verticalOffset / 2, 56, 142, 60));
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

        ImageView image = (ImageView) findViewById(R.id.cin_header);
        WebView myWebView = (WebView) findViewById(R.id.webViewGeneral);

        System.out.println(burc);

        if (burc.contains("Fare")) {
            image.setImageResource(R.drawable.cin_fare);
            link = "http://uusoftware.org/burclar/cin-fare.html";
        } else if (burc.contains("Öküz")) {
            image.setImageResource(R.drawable.cin_okuz);
            link = "http://uusoftware.org/burclar/cin-okuz.html";
        } else if (burc.contains("Kaplan")) {
            image.setImageResource(R.drawable.cin_kaplan);
            link = "http://uusoftware.org/burclar/cin-kaplan.html";
        } else if (burc.contains("Tavşan")) {
            image.setImageResource(R.drawable.cin_tavsan);
            link = "http://uusoftware.org/burclar/cin-tavsan.html";
        } else if (burc.contains("Ejderha")) {
            image.setImageResource(R.drawable.cin_ejdarha);
            link = "http://uusoftware.org/burclar/cin-ejderha.html";
        } else if (burc.contains("Yılan")) {
            image.setImageResource(R.drawable.cin_yilan);
            link = "http://uusoftware.org/burclar/cin-yilan.html";
        } else if (burc.contains("At")) {
            image.setImageResource(R.drawable.cin_at);
            link = "http://uusoftware.org/burclar/cin-at.html";
        } else if (burc.contains("Keçi")) {
            image.setImageResource(R.drawable.cin_keci);
            link = "http://uusoftware.org/burclar/cin-keci.html";
        } else if (burc.contains("Maymun")) {
            image.setImageResource(R.drawable.cin_maymun);
            link = "http://uusoftware.org/burclar/cin-maymun.html";
        } else if (burc.contains("Horoz")) {
            image.setImageResource(R.drawable.cin_horoz);
            link = "http://uusoftware.org/burclar/cin-horoz.html";
        } else if (burc.contains("Köpek")) {
            image.setImageResource(R.drawable.cin_kopek);
            link = "http://uusoftware.org/burclar/cin-kopek.html";
        } else if (burc.contains("Domuz")) {
            image.setImageResource(R.drawable.cin_domuz);
            link = "http://uusoftware.org/burclar/cin-domuz.html";
        } else {
            image.setImageResource(R.drawable.cin_unknown);
            link = "http://uusoftware.org/burclar/cin-noburc.html";
        }

        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.loadUrl(link);

        //Floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyStoragePermissions();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                verifyStoragePermissions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void coloredBars(int color1, int color2) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color1);
            toolbar.setBackgroundDrawable(new ColorDrawable(color2));
        } else {
            toolbar.setBackgroundDrawable(new ColorDrawable(color2));
        }
    }

    public void verifyStoragePermissions() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            } else {
                MainActivity.createFolder();
                saveBitmap();
            }
        } else {
            saveBitmap();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
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

    public void saveBitmap() {
        CharSequence now = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm", new Date());
        String filePath = now + ".png";

        try {
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar", filePath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

            shareIt(Environment.getExternalStorageDirectory() + "/Günlük Burçlar" + "/" + filePath);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    public void shareIt(String path) {
        // Share
        Uri myUri = Uri.parse("file://" + path);
        String shareBody;
        if (burc.contains("Burcunuzu bulamadık?")) {
            shareBody = "Çin burcumu aradım ama bulamadım :( Ya sen? https://play.google.com/store/apps/details?id=org.uusoftware.burclar";
        } else {
            shareBody = "Çin burcum" + burc + " çıktı. Seninki? https://play.google.com/store/apps/details?id=org.uusoftware.burclar";
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(intent, "Paylaş..."));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}