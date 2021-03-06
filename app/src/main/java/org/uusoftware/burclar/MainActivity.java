package org.uusoftware.burclar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.kobakei.ratethisapp.RateThisApp;

import org.uusoftware.burclar.receiver.AlarmReceiver;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    public static boolean premium;
    public static int adCount;
    static InterstitialAd facebookInterstitial;
    static com.google.android.gms.ads.InterstitialAd admobInterstitial;
    boolean doubleBackToExitPressedOnce;
    SharedPreferences prefs;
    PendingIntent pendingIntent;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;

    Window window;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    RelativeLayout bannerLayout;
    RelativeLayout adViewContainer;
    private AdView bannerFacebook;
    private com.google.android.gms.ads.AdView bannerAdmob;

    //This function calls after user give the sdcard permissions
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar/Favoriler");
        folder.mkdirs();

        File folder2 = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar/Paylaşılanlar");
        folder2.mkdirs();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CheckInternet
        if (!isNetworkConnected()) {
            Toast.makeText(getApplicationContext(), "İnternet bağlantınızda bir sorun var gibi görünüyor... Uygulamadaki bazı özellikleri kullanamayabilirsiniz.", Toast.LENGTH_LONG).show();
        }

        // Initializing Toolbar and setting it as the actionbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ColoredBars
        window = this.getWindow();
        coloredBars(ContextCompat.getColor(this, R.color.colorMainDark), ContextCompat.getColor(this, R.color.colorMainPrimary));

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer openes as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //Initializing NavigationView
        navigationView = findViewById(R.id.nav_view);

        //Add Navigation header and its ClickListener
        View headerView = getLayoutInflater().inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.nav_text_version), Toast.LENGTH_LONG).show();
            }
        });

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        menuItem.setChecked(true);
                        Fragment fragment = new FragmentHome();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, "Home").commit();
                        toolbar.setTitle(R.string.nav_text_home);
                        return true;
                    case R.id.nav_uyum:
                        menuItem.setChecked(true);
                        Fragment fragment2 = new FragmentSecond();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment2, "Second").commit();
                        toolbar.setTitle(R.string.nav_text_uyum);
                        return true;
                    case R.id.nav_yukselen:
                        menuItem.setChecked(true);
                        Fragment fragment3 = new FragmentThird();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment3, "Third").commit();
                        toolbar.setTitle(R.string.nav_text_yukselen);
                        return true;
                    case R.id.nav_cin:
                        menuItem.setChecked(true);
                        Fragment fragment4 = new FragmentFourth();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment4, "Fourth").commit();
                        toolbar.setTitle(R.string.nav_text_cin);
                        return true;
                    case R.id.nav_favoriler:
                        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_paylasilanlar:
                        Intent intent2 = new Intent(MainActivity.this, SharingsActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.nav_help:
                        Intent intent3 = new Intent(MainActivity.this, FaqActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.nav_premium:
                        if (!premium) {
                            try {
                                buyPremium();
                            } catch (RemoteException | SendIntentException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Zaten daha önce premium sürüme geçmiş yapmışsınız...", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    case R.id.nav_puanla:
                        //PUANLA
                        Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=org.uusoftware.burclar"));
                        startActivity(intent4);
                        return true;
                    case R.id.nav_support:
                        //PAYLAŞ
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Günlük Burçlar: Astroloji, burç uyumu, yükselen burç ve daha fazlası! https://play.google.com/store/apps/details?id=org.uusoftware.burclar");
                        sendIntent.setType("text/plain");
                        startActivity(Intent.createChooser(sendIntent, "Uygulamayı paylaş"));
                        return true;
                    case R.id.nav_beta:
                        //Beta
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        builder.enableUrlBarHiding();
                        builder.setShowTitle(true);
                        builder.setToolbarColor(Color.parseColor("#212121"));
                        customTabsIntent.launchUrl(MainActivity.this, Uri.parse("https://play.google.com/apps/testing/org.uusoftware.burclar"));
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Bir hata oluştu! Lütfen daha sonra tekrar deneyiniz...", Toast.LENGTH_LONG).show();
                        return true;
                }
            }
        });

        // When Activity first times opened
        if (savedInstanceState == null) {
            Fragment fragment = new FragmentHome();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, "Home").commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        //Check user have a premium
        InAppBilling();
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        premium = prefs.getBoolean("Premium", false);

        // AppRater
        RateThisApp.onCreate(this);
        RateThisApp.showRateDialogIfNeeded(this);

        // AlarmManager
        AlarmManager();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void InAppBilling() {
        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
                AudienceNetwork();
                loadBanner();
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
            assert ownedSkus != null;
            if (ownedSkus.contains("premium")) {
                prefs.edit().putBoolean("Premium", true).apply();
                loadBanner();
            } else {
                prefs.edit().putBoolean("Premium", false).apply();
                AudienceNetwork();
                loadBanner();
            }
        } else {
            AudienceNetwork();
            loadBanner();
        }
    }

    public void buyPremium() throws RemoteException, SendIntentException {
        Toast.makeText(MainActivity.this,
                "Premium sürüme geçerek uygulama içerisindeki tüm reklamları kaldırabilirsiniz.", Toast.LENGTH_LONG)
                .show();
        Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(), "premium", "inapp",
                "/tYMgwhg1DVikb4R4iLNAO5pNj/QWh19+vwajyUFbAyw93xVnDkeTZFdhdSdJ8M");
        PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
        assert pendingIntent != null;
        startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), 0,
                0, 0);
    }

    //First try to load Audience Network, fails load AdMob
    public void AudienceNetwork() {
        if (adCount < 3) {
            facebookInterstitial = new InterstitialAd(this, getString(R.string.interstitial_facebook));
            facebookInterstitial.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    // Interstitial displayed callback
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    // Interstitial dismissed callback
                    AudienceNetwork();
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    AdMob();
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Show the ad when it's done loading.
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            facebookInterstitial.loadAd();
        }
    }

    public void AdMob() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("1570E844A1342361F2C23869919CF823").build();
        admobInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
        admobInterstitial.setAdUnitId(getString(R.string.interstitial_admob));
        admobInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                AudienceNetwork();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                AudienceNetwork();
            }
        });
        admobInterstitial.loadAd(adRequest);
    }

    public void loadBanner() {
        bannerLayout = findViewById(R.id.bannerLayout);
        adViewContainer = findViewById(R.id.adFacebook);
        bannerAdmob = findViewById(R.id.adView);

        if (premium) {
            bannerLayout.setVisibility(View.GONE);
            adViewContainer.setVisibility(View.GONE);
            bannerAdmob.setVisibility(View.GONE);
            FrameLayout layout = findViewById(R.id.frame_container);
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) layout.getLayoutParams();
            params.bottomMargin = 0;
            layout.setLayoutParams(params);
        } else {
            bannerFacebook = new AdView(MainActivity.this, getString(R.string.banner_facebook), AdSize.BANNER_HEIGHT_50);
            adViewContainer.addView(bannerFacebook);
            bannerFacebook.setAdListener(new com.facebook.ads.AdListener() {
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
    }

    public void AlarmManager() {
        boolean alarm = prefs.getBoolean("Alarm", true);
        if (alarm) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            Calendar calendar = Calendar.getInstance();

            int alarmHour = prefs.getInt("alarmHour", 10);
            int alarmMinute = prefs.getInt("alarmMinute", 0);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.HOUR_OF_DAY, alarmHour);
            calendar2.set(Calendar.MINUTE, alarmMinute);

            if (calendar.getTimeInMillis() < calendar2.getTimeInMillis()) {
                alarmManager.setInexactRepeating(AlarmManager.RTC, calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
            } else {
                alarmManager.setInexactRepeating(AlarmManager.RTC, 1000 * 60 * 60 * 24 + calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
            }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "Satın alma başarılı. Premium sürüme geçiriliyorsunuz, teşekkürler!",
                        Toast.LENGTH_LONG).show();
                prefs.edit().putBoolean("Premium", true).apply();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Satın alma başarısız. Lütfen daha sonra tekrar deneyiniz.",
                        Toast.LENGTH_LONG).show();
                prefs.edit().putBoolean("Premium", false).apply();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            navigationView.setCheckedItem(R.id.nav_home);

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
                    Fragment fragment = new FragmentHome();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, "Home").commit();
                    toolbar.setTitle(R.string.nav_text_home);
                }
            }

            // FragmentThird OnBackPressed
            if (fragment2 != null) {
                if (fragment2.isVisible()) {
                    Fragment fragment = new FragmentHome();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, "Home").commit();
                    toolbar.setTitle(R.string.nav_text_home);
                }
            }

            // FragmentFourth OnBackPressed
            if (fragment3 != null) {
                if (fragment3.isVisible()) {
                    Fragment fragment = new FragmentHome();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, "Home").commit();
                    toolbar.setTitle(R.string.nav_text_home);
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
        if (facebookInterstitial != null) {
            facebookInterstitial.destroy();
        }
        if (bannerFacebook != null) {
            bannerFacebook.destroy();
        }
        if (bannerAdmob != null) {
            bannerAdmob.destroy();
        }
    }
}