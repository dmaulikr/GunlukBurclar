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

        if (isNetworkConnected() && !premium) {
            AdMob();
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeover = true;
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        } else {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 1500);
        }
    }

    public void AdMob() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("FBD4B60FBD19C916398DB53B16F09D17").build();
        admobInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
        admobInterstitial.setAdUnitId(getString(R.string.interstitial_admob));
        admobInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (!timeover) {
                    handler.removeCallbacksAndMessages(null);
                    admobInterstitial.show();
                    MainActivity.adCount++;
                }
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
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