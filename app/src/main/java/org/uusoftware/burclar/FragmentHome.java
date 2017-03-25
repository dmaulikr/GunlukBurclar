package org.uusoftware.burclar;

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
        RelativeLayout adViewContainer = (RelativeLayout) v.findViewById(R.id.adFacebook);
        if (premium) {
            adViewContainer.setVisibility(View.GONE);
        } else {
            adView = new com.facebook.ads.AdView(getActivity(), "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("bfe5e795d34fe79746ff9fa33c0ee5ed");
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

        ImageView img = (ImageView) v.findViewById(R.id.imageView1);
        ImageView img2 = (ImageView) v.findViewById(R.id.imageView2);
        ImageView img3 = (ImageView) v.findViewById(R.id.imageView3);
        ImageView img4 = (ImageView) v.findViewById(R.id.imageView4);
        ImageView img5 = (ImageView) v.findViewById(R.id.imageView5);
        ImageView img6 = (ImageView) v.findViewById(R.id.imageView6);
        ImageView img7 = (ImageView) v.findViewById(R.id.imageView7);
        ImageView img8 = (ImageView) v.findViewById(R.id.imageView8);
        ImageView img9 = (ImageView) v.findViewById(R.id.imageView9);
        ImageView img10 = (ImageView) v.findViewById(R.id.imageView10);
        ImageView img11 = (ImageView) v.findViewById(R.id.imageView11);
        ImageView img12 = (ImageView) v.findViewById(R.id.imageView12);

        OnClickListener buttonListener = new OnClickListener() {

            public void onClick(View v) {
                intent = new Intent(getActivity(), SecondActivity.class);
                switch (v.getId()) {
                    case R.id.imageView1:
                        intent.putExtra("burcid", "koc");
                        break;
                    case R.id.imageView2:
                        intent.putExtra("burcid", "boga");
                        break;
                    case R.id.imageView3:
                        intent.putExtra("burcid", "ikizler");
                        break;
                    case R.id.imageView4:
                        intent.putExtra("burcid", "yengec");
                        break;
                    case R.id.imageView5:
                        intent.putExtra("burcid", "aslan");
                        break;
                    case R.id.imageView6:
                        intent.putExtra("burcid", "basak");
                        break;
                    case R.id.imageView7:
                        intent.putExtra("burcid", "terazi");
                        break;
                    case R.id.imageView8:
                        intent.putExtra("burcid", "akrep");
                        break;
                    case R.id.imageView9:
                        intent.putExtra("burcid", "yay");
                        break;
                    case R.id.imageView10:
                        intent.putExtra("burcid", "oglak");
                        break;
                    case R.id.imageView11:
                        intent.putExtra("burcid", "kova");
                        break;
                    case R.id.imageView12:
                        intent.putExtra("burcid", "balik");
                        break;
                }
                if (premium) {
                    startActivity(intent);
                } else {
                    showAds();
                }
            }
        };

        img.setOnClickListener(buttonListener);
        img2.setOnClickListener(buttonListener);
        img3.setOnClickListener(buttonListener);
        img4.setOnClickListener(buttonListener);
        img5.setOnClickListener(buttonListener);
        img6.setOnClickListener(buttonListener);
        img7.setOnClickListener(buttonListener);
        img8.setOnClickListener(buttonListener);
        img9.setOnClickListener(buttonListener);
        img10.setOnClickListener(buttonListener);
        img11.setOnClickListener(buttonListener);
        img12.setOnClickListener(buttonListener);

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