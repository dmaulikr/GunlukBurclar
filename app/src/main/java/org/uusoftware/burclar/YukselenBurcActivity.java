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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
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
    int selectedburc;
    Tracker t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yukselen);

        // Colored bars
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);

            toolbar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            toolbar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        // Analytics
        t = ((AnalyticsApplication) this.getApplication()).getDefaultTracker();
        t.setScreenName("Yükselen burç - Sonuç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        Bundle extras = getIntent().getExtras();
        selectedburc = extras.getInt("burcid");

        ImageView image = (ImageView) findViewById(R.id.burc_header);
        // TextView textview2 = (TextView) findViewById(R.id.textView2genelozellikler);
        TextView textview3 = (TextView) findViewById(R.id.burc_exp);

        if (selectedburc == 0) {
            image.setImageResource(R.drawable.burc_koc);
            //textview2.setText("21 Mart - 20 Nisan");
            textview3.setText(R.string.koc);
        }
        if (selectedburc == 1) {
            image.setImageResource(R.drawable.burc_boga);
            //textview2.setText("21 Nisan - 20 Mayıs");
            textview3.setText(R.string.boga);
        }
        if (selectedburc == 2) {
            image.setImageResource(R.drawable.burc_ikizler);
            // textview2.setText("21 Mayıs - 20 Haziran");
            textview3.setText(R.string.ikizler);
        }
        if (selectedburc == 3) {
            image.setImageResource(R.drawable.burc_yengec);
            // textview2.setText("21 Haziran - 20 Temmuz");
            textview3.setText(R.string.yengec);
        }
        if (selectedburc == 4) {
            image.setImageResource(R.drawable.burc_aslan);
            // textview2.setText("23 Temmuz - 23 Ağustos");
            textview3.setText(R.string.aslan);
        }
        if (selectedburc == 5) {
            image.setImageResource(R.drawable.burc_basak);
            // textview2.setText("24 Ağustos - 23 Eylül");
            textview3.setText(R.string.basak);
        }
        if (selectedburc == 6) {
            image.setImageResource(R.drawable.burc_terazi);
            //textview2.setText("24 Eylül - 23 Ekim");
            textview3.setText(R.string.terazi);
        }
        if (selectedburc == 7) {
            image.setImageResource(R.drawable.burc_akrep);
            //textview2.setText("24 Ekim - 22 Kasım");
            textview3.setText(R.string.akrep);
        }
        if (selectedburc == 8) {
            image.setImageResource(R.drawable.burc_yay);
            // textview2.setText("23 Kasım - 21 Aralık");
            textview3.setText(R.string.yay);
        }
        if (selectedburc == 9) {
            image.setImageResource(R.drawable.burc_oglak);
            // textview2.setText("22 Aralık - 20 Ocak");
            textview3.setText(R.string.oglak);
        }
        if (selectedburc == 10) {
            image.setImageResource(R.drawable.burc_kova);
            //  textview2.setText("21 Ocak - 18 Şubat");
            textview3.setText(R.string.kova);
        }
        if (selectedburc == 11) {
            image.setImageResource(R.drawable.burc_balik);
            //  textview2.setText("19 Şubat - 20 Mart");
            textview3.setText(R.string.balik);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_share:
                MainActivity.createFolder();
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
}