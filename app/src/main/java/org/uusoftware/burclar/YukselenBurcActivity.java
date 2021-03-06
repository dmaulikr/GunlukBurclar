package org.uusoftware.burclar;

import android.Manifest;
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

public class YukselenBurcActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

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
        collapsingToolbarLayout = findViewById(R.id.collapsing_header);
        collapsingToolbarLayout.setTitle("Yükselen burç");
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
                    coloredBars(ContextCompat.getColor(YukselenBurcActivity.this, R.color.colorYukselenDark), ContextCompat.getColor(YukselenBurcActivity.this, R.color.colorYukselenPrimary));
                } else if (verticalOffset == 0) {
                    coloredBars(Color.TRANSPARENT, Color.TRANSPARENT);
                } else {
                    coloredBars(Color.argb(255 - verticalOffset / 2, 230, 74, 25), Color.argb(255 - verticalOffset / 2, 255, 87, 34));
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

        ImageView image = findViewById(R.id.burc_header);
        WebView myWebView = findViewById(R.id.webViewGeneral);
        link = "file:///android_asset/burcyorumlari/" + selectedburc + ".html";

        assert selectedburc != null;
        if (selectedburc.contains("koc")) {
            image.setImageResource(R.drawable.burc_koc);
            burcName = "Koç";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("boga")) {
            image.setImageResource(R.drawable.burc_boga);
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#1E88E5"));
            burcName = "Boğa";
        } else if (selectedburc.contains("ikizler")) {
            image.setImageResource(R.drawable.burc_ikizler);
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#1E88E5"));
            burcName = "İkizler";
        } else if (selectedburc.contains("yengec")) {
            image.setImageResource(R.drawable.burc_yengec);
            burcName = "Yengeç";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("aslan")) {
            image.setImageResource(R.drawable.burc_aslan);
            burcName = "Aslan";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("basak")) {
            image.setImageResource(R.drawable.burc_basak);
            burcName = "Başak";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("terazi")) {
            image.setImageResource(R.drawable.burc_terazi);
            burcName = "Terazi";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("akrep")) {
            image.setImageResource(R.drawable.burc_akrep);
            burcName = "Akrep";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("yay")) {
            image.setImageResource(R.drawable.burc_yay);
            burcName = "Yay";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("oglak")) {
            image.setImageResource(R.drawable.burc_oglak);
            burcName = "Oğlak";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#311B92"));
        } else if (selectedburc.contains("kova")) {
            image.setImageResource(R.drawable.burc_kova);
            burcName = "Kova";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#311B92"));
        } else {
            image.setImageResource(R.drawable.burc_balik);
            burcName = "Balık";
            collapsingToolbarLayout.setBackgroundColor(Color.parseColor("#311B92"));
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