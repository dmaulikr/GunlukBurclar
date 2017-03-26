package org.uusoftware.burclar;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class SettingsActivity extends AppCompatActivity {

    Window window;
    Toolbar toolbar;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    boolean premium, alarm;

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
        editor = prefs.edit();
        premium = MainActivity.premium;
        alarm = prefs.getBoolean("Alarm", true);

        Switch alarmSwitch = (Switch) findViewById(R.id.mySwitch);
        if (alarm) {
            alarmSwitch.setChecked(true);
        } else {
            alarmSwitch.setChecked(false);
        }
        if (premium) {
            alarmSwitch.setEnabled(true);
        } else {
            alarmSwitch.setEnabled(false);
            Toast.makeText(SettingsActivity.this, R.string.only_premium, Toast.LENGTH_LONG).show();
        }
        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("Alarm", true);
                } else {
                    editor.putBoolean("Alarm", false);
                }
            }
        });

        final TextView textViewClock = (TextView) findViewById(R.id.textViewClockText);
        final int alarmHour = prefs.getInt("alarmHour", 10);
        final int alarmMinute = prefs.getInt("alarmMinute", 0);
        textViewClock.setText(pad(alarmHour) + ":" + pad(alarmMinute));
        textViewClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editor.putInt("alarmHour", selectedHour);
                        editor.putInt("alarmMinute", selectedMinute);
                        textViewClock.setText(pad(selectedHour) + ":" + pad(selectedMinute));
                    }
                }, alarmHour, alarmMinute, true);
                mTimePicker.setTitle("Bildirim saatini seçiniz");
                mTimePicker.show();
            }
        });

        TextView textViewAccount = (TextView) findViewById(R.id.textViewAccountInfo);
        if (premium) {
            textViewAccount.setText(R.string.account_premium);
        } else {
            textViewAccount.setText(R.string.account_standart);
        }

        TextView textViewVersion = (TextView) findViewById(R.id.textViewVersionInfo);
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        textViewVersion.setText("v" + version);

        ImageView privacy = (ImageView) findViewById(R.id.imageButton);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Privacy
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                builder.enableUrlBarHiding();
                builder.setShowTitle(true);
                builder.setToolbarColor(Color.parseColor("#212121"));
                customTabsIntent.launchUrl(SettingsActivity.this, Uri.parse("http://uusoftware.org/privacy"));
            }
        });

        ImageView beta = (ImageView) findViewById(R.id.imageButton2);
        beta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Privacy
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                builder.enableUrlBarHiding();
                builder.setShowTitle(true);
                builder.setToolbarColor(Color.parseColor("#212121"));
                customTabsIntent.launchUrl(SettingsActivity.this, Uri.parse("https://play.google.com/apps/testing/org.uusoftware.burclar"));
            }
        });
    }

    public String pad(int input) {
        if (input >= 10) {
            return String.valueOf(input);
        } else {
            return "0" + String.valueOf(input);
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
                editor.apply();
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