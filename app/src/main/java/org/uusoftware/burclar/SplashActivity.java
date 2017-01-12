package org.uusoftware.burclar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class SplashActivity extends Activity {

    InterstitialAd interstitial;
    Handler handler;
    boolean timeover = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences prefs = this.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        boolean premium = prefs.getBoolean("Premium", false);
        handler = new Handler();

        if (isNetworkAvailable()) {
            if (premium) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timeover = true;
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }, 2000);
            } else {
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
            }
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeover = true;
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 3000);
        }
    }

    public void AdMob() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("0A83AF9337EAE655A7B29C5B61372D84").build();
        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId("ca-app-pub-1576175228836763/3285097730");
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (timeover) {
                    // Do nothing
                } else {
                    handler.removeCallbacksAndMessages(null);
                    interstitial.show();
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
        interstitial.loadAd(adRequest);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}