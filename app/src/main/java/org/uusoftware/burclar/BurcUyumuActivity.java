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
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class BurcUyumuActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    String uyum;
    int skor, burckadin, burcerkek;
    Window window;
    Toolbar toolbar;
    Context mContext;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burcuyumu);

        /* Colored bars */
        mContext = this.getApplicationContext();

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
                    coloredBars(ContextCompat.getColor(mContext, R.color.colorUyumDark), ContextCompat.getColor(mContext, R.color.colorUyumPrimary));
                } else if (verticalOffset == 0) {
                    coloredBars(Color.TRANSPARENT, Color.TRANSPARENT);
                } else {
                    coloredBars(Color.argb(255 - verticalOffset / 2, 233, 30, 99), Color.argb(255 - verticalOffset / 2, 233, 30, 99));
                }
            }
        });

        // Analytics
        Tracker t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Burç uyumu - Sonuç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle extras = getIntent().getExtras();
        uyum = extras.getString("burcuyumu");
        skor = extras.getInt("number");
        burckadin = extras.getInt("burckadin");
        burcerkek = extras.getInt("burcerkek");


       /* text = (TextView) findViewById(R.id.textViewUyum);
        number = (TextView) findViewById(R.id.textViewSkor);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        imageerkek = (ImageView) findViewById(R.id.imageView1);
        imagekadin = (ImageView) findViewById(R.id.imageView2);

        text.setText(uyum);
        number.setText("%" + Integer.toString(skor));
        pb.setProgress(skor);*/

        /*
        // SetImageViews
        // Erkek
        switch (burcerkek) {
            case 0:
                imageerkek.setImageResource(R.drawable.burc_koc);
                break;
            case 1:
                imageerkek.setImageResource(R.drawable.burc_boga);
                break;
            case 2:
                imageerkek.setImageResource(R.drawable.burc_ikizler);
                break;
            case 3:
                imageerkek.setImageResource(R.drawable.burc_yengec);
                break;
            case 4:
                imageerkek.setImageResource(R.drawable.burc_aslan);
                break;
            case 5:
                imageerkek.setImageResource(R.drawable.burc_basak);
                break;
            case 6:
                imageerkek.setImageResource(R.drawable.burc_terazi);
                break;
            case 7:
                imageerkek.setImageResource(R.drawable.burc_akrep);
                break;
            case 8:
                imageerkek.setImageResource(R.drawable.burc_yay);
                break;
            case 9:
                imageerkek.setImageResource(R.drawable.burc_oglak);
                break;
            case 10:
                imageerkek.setImageResource(R.drawable.burc_kova);
                break;
            case 11:
                imageerkek.setImageResource(R.drawable.burc_balik);
                break;
            default:
                break;
        }
        // Kadın
        switch (burckadin) {
            case 0:
                imagekadin.setImageResource(R.drawable.burc_koc);
                break;
            case 1:
                imagekadin.setImageResource(R.drawable.burc_boga);
                break;
            case 2:
                imagekadin.setImageResource(R.drawable.burc_ikizler);
                break;
            case 3:
                imagekadin.setImageResource(R.drawable.burc_yengec);
                break;
            case 4:
                imagekadin.setImageResource(R.drawable.burc_aslan);
                break;
            case 5:
                imagekadin.setImageResource(R.drawable.burc_basak);
                break;
            case 6:
                imagekadin.setImageResource(R.drawable.burc_terazi);
                break;
            case 7:
                imagekadin.setImageResource(R.drawable.burc_akrep);
                break;
            case 8:
                imagekadin.setImageResource(R.drawable.burc_yay);
                break;
            case 9:
                imagekadin.setImageResource(R.drawable.burc_oglak);
                break;
            case 10:
                imagekadin.setImageResource(R.drawable.burc_kova);
                break;
            case 11:
                imagekadin.setImageResource(R.drawable.burc_balik);
                break;
            default:
                break;
        }
        */

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
                    saveBitmap();
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

    public void shareIt(String name) {
        // Share
        File imagePath = new File(this.getFilesDir(), "Günlük Burçlar");
        File newFile = new File(imagePath, name);
        Uri myUri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", newFile);
        String shareBody = "Günlük Burçlar uygulaması Google Play'de: https://play.google.com/store/apps/details?id=org.uusoftware.burclar";
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