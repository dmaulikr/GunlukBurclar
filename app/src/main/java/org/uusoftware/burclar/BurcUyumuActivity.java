package org.uusoftware.burclar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
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
    TextView text, number;
    ProgressBar pb;
    Tracker t;
    ImageView imageerkek, imagekadin;

    //Facebook Audience Network
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burcuyumu);

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorMainDark));

            toolbar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorMainPrimary)));
        } else {
            toolbar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorMainPrimary)));
        }

        // Analytics
        t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
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

        text = (TextView) findViewById(R.id.textViewUyum);
        number = (TextView) findViewById(R.id.textViewSkor);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        imageerkek = (ImageView) findViewById(R.id.imageView1);
        imagekadin = (ImageView) findViewById(R.id.imageView2);

        text.setText(uyum);
        number.setText("%" + Integer.toString(skor));
        pb.setProgress(skor);

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
        System.out.println(myUri);
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

    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}