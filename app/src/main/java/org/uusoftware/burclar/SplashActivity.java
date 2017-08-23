package org.uusoftware.burclar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends Activity {

    SharedPreferences prefs;
    InterstitialAd admobInterstitial;
    boolean premium, timeover = false;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        premium = prefs.getBoolean("Premium", false);
        handler = new Handler();

        if (isNetworkConnected() && !premium) {
            AdMob();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeover = true;
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 5000);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 2500);
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
                if (!timeover) {
                    handler.removeCallbacksAndMessages(null);
                    MainActivity.adCount++;
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    admobInterstitial.show();
                    finish();
                }
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }
        });
        admobInterstitial.loadAd(adRequest);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}