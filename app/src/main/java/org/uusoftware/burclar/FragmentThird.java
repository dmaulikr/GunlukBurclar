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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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
    AdView adView;
    TextView txt;
    ImageView imagebutton;
    Spinner spinner1;

    String[] strings = {"21 Mart–19 Nisan", "20 Nisan–20 Mayıs", "21 Mayıs–21 Haziran",
            "22 Haziran–22 Temmuz", "23 Temmuz–22 Ağustos", "23 Ağustos–22 Eylül",
            "23 Eylül–22 Ekim", "23 Ekim–21 Kasım", "23 Kasım–21 Aralık",
            "22 Aralık–19 Ocak", "20 Ocak–18 Şubat", "19 Şubat–20 Mart"};
    String[] strings2 = {"00:00–02:00", "02:00–04:00", "04:00–06:00", "06:00–08:00", "08:00–10:00", "10:00–12:00",
            "12:00–14:00", "14:00–16:00", "16:00–18:00", "18:00–20:00", "20:00–22:00", "22:00–00:00",};
    int images[] = {R.drawable.burc_koc, R.drawable.burc_boga, R.drawable.burc_ikizler, R.drawable.burc_yengec,
            R.drawable.burc_aslan, R.drawable.burc_basak, R.drawable.burc_terazi, R.drawable.burc_akrep,
            R.drawable.burc_yay, R.drawable.burc_oglak, R.drawable.burc_kova, R.drawable.burc_balik};
    Intent intent;
    Calendar mcurrentTime;
    int hour, minute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_third, container, false);

        // Premium & AdMob
        boolean premium = MainActivity.premium;
        AdView adView = (AdView) rootView.findViewById(R.id.adMob);
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
                if (position == 0) {
                    burc = 0;
                } else if (position == 1) {
                    burc = 1;
                } else if (position == 2) {
                    burc = 2;
                } else if (position == 3) {
                    burc = 3;
                } else if (position == 4) {
                    burc = 4;
                } else if (position == 5) {
                    burc = 5;
                } else if (position == 6) {
                    burc = 6;
                } else if (position == 7) {
                    burc = 7;
                } else if (position == 8) {
                    burc = 8;
                } else if (position == 9) {
                    burc = 9;
                } else if (position == 10) {
                    burc = 10;
                } else {
                    burc = 11;
                }

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
        // Koç
        if (burc == 0 && dogumsaati == 0) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 0 && dogumsaati == 1) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 0 && dogumsaati == 2) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 0 && dogumsaati == 3) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 0 && dogumsaati == 4) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 0 && dogumsaati == 5) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 0 && dogumsaati == 6) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 0 && dogumsaati == 7) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 0 && dogumsaati == 8) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 0 && dogumsaati == 9) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 0 && dogumsaati == 10) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 0 && dogumsaati == 11) {
            intent.putExtra("burcid", 9);
        }

        // Boğa
        if (burc == 1 && dogumsaati == 0) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 1 && dogumsaati == 1) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 1 && dogumsaati == 2) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 1 && dogumsaati == 3) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 1 && dogumsaati == 4) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 1 && dogumsaati == 5) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 1 && dogumsaati == 6) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 1 && dogumsaati == 7) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 1 && dogumsaati == 8) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 1 && dogumsaati == 9) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 1 && dogumsaati == 10) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 1 && dogumsaati == 11) {
            intent.putExtra("burcid", 10);
        }

        // İkizler
        if (burc == 2 && dogumsaati == 0) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 2 && dogumsaati == 1) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 2 && dogumsaati == 2) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 2 && dogumsaati == 3) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 2 && dogumsaati == 4) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 2 && dogumsaati == 5) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 2 && dogumsaati == 6) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 2 && dogumsaati == 7) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 2 && dogumsaati == 8) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 2 && dogumsaati == 9) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 2 && dogumsaati == 10) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 2 && dogumsaati == 11) {
            intent.putExtra("burcid", 11);
        }

        // Yengeç
        if (burc == 3 && dogumsaati == 0) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 3 && dogumsaati == 1) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 3 && dogumsaati == 2) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 3 && dogumsaati == 3) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 3 && dogumsaati == 4) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 3 && dogumsaati == 5) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 3 && dogumsaati == 6) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 3 && dogumsaati == 7) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 3 && dogumsaati == 8) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 3 && dogumsaati == 9) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 2 && dogumsaati == 10) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 3 && dogumsaati == 11) {
            intent.putExtra("burcid", 0);
        }

        // Aslan
        if (burc == 4 && dogumsaati == 0) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 4 && dogumsaati == 1) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 4 && dogumsaati == 2) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 4 && dogumsaati == 3) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 4 && dogumsaati == 4) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 4 && dogumsaati == 5) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 4 && dogumsaati == 6) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 4 && dogumsaati == 7) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 4 && dogumsaati == 8) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 4 && dogumsaati == 9) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 4 && dogumsaati == 10) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 4 && dogumsaati == 11) {
            intent.putExtra("burcid", 1);
        }

        // Başak
        if (burc == 5 && dogumsaati == 0) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 5 && dogumsaati == 1) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 5 && dogumsaati == 2) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 5 && dogumsaati == 3) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 5 && dogumsaati == 4) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 5 && dogumsaati == 5) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 5 && dogumsaati == 6) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 5 && dogumsaati == 7) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 5 && dogumsaati == 8) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 5 && dogumsaati == 9) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 5 && dogumsaati == 10) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 5 && dogumsaati == 11) {
            intent.putExtra("burcid", 2);
        }

        // Terazi
        if (burc == 6 && dogumsaati == 0) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 6 && dogumsaati == 1) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 6 && dogumsaati == 2) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 6 && dogumsaati == 3) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 6 && dogumsaati == 4) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 6 && dogumsaati == 5) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 6 && dogumsaati == 6) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 6 && dogumsaati == 7) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 6 && dogumsaati == 8) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 6 && dogumsaati == 9) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 6 && dogumsaati == 10) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 6 && dogumsaati == 11) {
            intent.putExtra("burcid", 3);
        }

        // Akrep
        if (burc == 7 && dogumsaati == 0) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 7 && dogumsaati == 1) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 7 && dogumsaati == 2) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 7 && dogumsaati == 3) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 7 && dogumsaati == 4) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 7 && dogumsaati == 5) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 7 && dogumsaati == 6) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 7 && dogumsaati == 7) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 7 && dogumsaati == 8) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 7 && dogumsaati == 9) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 7 && dogumsaati == 10) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 7 && dogumsaati == 11) {
            intent.putExtra("burcid", 4);
        }

        // Yay
        if (burc == 8 && dogumsaati == 0) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 8 && dogumsaati == 1) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 8 && dogumsaati == 2) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 8 && dogumsaati == 3) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 8 && dogumsaati == 4) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 8 && dogumsaati == 5) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 8 && dogumsaati == 6) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 8 && dogumsaati == 7) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 8 && dogumsaati == 8) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 8 && dogumsaati == 9) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 8 && dogumsaati == 10) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 8 && dogumsaati == 11) {
            intent.putExtra("burcid", 5);
        }

        // Oğlak
        if (burc == 9 && dogumsaati == 0) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 9 && dogumsaati == 1) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 9 && dogumsaati == 2) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 9 && dogumsaati == 3) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 9 && dogumsaati == 4) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 9 && dogumsaati == 5) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 9 && dogumsaati == 6) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 9 && dogumsaati == 7) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 9 && dogumsaati == 8) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 9 && dogumsaati == 9) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 9 && dogumsaati == 10) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 9 && dogumsaati == 11) {
            intent.putExtra("burcid", 6);
        }

        // Kova
        if (burc == 10 && dogumsaati == 0) {
            intent.putExtra("burcid", 8);
        }
        if (burc == 10 && dogumsaati == 1) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 10 && dogumsaati == 2) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 10 && dogumsaati == 3) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 10 && dogumsaati == 4) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 10 && dogumsaati == 5) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 10 && dogumsaati == 6) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 10 && dogumsaati == 7) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 10 && dogumsaati == 8) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 10 && dogumsaati == 9) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 10 && dogumsaati == 10) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 10 && dogumsaati == 11) {
            intent.putExtra("burcid", 7);
        }

        // Balık
        if (burc == 11 && dogumsaati == 0) {
            intent.putExtra("burcid", 9);
        }
        if (burc == 11 && dogumsaati == 1) {
            intent.putExtra("burcid", 10);
        }
        if (burc == 11 && dogumsaati == 2) {
            intent.putExtra("burcid", 11);
        }
        if (burc == 11 && dogumsaati == 3) {
            intent.putExtra("burcid", 0);
        }
        if (burc == 11 && dogumsaati == 4) {
            intent.putExtra("burcid", 1);
        }
        if (burc == 11 && dogumsaati == 5) {
            intent.putExtra("burcid", 2);
        }
        if (burc == 11 && dogumsaati == 6) {
            intent.putExtra("burcid", 3);
        }
        if (burc == 11 && dogumsaati == 7) {
            intent.putExtra("burcid", 4);
        }
        if (burc == 11 && dogumsaati == 8) {
            intent.putExtra("burcid", 5);
        }
        if (burc == 11 && dogumsaati == 9) {
            intent.putExtra("burcid", 6);
        }
        if (burc == 11 && dogumsaati == 10) {
            intent.putExtra("burcid", 7);
        }
        if (burc == 11 && dogumsaati == 11) {
            intent.putExtra("burcid", 8);
        }
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