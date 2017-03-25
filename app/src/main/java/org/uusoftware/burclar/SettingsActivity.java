package org.uusoftware.burclar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class SettingsActivity extends AppCompatActivity {

    Window window;
    Toolbar toolbar;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
        t.setScreenName("Ayarlar");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);
        editor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_save:
                editor.commit();
                Toast.makeText(this, R.string.settings_saved, Toast.LENGTH_SHORT).show();
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
}