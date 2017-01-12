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
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.uusoftware.burclar.adapter.NavDrawerListAdapter;
import org.uusoftware.burclar.model.NavDrawerItem;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jp.co.recruit_mp.android.rmp_appirater.RmpAppirater;

public class MainActivity extends AppCompatActivity {

    static InterstitialAd interstitial;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    Fragment fragment = null;
    public static Context mContext;
    FragmentTransaction ft;
    boolean doubleBackToExitPressedOnce = false;
    PendingIntent pendingIntent;
    public static boolean premium = false;
    public static long start = System.currentTimeMillis();
    public static boolean displayed = false;
    public static boolean displayed2 = false;
    public static boolean displayed3 = false;
    public static boolean displayed4 = false;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private static final int REQUEST_ID_PERMISSION = 1;
    private static String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this.getApplicationContext();
        InAppBilling();
        editor = getSharedPreferences("Preferences", Context.MODE_PRIVATE).edit();

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

        // Other codes
        mTitle = mDrawerTitle = getTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Home2
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Home3
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Home4
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Home5
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Premium
        if (premium) {
            // Do nothing
        } else {
            // Call AdMob
            AdMob();
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        }
        // Footer
        navDrawerItems.add(new NavDrawerItem("", 0));

        // Recycle the typed array
        navMenuIcons.recycle();
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_slider_drawer, R.string.app_name) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu();
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            displayView(0);
        }
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

    public static void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar");
        folder.mkdirs();
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_help:
                Intent intent2 = new Intent(this, HelpingActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_about:
                Intent intent3 = new Intent(this, AboutUsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_support:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Günlük Burçlar uygulaması Google Play'de https://play.google.com/store/apps/details?id=org.uusoftware.burclar";
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Paylaş"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void displayView(int position) {
        ft = getSupportFragmentManager().beginTransaction();

        // Fragments
        FragmentHome fragment0 = (FragmentHome) getSupportFragmentManager().findFragmentByTag("Home");
        FragmentSecond fragment1 = (FragmentSecond) getSupportFragmentManager().findFragmentByTag("Second");
        FragmentThird fragment2 = (FragmentThird) getSupportFragmentManager().findFragmentByTag("Third");
        FragmentFourth fragment3 = (FragmentFourth) getSupportFragmentManager().findFragmentByTag("Fourth");

        switch (position) {
            case 0:
                if (fragment0 != null && fragment0.isVisible()) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    setTitle(navMenuTitles[0]);
                    mDrawerLayout.closeDrawer(mDrawerList);

                    fragment = new FragmentHome();
                    ft.replace(R.id.frame_container, fragment, "Home").commit();
                }
                break;
            case 1:
                if (fragment1 != null && fragment1.isVisible()) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    setTitle(navMenuTitles[1]);
                    mDrawerLayout.closeDrawer(mDrawerList);

                    fragment = new FragmentSecond();
                    ft.replace(R.id.frame_container, fragment, "Second").commit();
                }
                break;
            case 2:
                if (fragment2 != null && fragment2.isVisible()) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    setTitle(navMenuTitles[2]);
                    mDrawerLayout.closeDrawer(mDrawerList);

                    fragment = new FragmentThird();
                    ft.replace(R.id.frame_container, fragment, "Third").commit();
                }
                break;
            case 3:
                if (fragment3 != null && fragment3.isVisible()) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    setTitle(navMenuTitles[3]);
                    mDrawerLayout.closeDrawer(mDrawerList);

                    fragment = new FragmentFourth();
                    ft.replace(R.id.frame_container, fragment, "Fourth").commit();
                }
                break;
            case 4:
                setTitle(navMenuTitles[4]);
                mDrawerLayout.closeDrawer(mDrawerList);
                Intent intent = new Intent(this, FavoritesActivity.class);
                startActivity(intent);
                break;
            case 5:
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
                break;
            default:
                break;
        }
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
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
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
                displayView(0);
            }
        }

        // FragmentThird OnBackPressed
        if (fragment2 != null) {
            if (fragment2.isVisible()) {
                displayView(0);
            }
        }

        // FragmentFourth OnBackPressed
        if (fragment3 != null) {
            if (fragment3.isVisible()) {
                displayView(0);
            }
        }

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }
}