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
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class FragmentSecond extends Fragment {

    Window window;
    Context mContext;
    boolean premium;
    ActionBar actionbar;

    Intent intent;
    ImageView clickButton;
    ImageView imageskadin[] = new ImageView[12];
    ImageView imageserkek[] = new ImageView[12];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        // Premium & Ads
        premium = MainActivity.premium;

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
        imageskadin[0] = rootView.findViewById(R.id.kadin1);
        imageskadin[1] = rootView.findViewById(R.id.kadin2);
        imageskadin[2] = rootView.findViewById(R.id.kadin3);
        imageskadin[3] = rootView.findViewById(R.id.kadin4);
        imageskadin[4] = rootView.findViewById(R.id.kadin5);
        imageskadin[5] = rootView.findViewById(R.id.kadin6);
        imageskadin[6] = rootView.findViewById(R.id.kadin7);
        imageskadin[7] = rootView.findViewById(R.id.kadin8);
        imageskadin[8] = rootView.findViewById(R.id.kadin9);
        imageskadin[9] = rootView.findViewById(R.id.kadin10);
        imageskadin[10] = rootView.findViewById(R.id.kadin11);
        imageskadin[11] = rootView.findViewById(R.id.kadin12);

        //ImagesErkek
        imageserkek[0] = rootView.findViewById(R.id.erkek1);
        imageserkek[1] = rootView.findViewById(R.id.erkek2);
        imageserkek[2] = rootView.findViewById(R.id.erkek3);
        imageserkek[3] = rootView.findViewById(R.id.erkek4);
        imageserkek[4] = rootView.findViewById(R.id.erkek5);
        imageserkek[5] = rootView.findViewById(R.id.erkek6);
        imageserkek[6] = rootView.findViewById(R.id.erkek7);
        imageserkek[7] = rootView.findViewById(R.id.erkek8);
        imageserkek[8] = rootView.findViewById(R.id.erkek9);
        imageserkek[9] = rootView.findViewById(R.id.erkek10);
        imageserkek[10] = rootView.findViewById(R.id.erkek11);
        imageserkek[11] = rootView.findViewById(R.id.erkek12);

        //ClickButton
        clickButton = rootView.findViewById(R.id.imageViewButton);

        //onClickListener for Kadın
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
                        intent.putExtra("kadinid", "balik");
                        break;
                }

                for (int i = 0; i < 12; i++) {
                    imageskadin[i].setAlpha(0.33f);
                }

                v.setAlpha(1.0f);
                if (intent.getStringExtra("kadinid") != null && intent.getStringExtra("erkekid") != null) {
                    clickButton.setAlpha(1.0f);
                }
            }
        };

        //onClickListener for Erkek
        OnClickListener buttonListener2 = new OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.erkek1:
                        intent.putExtra("erkekid", "koc");
                        break;
                    case R.id.erkek2:
                        intent.putExtra("erkekid", "boga");
                        break;
                    case R.id.erkek3:
                        intent.putExtra("erkekid", "ikizler");
                        break;
                    case R.id.erkek4:
                        intent.putExtra("erkekid", "yengec");
                        break;
                    case R.id.erkek5:
                        intent.putExtra("erkekid", "aslan");
                        break;
                    case R.id.erkek6:
                        intent.putExtra("erkekid", "basak");
                        break;
                    case R.id.erkek7:
                        intent.putExtra("erkekid", "terazi");
                        break;
                    case R.id.erkek8:
                        intent.putExtra("erkekid", "akrep");
                        break;
                    case R.id.erkek9:
                        intent.putExtra("erkekid", "yay");
                        break;
                    case R.id.erkek10:
                        intent.putExtra("erkekid", "oglak");
                        break;
                    case R.id.erkek11:
                        intent.putExtra("erkekid", "kova");
                        break;
                    case R.id.erkek12:
                        intent.putExtra("erkekid", "balik");
                        break;
                }

                for (int i = 0; i < 12; i++) {
                    imageserkek[i].setAlpha(0.33f);
                }

                v.setAlpha(1.0f);
                if (intent.getStringExtra("kadinid") != null && intent.getStringExtra("erkekid") != null) {
                    clickButton.setAlpha(1.0f);
                }
            }
        };

        //onClickListener for screen changer button
        OnClickListener buttonListener3 = new OnClickListener() {
            public void onClick(View v) {
                if (intent.getStringExtra("kadinid") != null && intent.getStringExtra("erkekid") != null) {
                    if (premium) {
                        startActivity(intent);
                    } else {
                        showAds();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Lütfen kendinizin ve partnerinizin burcunu seçiniz.", Toast.LENGTH_SHORT).show();
                }
            }
        };


        //Set Click Listeners
        for (int i = 0; i < 12; i++) {
            imageskadin[i].setOnClickListener(buttonListener);
            imageserkek[i].setOnClickListener(buttonListener2);
        }
        clickButton.setOnClickListener(buttonListener3);

        return rootView;
    }

    public void showAds() {
        if (MainActivity.facebookInterstitial != null && MainActivity.facebookInterstitial.isAdLoaded()) {
            //Facebook ads loaded he will see Facebook
            startActivity(intent);
            MainActivity.facebookInterstitial.show();
            MainActivity.adCount++;
        } else if (MainActivity.admobInterstitial != null && MainActivity.admobInterstitial.isLoaded()) {
            //Facebook ads doesnt loaded he will see AdMob
            startActivity(intent);
            MainActivity.admobInterstitial.show();
            MainActivity.adCount++;
        } else {
            //Both ads doesn't loaded.
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
}