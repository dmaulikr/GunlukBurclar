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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.uusoftware.burclar.adapter.SecondActivityAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    public static String burcid;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    SecondActivityAdapter mSectionsPagerAdapter;
    PagerTitleStrip pagertabstrip;
    ViewPager mViewPager;
    Window window;
    Toolbar toolbar;
    boolean premium;

    //Facebook Audience Network
    RelativeLayout bannerLayout;
    RelativeLayout adViewContainer;
    private AdView bannerFacebook;
    private com.google.android.gms.ads.AdView bannerAdmob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Premium & Ads
        premium = MainActivity.premium;

        bannerLayout = (RelativeLayout) findViewById(R.id.bannerLayout);
        adViewContainer = (RelativeLayout) findViewById(R.id.adFacebook);
        bannerAdmob = (com.google.android.gms.ads.AdView) findViewById(R.id.adView);

        if (premium) {
            bannerLayout.setVisibility(View.GONE);
            adViewContainer.setVisibility(View.GONE);
            bannerAdmob.setVisibility(View.GONE);
        } else {
            bannerFacebook = new AdView(SecondActivity.this, getString(R.string.banner_facebook), AdSize.BANNER_HEIGHT_50);
            adViewContainer.addView(bannerFacebook);
            bannerFacebook.setAdListener(new AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    adViewContainer.setVisibility(View.GONE);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    bannerAdmob.loadAd(adRequest);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Ad loaded callback
                    bannerAdmob.setVisibility(View.GONE);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            bannerFacebook.loadAd();
        }

        //StatusBar
        window = this.getWindow();

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coloredBars(ContextCompat.getColor(this, R.color.colorMainDark), ContextCompat.getColor(this, R.color.colorMainPrimary));

        // Analytics
        Tracker t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Burçlar - Sonuç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        Bundle extras = getIntent().getExtras();
        burcid = extras.getString("burcid");

        mSectionsPagerAdapter = new SecondActivityAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        pagertabstrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
        String selectedburc = SecondActivity.burcid;
        if (selectedburc.contains("koc")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("boga")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("ikizler")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("yengec")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("aslan")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("basak")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("terazi")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("akrep")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("yay")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("oglak")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#311B92"));
        } else if (selectedburc.contains("kova")) {
            pagertabstrip.setBackgroundColor(Color.parseColor("#311B92"));
        } else {
            pagertabstrip.setBackgroundColor(Color.parseColor("#311B92"));
        }
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

    public void saveBitmap(String operation) {
        CharSequence now = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm", new Date());
        String fileName = now + ".png";

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
            bitmap.compress(Bitmap.CompressFormat.PNG, 70, outputStream);
            outputStream.flush();
            outputStream.close();

            if (operation.contains("share")) {
                shareIt(fileName);
            } else {
                Toast.makeText(this, "Favorilerinize eklendi...", Toast.LENGTH_SHORT)
                        .show();
            }
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

    public void shareIt(String path) {
        // Share
        Uri myUri = Uri.parse("file://" + path);
        String shareBody = "Günlük Burçlar Google Play'de: https://play.google.com/store/apps/details?id=org.uusoftware.burclar";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareBody);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, myUri);
        startActivity(Intent.createChooser(intent, "Paylaş..."));
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

    @Override
    public void onDestroy() {
        if (bannerFacebook != null) {
            bannerFacebook.destroy();
        }
        if (bannerAdmob != null) {
            bannerAdmob.destroy();
        }
        super.onDestroy();
    }
}