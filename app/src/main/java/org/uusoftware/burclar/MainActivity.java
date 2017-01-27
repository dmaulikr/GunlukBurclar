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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_ID_PERMISSION = 1;
    public static boolean premium = false;
    static InterstitialAd interstitial;
    private static String[] PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    SharedPreferences prefs;
    boolean doubleBackToExitPressedOnce = false;

    PendingIntent pendingIntent;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;

    Window window;
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

   /* CallbackManager callbackmanager;
    LoginButton loginButton;*/

    public static void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Günlük Burçlar");
        folder.mkdirs();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                //FacebookLogin();
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Add Navigation header and its ClickListener
        View headerView = getLayoutInflater().inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(headerView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (isLoggedIn()) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else {
                    //Do nothing
                }*/
            }
        });

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Fragment fragment = new FragmentHome();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment, "Home").commit();
                        toolbar.setTitle(R.string.nav_text_home);
                        return true;
                    case R.id.nav_uyum:
                        Fragment fragment2 = new FragmentSecond();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment2, "Second").commit();
                        toolbar.setTitle(R.string.nav_text_uyum);
                        return true;
                    case R.id.nav_yukselen:
                        Fragment fragment3 = new FragmentThird();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment3, "Third").commit();
                        toolbar.setTitle(R.string.nav_text_yukselen);
                        return true;
                    case R.id.nav_cin:
                        Fragment fragment4 = new FragmentFourth();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment4, "Fourth").commit();
                        toolbar.setTitle(R.string.nav_text_cin);
                        return true;
                    case R.id.nav_favoriler:
                        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_help:
                        Intent intent2 = new Intent(MainActivity.this, HelpingActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.nav_premium:
                        try {
                            buyPremium();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (SendIntentException e) {
                            e.printStackTrace();
                        }
                        return true;
                    case R.id.nav_puanla:
                        //PUANLA
                        Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=org.uusoftware.burclar"));
                        startActivity(intent4);
                        return true;
                    case R.id.nav_about:
                        Intent intent3 = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.nav_beta:
                        //BETA
                        Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/apps/testing/org.uusoftware.burclar"));
                        startActivity(intent5);
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Bir hata oluştu! Lütfen daha sonra tekrar deneyiniz...", Toast.LENGTH_SHORT).show();
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

        //SharedPreferences
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        //Check user have a premium
        premium = prefs.getBoolean("Premium", false);
        InAppBilling();

        // Create Günlük Burçlar folder
        verifyStoragePermissions();

        //ColoredBars
        window = this.getWindow();
        coloredBars(ContextCompat.getColor(this, R.color.colorMainDark), ContextCompat.getColor(this, R.color.colorMainPrimary));

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
                        "Oyla!", "Belki başka zaman",
                        "Hayır, teşekkürler"));

        // AlarmManager
        AlarmManager();
    }

   /* public void FacebookLogin() {
        callbackmanager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile");
        callbackmanager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Bundle params = new Bundle();
                params.putString("fields", "id,email,gender,cover,picture.type(large)");
                new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                        new GraphRequest.Callback() {
                            @Override
                            public void onCompleted(GraphResponse response) {
                                if (response != null) {
                                    try {
                                        JSONObject data = response.getJSONObject();
                                        if (data.has("picture")) {
                                            String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                            CircleImageView profileImage = (CircleImageView) findViewById(R.id.profile_image);
                                            Picasso.with(MainActivity.this)
                                                    .load(profilePicUrl)
                                                    .into(profileImage);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).executeAsync();
            }

            @Override
            public void onCancel() {
                //Do nothing
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("hata", exception.toString());
            }
        });
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }*/

    private void InAppBilling() {
        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
                AdMob();
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
                premium = true;
                prefs.edit().putBoolean("Premium", true).apply();

                MenuItem item = navigationView.getMenu().findItem(R.id.nav_premium);
                item.setTitle(R.string.nav_text_premium2);
            } else {
                premium = false;
                prefs.edit().putBoolean("Premium", false).apply();
                AdMob();

                MenuItem item = navigationView.getMenu().findItem(R.id.nav_premium);
                item.setTitle(R.string.nav_text_premium);
                item.setIcon(R.drawable.ic_slider_premium);
            }
        } else {
            AdMob();
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

        int hourofday = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        Calendar calendar = Calendar.getInstance();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "Satın alma başarılı. Premium sürüme geçiriliyorsunuz, teşekkürler!",
                        Toast.LENGTH_LONG).show();
                prefs.edit().putBoolean("Premium", true).commit();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Satın alma başarısız. Lütfen daha sonra tekrar deneyiniz.",
                        Toast.LENGTH_LONG).show();
                prefs.edit().putBoolean("Premium", false).commit();
            }
        }
        // callbackmanager.onActivityResult(requestCode, resultCode, data);
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
    }
}