package org.uusoftware.burclar;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jp.co.recruit_mp.android.rmp_appirater.RmpAppirater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_ID_PERMISSION = 1;
    public static Context mContext;
    public static boolean premium = false;
    public static long start = System.currentTimeMillis();
    public static boolean displayed = false;
    public static boolean displayed2 = false;
    public static boolean displayed3 = false;
    public static boolean displayed4 = false;
    static InterstitialAd interstitial;
    private static String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    Fragment fragment = null;
    FragmentTransaction ft;
    boolean doubleBackToExitPressedOnce = false;
    PendingIntent pendingIntent;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Toolbar toolbar;

    public static void displayAds() {
        if (interstitial.isLoaded()) {
            interstitial.show();
            displayed = true;
        } else {
            mContext.startActivity(FragmentHome.intent);
        }
    }

    public static void displayAds2() {
        if (interstitial.isLoaded()) {
            interstitial.show();
            displayed2 = true;
        } else {
            mContext.startActivity(FragmentHome.intent);
        }
    }

    public static void displayAds3() {
        if (interstitial.isLoaded()) {
            interstitial.show();
            displayed3 = true;
        } else {
            mContext.startActivity(FragmentHome.intent);
        }
    }

    public static void displayAds4() {
        if (interstitial.isLoaded()) {
            interstitial.show();
            displayed4 = true;
        } else {
            mContext.startActivity(FragmentHome.intent);
        }
    }

    public static void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar");
        folder.mkdirs();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();
        InAppBilling();
        editor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();

        // Other codes
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ft = getSupportFragmentManager().beginTransaction();
        fragment = new FragmentHome();
        ft.replace(R.id.frame_container, fragment, "Home").commit();
        toolbar.setTitle(R.string.nav_home);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Colored bars
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorMainDark));

            toolbar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorMainPrimary)));
        } else {
            toolbar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.colorMainPrimary)));
        }

        // Create Günlük Burçlar folder
        verifyStoragePermissions();

        // AppRater
        RmpAppirater.appLaunched(this,
                new RmpAppirater.ShowRateDialogCondition() {
                    @Override
                    public boolean isShowRateDialog(
                            long appLaunchCount, long appThisVersionCodeLaunchCount,
                            long firstLaunchDate, int appVersionCode,
                            int previousAppVersionCode, Date rateClickDate,
                            Date reminderClickDate, boolean doNotShowAgain) {
                        // Show rating dialog if user isn't rating yet
                        // && don't select "Not show again"
                        // && launched app more than 5 times.
                        return (rateClickDate == null && !doNotShowAgain && appLaunchCount >= 5);
                    }
                },
                new RmpAppirater.Options(
                        "Günlük Burçlar'ı sevdin mi?", "5 puan vererek bize destek olabilirsin!",
                        "Oyla!", "Başka zaman",
                        "Hayır, teşekkürler"));

        // AlarmManager
        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);
        AlarmManager();
    }

    private void InAppBilling() {
        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = IInAppBillingService.Stub.asInterface(service);
                try {
                    checkPremium();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };

        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    public void checkPremium() throws RemoteException {
        Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
        if (ownedItems.getInt("RESPONSE_CODE") == 0) {
            ArrayList<String> ownedSkus = ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            if (ownedSkus.contains("premium")) {
                editor.putBoolean("Premium", true).apply();
            } else {
                editor.putBoolean("Premium", false).apply();
                AdMob();
            }
        }
    }

    public void buyPremium() throws RemoteException, SendIntentException {
        Toast.makeText(MainActivity.this,
                "Premium sürüme geçerek uygulama içerisindeki tüm reklamları kaldırabilirsiniz.", Toast.LENGTH_LONG)
                .show();
        Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(), "premium", "inapp",
                "/tYMgwhg1DVikb4R4iLNAO5pNj/QWh19+vwajyUFbAyw93xVnDkeTZFdhdSdJ8M");
        PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
        startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0),
                Integer.valueOf(0), Integer.valueOf(0));
    }

    public void AdMob() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("0A83AF9337EAE655A7B29C5B61372D84").build();
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-1576175228836763/3285097730");
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startActivity(FragmentHome.intent);
                AdMob();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }
        });
        interstitial.loadAd(adRequest);
    }

    public void AlarmManager() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();

        Calendar calendar2 = Calendar.getInstance();
        int hourofday = calendar2.get(Calendar.HOUR_OF_DAY);

        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (hourofday < 10) {
            alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {
            alarmManager.setInexactRepeating(AlarmManager.RTC, 1000 * 60 * 60 * 24 + calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void verifyStoragePermissions() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, PERMISSION, REQUEST_ID_PERMISSION);
            } else {
                createFolder();
            }
        } else {
            createFolder();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new FragmentHome();
            ft.replace(R.id.frame_container, fragment, "Home").commit();
            toolbar.setTitle(R.string.nav_home);
        } else if (id == R.id.nav_uyum) {
            fragment = new FragmentSecond();
            ft.replace(R.id.frame_container, fragment, "Second").commit();
            toolbar.setTitle(R.string.nav_uyum);
        } else if (id == R.id.nav_yukselen) {
            fragment = new FragmentThird();
            ft.replace(R.id.frame_container, fragment, "Third").commit();
            toolbar.setTitle(R.string.nav_yukselen);
        } else if (id == R.id.nav_cin) {
            fragment = new FragmentFourth();
            ft.replace(R.id.frame_container, fragment, "Fourth").commit();
            toolbar.setTitle(R.string.nav_cin);
        } else if (id == R.id.nav_favoriler) {
            Intent intent = new Intent(this, FavoritesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_help) {
            Intent intent2 = new Intent(this, HelpingActivity.class);
            startActivity(intent2);
        } else if (id == R.id.nav_premium) {
            if (premium) {
                // Do nothing
            } else {
                try {
                    buyPremium();
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SendIntentException e) {
                    e.printStackTrace();
                }
            }
        } else if (id == R.id.nav_about) {
            Intent intent3 = new Intent(this, AboutUsActivity.class);
            startActivity(intent3);
        } else if (id == R.id.nav_puanla) {
            //PUANLA
        } else {
            //BETA
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "Satın alma başarılı. Premium sürüme geçiriliyorsunuz, teşekkürler!",
                        Toast.LENGTH_LONG).show();
                editor.putBoolean("Premium", true).commit();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Satın alma başarısız. Lütfen daha sonra tekrar deneyiniz.",
                        Toast.LENGTH_LONG).show();
                editor.putBoolean("Premium", false).commit();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_PERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createFolder();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            ft = getSupportFragmentManager().beginTransaction();

            // Fragments
            FragmentHome fragment0 = (FragmentHome) getSupportFragmentManager().findFragmentByTag("Home");
            FragmentSecond fragment1 = (FragmentSecond) getSupportFragmentManager().findFragmentByTag("Second");
            FragmentThird fragment2 = (FragmentThird) getSupportFragmentManager().findFragmentByTag("Third");
            FragmentFourth fragment3 = (FragmentFourth) getSupportFragmentManager().findFragmentByTag("Fourth");

            // FragmentHome OnBackPressed
            if (fragment0 != null) {
                if (fragment0.isVisible()) {
                    if (doubleBackToExitPressedOnce) {
                        super.onBackPressed();
                        return;
                    }

                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, getString(R.string.exit), Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                }
            }

            // FragmentSecond OnBackPressed
            if (fragment1 != null) {
                if (fragment1.isVisible()) {
                    fragment = new FragmentHome();
                    ft.replace(R.id.frame_container, fragment, "Home").commit();
                    toolbar.setTitle(R.string.nav_home);
                }
            }

            // FragmentThird OnBackPressed
            if (fragment2 != null) {
                if (fragment2.isVisible()) {
                    fragment = new FragmentHome();
                    ft.replace(R.id.frame_container, fragment, "Home").commit();
                    toolbar.setTitle(R.string.nav_home);
                }
            }

            // FragmentFourth OnBackPressed
            if (fragment3 != null) {
                if (fragment3.isVisible()) {
                    fragment = new FragmentHome();
                    ft.replace(R.id.frame_container, fragment, "Home").commit();
                    toolbar.setTitle(R.string.nav_home);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mServiceConn != null) {
            unbindService(mServiceConn);
        }
    }
}