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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Calendar;

public class FragmentThird extends Fragment {

    int burc, dogumsaati;
    Window window;
    ActionBar actionbar;
    Context mContext;
    TextView txt;
    ImageView imagebutton;
    Spinner spinner1;
    boolean premium;

    String[] strings = {"21 Mart–19 Nisan", "20 Nisan–20 Mayıs", "21 Mayıs–21 Haziran",
            "22 Haziran–22 Temmuz", "23 Temmuz–22 Ağustos", "23 Ağustos–22 Eylül",
            "23 Eylül–22 Ekim", "23 Ekim–21 Kasım", "23 Kasım–21 Aralık",
            "22 Aralık–19 Ocak", "20 Ocak–18 Şubat", "19 Şubat–20 Mart"};
    int images[] = {R.drawable.burc_koc, R.drawable.burc_boga, R.drawable.burc_ikizler, R.drawable.burc_yengec,
            R.drawable.burc_aslan, R.drawable.burc_basak, R.drawable.burc_terazi, R.drawable.burc_akrep,
            R.drawable.burc_yay, R.drawable.burc_oglak, R.drawable.burc_kova, R.drawable.burc_balik};
    Intent intent;
    Calendar mcurrentTime;
    int hour, minute;

    //Facebook Audience Network
    private AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        // Premium & Facebook Audience Network
        premium = MainActivity.premium;
        if (premium) {
            //Do nothing
        } else {
            RelativeLayout adViewContainer = (RelativeLayout) rootView.findViewById(R.id.adFacebook);
            adView = new com.facebook.ads.AdView(getActivity(), "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("25100dd41a6642a625d348086dbd18bb");
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
        t.setScreenName("Yükselen burç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        // Intent
        intent = new Intent(getActivity(), YukselenBurcActivity.class);

      /*  // spinner1
        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
        spinner1.setAdapter(new SpinnerAdapter(getActivity(), R.layout.spinner_row, strings));
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long lng) {
                burc = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        // EditText for time
        txt = (TextView) rootView.findViewById(R.id.textView2);
        mcurrentTime = Calendar.getInstance();
        hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        minute = mcurrentTime.get(Calendar.MINUTE);
        txt.setText(pad(hour) + ":" + pad(minute));
        txt.setOnClickListener(new OnClickListener() {
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

        imagebutton = (ImageView) rootView.findViewById(R.id.imageView1);
        imagebutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
                if (premium) {
                    startActivity(intent);
                } else {
                    showAds();
                }
            }
        });
*/
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
        if (MainActivity.interstitial2 != null) {
            if (MainActivity.interstitial2.isLoaded()) {
                MainActivity.interstitial2.show();
                startActivity(intent);
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

   /* public class SpinnerAdapter extends ArrayAdapter<String> {

        public SpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.spinner_row, parent, false);

            TextView text = (TextView) row.findViewById(R.id.textView2);
            text.setText(strings[position]);

            ImageView icon = (ImageView) row.findViewById(R.id.imageView1);
            icon.setImageResource(images[position]);
            return row;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row2 = inflater.inflate(R.layout.spinner_dropdown, parent, false);

            TextView text2 = (TextView) row2.findViewById(R.id.textView2);
            text2.setText(strings[position]);

            ImageView icon2 = (ImageView) row2.findViewById(R.id.imageView2);
            icon2.setImageResource(images[position]);

            return row2;
        }
    }*/
}