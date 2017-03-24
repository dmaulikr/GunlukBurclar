package org.uusoftware.burclar;

import android.Manifest;
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

public class YukselenBurcActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    Window window;
    Toolbar toolbar;

    CollapsingToolbarLayout collapsingToolbarLayout;
    String burcName;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yukselen);

        //StatusBar
        window = this.getWindow();

        //Collapsing Toolbar
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_header);
        collapsingToolbarLayout.setTitle("Yükselen burç");
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
                    coloredBars(ContextCompat.getColor(YukselenBurcActivity.this, R.color.colorYukselenDark), ContextCompat.getColor(YukselenBurcActivity.this, R.color.colorYukselenPrimary));
                } else if (verticalOffset == 0) {
                    coloredBars(Color.TRANSPARENT, Color.TRANSPARENT);
                } else {
                    coloredBars(Color.argb(255 - verticalOffset / 2, 255, 87, 34), Color.argb(255 - verticalOffset / 2, 63, 81, 181));
                }
            }
        });

        // Analytics
        Tracker t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Yükselen burç - Sonuç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        //Get Yükselen Burç
        Bundle extras = getIntent().getExtras();
        String selectedburc = extras.getString("burcid");

        ImageView image = (ImageView) findViewById(R.id.burc_header);
        WebView myWebView = (WebView) findViewById(R.id.webViewGeneral);
        link = "http://uusoftware.org/gunlukburclar/burcyorumlari/" + selectedburc + ".html";

        if (selectedburc.contains("koc")) {
            image.setImageResource(R.drawable.burc_koc);
            burcName = "Koç";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#341A47"));
        } else if (selectedburc.contains("boga")) {
            image.setImageResource(R.drawable.burc_boga);
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#3C224F"));
            burcName = "Boğa";
        } else if (selectedburc.contains("ikizler")) {
            image.setImageResource(R.drawable.burc_ikizler);
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#442B55"));
            burcName = "İkizler";
        } else if (selectedburc.contains("yengec")) {
            image.setImageResource(R.drawable.burc_yengec);
            burcName = "Yengeç";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#6A3667"));
        } else if (selectedburc.contains("aslan")) {
            image.setImageResource(R.drawable.burc_aslan);
            burcName = "Aslan";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#5F3A6E"));
        } else if (selectedburc.contains("basak")) {
            image.setImageResource(R.drawable.burc_basak);
            burcName = "Başak";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#614379"));
        } else if (selectedburc.contains("terazi")) {
            image.setImageResource(R.drawable.burc_terazi);
            burcName = "Terazi";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#7C3E75"));
        } else if (selectedburc.contains("akrep")) {
            image.setImageResource(R.drawable.burc_akrep);
            burcName = "Akrep";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#82396A"));
        } else if (selectedburc.contains("yay")) {
            image.setImageResource(R.drawable.burc_yay);
            burcName = "Yay";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#8F4073"));
        } else if (selectedburc.contains("oglak")) {
            image.setImageResource(R.drawable.burc_oglak);
            burcName = "Oğlak";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#C55C73"));
        } else if (selectedburc.contains("kova")) {
            image.setImageResource(R.drawable.burc_kova);
            burcName = "Kova";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#BC4F6C"));
        } else {
            image.setImageResource(R.drawable.burc_balik);
            burcName = "Balık";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#A24F73"));
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Ayarlarınız kaydedildi...", Toast.LENGTH_SHORT).show();
                    MainActivity.createFolder();
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
                saveBitmap();
            }
        } else {
            saveBitmap();
        }
    }

    public void saveBitmap() {
        CharSequence now = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm", new Date());
        String fileName = now + ".png";

        try {
            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar", fileName);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, outputStream);
            outputStream.flush();
            outputStream.close();

            shareIt(fileName);
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    public void shareIt(String path) {
        // Share
        Uri myUri = Uri.parse("file://" + path);
        System.out.println(myUri);
        String shareBody = "Yükselen burcum" + burcName + " çıktı. Seninki? https://play.google.com/store/apps/details?id=org.uusoftware.burclar";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(intent, "Paylaş..."));
    }
}