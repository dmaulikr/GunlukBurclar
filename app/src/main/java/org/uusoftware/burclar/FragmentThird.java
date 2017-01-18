package org.uusoftware.burclar;

import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Calendar;

public class FragmentThird extends Fragment {

    int burc, dogumsaati;
    Tracker t;
    int color = Color.parseColor("#000000");
    int color2 = Color.parseColor("#313131");
    Window window;
    ActionBar bar;
    TextView txt;
    ImageView imagebutton;
    Spinner spinner1;

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
        boolean premium = MainActivity.premium;
        if (premium) {
            //Do nothing
        } else {
            RelativeLayout adViewContainer = (RelativeLayout) rootView.findViewById(R.id.adFacebook);
            adView = new com.facebook.ads.AdView(getActivity(), "155235578298611_155235834965252", AdSize.BANNER_HEIGHT_50);
            AdSettings.addTestDevice("90ff5bfeac54391d98cc2bb9ff05ebb7");
            adViewContainer.addView(adView);
            adView.loadAd();
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
        t.setScreenName("Yükselen burç");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        // Intent
        intent = new Intent(getActivity(), YukselenBurcActivity.class);

        // spinner1
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
                startActivity(intent);
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
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 8);
            } else {
                intent.putExtra("burcid", 9);
            }
        } else if (burc == 1) {
            //BOĞA
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 9);
            } else {
                intent.putExtra("burcid", 10);
            }
        } else if (burc == 2) {
            //İKİZLER
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 10);
            } else {
                intent.putExtra("burcid", 11);
            }
        } else if (burc == 3) {
            //YENGEÇ
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 11);
            } else {
                intent.putExtra("burcid", 0);
            }
        } else if (burc == 4) {
            //ASLAN
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 0);
            } else {
                intent.putExtra("burcid", 1);
            }
        } else if (burc == 5) {
            //BAŞAK
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 1);
            } else {
                intent.putExtra("burcid", 2);
            }
        } else if (burc == 6) {
            //TERAZİ
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 2);
            } else {
                intent.putExtra("burcid", 3);
            }
        } else if (burc == 7) {
            //AKREP
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 3);
            } else {
                intent.putExtra("burcid", 4);
            }
        } else if (burc == 8) {
            //YAY
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 4);
            } else {
                intent.putExtra("burcid", 5);
            }
        } else if (burc == 9) {
            //OĞLAK
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 7);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 5);
            } else {
                intent.putExtra("burcid", 6);
            }
        } else if (burc == 10) {
            //KOVA
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 8);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 6);
            } else {
                intent.putExtra("burcid", 7);
            }
        } else {
            //BALIK
            if (dogumsaati == 0) {
                intent.putExtra("burcid", 9);
            } else if (dogumsaati == 1) {
                intent.putExtra("burcid", 10);
            } else if (dogumsaati == 2) {
                intent.putExtra("burcid", 11);
            } else if (dogumsaati == 3) {
                intent.putExtra("burcid", 0);
            } else if (dogumsaati == 4) {
                intent.putExtra("burcid", 1);
            } else if (dogumsaati == 5) {
                intent.putExtra("burcid", 2);
            } else if (dogumsaati == 6) {
                intent.putExtra("burcid", 3);
            } else if (dogumsaati == 7) {
                intent.putExtra("burcid", 4);
            } else if (dogumsaati == 8) {
                intent.putExtra("burcid", 5);
            } else if (dogumsaati == 9) {
                intent.putExtra("burcid", 6);
            } else if (dogumsaati == 10) {
                intent.putExtra("burcid", 7);
            } else {
                intent.putExtra("burcid", 8);
            }
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    public class SpinnerAdapter extends ArrayAdapter<String> {

        public SpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.spinner_row, parent, false);

            TextView text = (TextView) row.findViewById(R.id.textView1);
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
    }
}