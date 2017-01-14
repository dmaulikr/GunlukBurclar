package org.uusoftware.burclar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class FragmentHome extends Fragment {

    public static Intent intent;
    Tracker t;
    boolean premium;

    //Facebook Audience Network
    private com.facebook.ads.AdView adView;

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
            AdSettings.addTestDevice("90ff5bfeac54391d98cc2bb9ff05ebb7");
            adViewContainer.addView(adView);
            adView.loadAd();
        }

        // Analytics
        t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
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
                        intent.putExtra("burcid", 0);
                        break;
                    case R.id.imageView2:
                        intent.putExtra("burcid", 1);
                        break;
                    case R.id.imageView3:
                        intent.putExtra("burcid", 2);
                        break;
                    case R.id.imageView4:
                        intent.putExtra("burcid", 3);
                        break;
                    case R.id.imageView5:
                        intent.putExtra("burcid", 4);
                        break;
                    case R.id.imageView6:
                        intent.putExtra("burcid", 5);
                        break;
                    case R.id.imageView7:
                        intent.putExtra("burcid", 6);
                        break;
                    case R.id.imageView8:
                        intent.putExtra("burcid", 7);
                        break;
                    case R.id.imageView9:
                        intent.putExtra("burcid", 8);
                        break;
                    case R.id.imageView10:
                        intent.putExtra("burcid", 9);
                        break;
                    case R.id.imageView11:
                        intent.putExtra("burcid", 10);
                        break;
                    case R.id.imageView12:
                        intent.putExtra("burcid", 11);
                        break;
                }
                if (premium) {
                    startActivity(intent);
                } else {
                    showAds();
                }
            }

            public void showAds() {
                // AdMob
                boolean displayed = MainActivity.displayed;
                boolean displayed2 = MainActivity.displayed2;
                boolean displayed3 = MainActivity.displayed3;
                boolean displayed4 = MainActivity.displayed4;
                long a = MainActivity.start;
                long b = System.currentTimeMillis();

                if (b - a >= 2500 && !displayed) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds();
                } else if (b - a >= 30000 && !displayed2) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds2();
                } else if (b - a >= 90000 && !displayed3) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds3();
                } else if (b - a >= 180000 && !displayed4) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds4();
                } else {
                    startActivity(intent);
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

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}