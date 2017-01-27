package org.uusoftware.burclar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.uusoftware.burclar.adapter.GridAdapter;

import java.util.Random;

public class FragmentHome extends Fragment {

    Intent intent;
    boolean premium;
    Window window;
    ActionBar actionbar;

    //Facebook Audience Network
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Premium & Facebook Audience Network
        premium = MainActivity.premium;
        if (premium) {
            //Do nothing
        } else {
            RelativeLayout adViewContainer = (RelativeLayout) v.findViewById(R.id.adFacebook);
            adView = new com.facebook.ads.AdView(getActivity(), "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("93b55fab32ae5be4b95fb1fa2815d35b");
            adViewContainer.addView(adView);
            adView.loadAd();
        }

        /* Colored bars */
        window = getActivity().getWindow();
        actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        coloredBars(ContextCompat.getColor(getActivity(), R.color.colorMainDark), ContextCompat.getColor(getActivity(), R.color.colorMainPrimary));

        // Analytics
        Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
        t.setScreenName("Anasayfa");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GridView gridView = (GridView) v.findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(getActivity()));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("burcid", position);
                if (premium) {
                    startActivity(intent);
                } else {
                    showAds();
                }
            }
        });

        return v;
    }

    public void showAds() {
        Random generator = new Random();
        int random = generator.nextInt(3);
        if (MainActivity.interstitial != null) {
            if (MainActivity.interstitial.isLoaded() && random == 1) {
                startActivity(intent);
                MainActivity.interstitial.show();
            } else {
                startActivity(intent);
            }
        } else {
            startActivity(intent);
        }
    }

    public void coloredBars(int color1, int color2) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color1);
            actionbar.setBackgroundDrawable(new ColorDrawable(color2));
        } else {
            actionbar.setBackgroundDrawable(new ColorDrawable(color2));
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}