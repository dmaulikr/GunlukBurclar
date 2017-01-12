package org.uusoftware.burclar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class FragmentHome extends Fragment {

    Tracker t;
    int color = Color.parseColor("#000000");
    int color2 = Color.parseColor("#313131");
    Window window;
    ActionBar bar;
    public static Intent intent;
    boolean premium;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Premium & AdMob
        premium = MainActivity.premium;
        AdView adView = (AdView) v.findViewById(R.id.adMob);
        if (premium) {
            adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("0A83AF9337EAE655A7B29C5B61372D84").build();
            adView.loadAd(adRequest);
        }

        // Colored bars
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);

            bar = ((MainActivity) getActivity()).getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color2));
        } else {
            bar = ((MainActivity) getActivity()).getSupportActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color2));
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
                if (!premium) {
                    showAds();
                } else {
                    startActivity(intent);
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

                if (b - a >= 7500 && !displayed) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds();
                } else if (b - a >= 45000 && !displayed2) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds2();
                } else if (b - a >= 180000 && !displayed3) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    MainActivity.displayAds3();
                } else if (b - a >= 360000 && !displayed4) {
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
}