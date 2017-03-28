package org.uusoftware.burclar;

import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Calendar;
import java.util.Random;

public class FragmentThird extends Fragment {

    int burc, dogumsaati;
    Window window;
    ActionBar actionbar;
    Context mContext;
    ImageView imagebutton;
    boolean premium;
    ImageView imagesburc[] = new ImageView[12];
    Intent intent;
    int hour, minute;
    TextView txt;
    Calendar mcurrentTime;

    //Facebook Audience Network
    RelativeLayout adViewContainer;
    private AdView bannerFacebook;
    private com.google.android.gms.ads.AdView bannerAdmob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        // Premium & Ads
        premium = MainActivity.premium;

        adViewContainer = (RelativeLayout) rootView.findViewById(R.id.adFacebook);
        bannerAdmob = (com.google.android.gms.ads.AdView) rootView.findViewById(R.id.adView);

        if (premium) {
            adViewContainer.setVisibility(View.GONE);
            bannerAdmob.setVisibility(View.GONE);
        } else {
            bannerFacebook = new AdView(getActivity(), getString(R.string.banner_facebook), AdSize.BANNER_HEIGHT_50);
            adViewContainer.addView(bannerFacebook);
            bannerFacebook.setAdListener(new AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    Toast.makeText(getActivity(), "Feys yüklenemedi", Toast.LENGTH_SHORT).show();
                    adViewContainer.setVisibility(View.GONE);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    bannerAdmob.loadAd(adRequest);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Ad loaded callback
                    bannerAdmob.setVisibility(View.GONE);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                }
            });
            bannerFacebook.loadAd();
        }

        /* Colored bars */
        mContext = getActivity().getApplicationContext();
        window = getActivity().getWindow();
        actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        coloredBars(ContextCompat.getColor(mContext, R.color.colorMainDark), ContextCompat.getColor(mContext, R.color.colorMainPrimary));

        // Analytics
        Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
        t.setScreenName("Yükselen burç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        // Intent
        intent = new Intent(getActivity(), YukselenBurcActivity.class);

        // ChooseBurc
        imagesburc[0] = (ImageView) rootView.findViewById(R.id.burc1);
        imagesburc[1] = (ImageView) rootView.findViewById(R.id.burc2);
        imagesburc[2] = (ImageView) rootView.findViewById(R.id.burc3);
        imagesburc[3] = (ImageView) rootView.findViewById(R.id.burc4);
        imagesburc[4] = (ImageView) rootView.findViewById(R.id.burc5);
        imagesburc[5] = (ImageView) rootView.findViewById(R.id.burc6);
        imagesburc[6] = (ImageView) rootView.findViewById(R.id.burc7);
        imagesburc[7] = (ImageView) rootView.findViewById(R.id.burc8);
        imagesburc[8] = (ImageView) rootView.findViewById(R.id.burc9);
        imagesburc[9] = (ImageView) rootView.findViewById(R.id.burc10);
        imagesburc[10] = (ImageView) rootView.findViewById(R.id.burc11);
        imagesburc[11] = (ImageView) rootView.findViewById(R.id.burc12);

        //onClickListener for Burc
        View.OnClickListener buttonListener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.burc1:
                        burc = 0;
                        break;
                    case R.id.burc2:
                        burc = 1;
                        break;
                    case R.id.burc3:
                        burc = 2;
                        break;
                    case R.id.burc4:
                        burc = 3;
                        break;
                    case R.id.burc5:
                        burc = 4;
                        break;
                    case R.id.burc6:
                        burc = 5;
                        break;
                    case R.id.burc7:
                        burc = 6;
                        break;
                    case R.id.burc8:
                        burc = 7;
                        break;
                    case R.id.burc9:
                        burc = 8;
                        break;
                    case R.id.burc10:
                        burc = 9;
                        break;
                    case R.id.burc11:
                        burc = 10;
                        break;
                    case R.id.burc12:
                        burc = 11;
                        break;
                }

                for (int i = 0; i < 12; i++) {
                    imagesburc[i].setAlpha(0.33f);
                }

                v.setAlpha(1.0f);
                imagebutton.setAlpha(1.0f);
            }
        };

        // ChooseTime
        txt = (TextView) rootView.findViewById(R.id.textClock);
        mcurrentTime = Calendar.getInstance();
        hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        minute = mcurrentTime.get(Calendar.MINUTE);
        txt.setText(pad(hour) + ":" + pad(minute));
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        if (selectedHour < 2) {
                            dogumsaati = 0;
                        } else if (selectedHour < 4) {
                            dogumsaati = 1;
                        } else if (selectedHour < 6) {
                            dogumsaati = 2;
                        } else if (selectedHour < 8) {
                            dogumsaati = 3;
                        } else if (selectedHour < 10) {
                            dogumsaati = 4;
                        } else if (selectedHour < 12) {
                            dogumsaati = 5;
                        } else if (selectedHour < 14) {
                            dogumsaati = 6;
                        } else if (selectedHour < 16) {
                            dogumsaati = 7;
                        } else if (selectedHour < 18) {
                            dogumsaati = 8;
                        } else if (selectedHour < 20) {
                            dogumsaati = 9;
                        } else if (selectedHour < 22) {
                            dogumsaati = 10;
                        } else {
                            dogumsaati = 11;
                        }
                        txt.setText(pad(selectedHour) + ":" + pad(selectedMinute));
                    }
                }, hour, minute, true);// Yes 24 hour time
                mTimePicker.setTitle("Doğum saatinizi seçin");
                mTimePicker.show();
            }
        });

        //Set Click Listeners
        for (int i = 0; i < 12; i++) {
            imagesburc[i].setOnClickListener(buttonListener);
        }

        imagebutton = (ImageView) rootView.findViewById(R.id.imageViewButton);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
                if (imagebutton.getAlpha() == 1.0f) {
                    if (premium) {
                        startActivity(intent);
                    } else {
                        showAds();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Lütfen burcunuzu ve doğum saatini giriniz...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

    public String pad(int input) {
        if (input >= 10) {
            return String.valueOf(input);
        } else {
            return "0" + String.valueOf(input);
        }
    }

    public void sendIntent() {
        if (burc == 0) {
            //KOÇ
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "yay");
            } else {
                intent.putExtra("burcid", "oglak");
            }
        } else if (burc == 1) {
            //BOĞA
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "oglak");
            } else {
                intent.putExtra("burcid", "kova");
            }
        } else if (burc == 2) {
            //İKİZLER
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "kova");
            } else {
                intent.putExtra("burcid", "balik");
            }
        } else if (burc == 3) {
            //YENGEÇ
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "balik");
            } else {
                intent.putExtra("burcid", "koc");
            }
        } else if (burc == 4) {
            //ASLAN
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "koc");
            } else {
                intent.putExtra("burcid", "boga");
            }
        } else if (burc == 5) {
            //BAŞAK
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "boga");
            } else {
                intent.putExtra("burcid", "ikizler");
            }
        } else if (burc == 6) {
            //TERAZİ
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "ikizler");
            } else {
                intent.putExtra("burcid", "yengec");
            }
        } else if (burc == 7) {
            //AKREP
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "yengec");
            } else {
                intent.putExtra("burcid", "aslan");
            }
        } else if (burc == 8) {
            //YAY
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "aslan");
            } else {
                intent.putExtra("burcid", "basak");
            }
        } else if (burc == 9) {
            //OĞLAK
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "akrep");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "basak");
            } else {
                intent.putExtra("burcid", "terazi");
            }
        } else if (burc == 10) {
            //KOVA
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "yay");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "terazi");
            } else {
                intent.putExtra("burcid", "akrep");
            }
        } else {
            //BALIK
            if (dogumsaati == 0) {
                intent.putExtra("burcid", "oglak");
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", "kova");
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", "balik");
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", "koc");
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", "boga");
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", "ikizler");
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", "yengec");
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", "aslan");
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", "basak");
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", "terazi");
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", "akrep");
            } else {
                intent.putExtra("burcid", "yay");
            }
        }
    }

    public void showAds() {
        Random generator = new Random();
        int random = generator.nextInt(2);
        if (MainActivity.interstitial2 != null) {
            if (MainActivity.interstitial2.isLoaded() && random == 1) {
                startActivity(intent);
                MainActivity.interstitial2.show();
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
        if (bannerFacebook != null) {
            bannerFacebook.destroy();
        }
        if (bannerAdmob != null) {
            bannerAdmob.destroy();
        }
        super.onDestroy();
    }
}