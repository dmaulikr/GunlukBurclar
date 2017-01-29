package org.uusoftware.burclar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class FragmentSecond extends Fragment {

    Window window;
    Context mContext;
    boolean premium;
    ActionBar actionbar;
    Intent intent;
    ImageView imageskadin[] = new ImageView[12];
    //Facebook Audience Network
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        // Premium & Facebook Audience Network
        premium = MainActivity.premium;
        if (premium) {
            //Do nothing
        } else {
            RelativeLayout adViewContainer = (RelativeLayout) rootView.findViewById(R.id.adFacebook);
            adView = new com.facebook.ads.AdView(getActivity(), "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("f7b438ca481bd95179a45b4b10ea9a7a");
            adViewContainer.addView(adView);
            adView.loadAd();
        }

        /* Colored bars */
        mContext = getActivity().getApplicationContext();
        window = getActivity().getWindow();
        actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        coloredBars(ContextCompat.getColor(mContext, R.color.colorMainDark), ContextCompat.getColor(mContext, R.color.colorMainPrimary));

        // Analytics
        Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
        t.setScreenName("Burç uyumu");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        //Intent
        intent = new Intent(getActivity(), BurcUyumuActivity.class);

        //ImagesKadın
        imageskadin[0] = (ImageView) rootView.findViewById(R.id.kadin1);
        imageskadin[1] = (ImageView) rootView.findViewById(R.id.kadin2);
        imageskadin[2] = (ImageView) rootView.findViewById(R.id.kadin3);
        imageskadin[3] = (ImageView) rootView.findViewById(R.id.kadin4);
        imageskadin[4] = (ImageView) rootView.findViewById(R.id.kadin5);
        imageskadin[5] = (ImageView) rootView.findViewById(R.id.kadin6);
        imageskadin[6] = (ImageView) rootView.findViewById(R.id.kadin7);
        imageskadin[7] = (ImageView) rootView.findViewById(R.id.kadin8);
        imageskadin[8] = (ImageView) rootView.findViewById(R.id.kadin9);
        imageskadin[9] = (ImageView) rootView.findViewById(R.id.kadin10);
        imageskadin[10] = (ImageView) rootView.findViewById(R.id.kadin11);
        imageskadin[11] = (ImageView) rootView.findViewById(R.id.kadin12);

        OnClickListener buttonListener = new OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.kadin1:
                        intent.putExtra("kadinid", "koc");
                        break;
                    case R.id.kadin2:
                        intent.putExtra("kadinid", "boga");
                        break;
                    case R.id.kadin3:
                        intent.putExtra("kadinid", "ikizler");
                        break;
                    case R.id.kadin4:
                        intent.putExtra("kadinid", "yengec");
                        break;
                    case R.id.kadin5:
                        intent.putExtra("kadinid", "aslan");
                        break;
                    case R.id.kadin6:
                        intent.putExtra("kadinid", "basak");
                        break;
                    case R.id.kadin7:
                        intent.putExtra("kadinid", "terazi");
                        break;
                    case R.id.kadin8:
                        intent.putExtra("kadinid", "akrep");
                        break;
                    case R.id.kadin9:
                        intent.putExtra("kadinid", "yay");
                        break;
                    case R.id.kadin10:
                        intent.putExtra("kadinid", "oglak");
                        break;
                    case R.id.kadin11:
                        intent.putExtra("kadinid", "kova");
                        break;
                    case R.id.kadin12:
                        intent.putExtra("burcid", "balik");
                        break;
                }

                for (int i = 0; i < 12; i++) {
                    imageskadin[i].setAlpha(1.0f);
                }

                v.setAlpha(0.5f);
            }
        };

        for (int i = 0; i < 12; i++) {
            imageskadin[i].setOnClickListener(buttonListener);
        }

        return rootView;
    }

    public void showAds() {
        if (MainActivity.interstitial != null) {
            if (MainActivity.interstitial.isLoaded()) {
                startActivity(intent);
                MainActivity.interstitial.show();
            } else {
                startActivity(intent);
            }
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
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
}