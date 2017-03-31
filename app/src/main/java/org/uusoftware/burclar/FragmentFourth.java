package org.uusoftware.burclar;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Calendar;
import java.util.Date;

public class FragmentFourth extends Fragment {

    Window window;
    ActionBar actionbar;
    Intent intent;
    boolean premium;

    //Facebook Audience Network
    RelativeLayout bannerLayout;
    RelativeLayout adViewContainer;
    private AdView bannerFacebook;
    private com.google.android.gms.ads.AdView bannerAdmob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);

        // Premium & Ads
        premium = MainActivity.premium;

        bannerLayout = (RelativeLayout) rootView.findViewById(R.id.bannerLayout);
        adViewContainer = (RelativeLayout) rootView.findViewById(R.id.adFacebook);
        bannerAdmob = (com.google.android.gms.ads.AdView) rootView.findViewById(R.id.adView);

        if (premium) {
            bannerLayout.setVisibility(View.GONE);
            adViewContainer.setVisibility(View.GONE);
            bannerAdmob.setVisibility(View.GONE);
        } else {
            bannerFacebook = new AdView(getActivity(), getString(R.string.banner_facebook), AdSize.BANNER_HEIGHT_50);
            adViewContainer.addView(bannerFacebook);
            bannerFacebook.setAdListener(new AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
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
        window = getActivity().getWindow();
        actionbar = ((MainActivity) getActivity()).getSupportActionBar();
        coloredBars(ContextCompat.getColor(getActivity(), R.color.colorMainDark), ContextCompat.getColor(getActivity(), R.color.colorMainPrimary));

        // Analytics
        Tracker t = ((AnalyticsApplication) getActivity().getApplication()).getDefaultTracker();
        t.setScreenName("Çin astrolojisi");
        t.enableAdvertisingIdCollection(true);
        t.send(new HitBuilders.ScreenViewBuilder().build());

        intent = new Intent(getActivity(), CinAstrolojisiActivity.class);
        ImageView imagebutton = (ImageView) rootView.findViewById(R.id.imageViewButton);
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Date date = new Date(year, monthOfYear, dayOfMonth);
                        findChineseHoroscope(date);
                        if (premium) {
                            startActivity(intent);
                        } else {
                            showAds();
                        }
                    }
                }, mcurrentTime.get(Calendar.YEAR), mcurrentTime.get(Calendar.MONTH),
                        mcurrentTime.get(Calendar.DAY_OF_MONTH));
                datePicker.setTitle("Tarih Seçiniz");
                datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", datePicker);
                datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);
                datePicker.show();
            }
        });

        return rootView;
    }

    public void findChineseHoroscope(Date date) {
        Date date1925b = new Date(1924, 1, 5);
        Date date2020a = new Date(2020, 0, 24);

        Date date1925 = new Date(1925, 0, 23);
        Date date1926 = new Date(1926, 1, 12);
        Date date1927 = new Date(1927, 1, 1);
        Date date1928 = new Date(1928, 0, 22);
        Date date1929 = new Date(1929, 1, 9);
        Date date1930 = new Date(1930, 0, 29);
        Date date1931 = new Date(1931, 1, 16);
        Date date1932 = new Date(1932, 1, 5);
        Date date1933 = new Date(1933, 0, 25);
        Date date1934 = new Date(1934, 1, 13);
        Date date1935 = new Date(1935, 1, 3);
        Date date1936 = new Date(1936, 0, 23);

        Date date1937 = new Date(1937, 1, 10);
        Date date1938 = new Date(1938, 0, 30);
        Date date1939 = new Date(1939, 1, 18);
        Date date1940 = new Date(1940, 1, 7);
        Date date1941 = new Date(1941, 0, 26);
        Date date1942 = new Date(1942, 1, 14);
        Date date1943 = new Date(1943, 1, 4);
        Date date1944 = new Date(1944, 0, 24);
        Date date1945 = new Date(1945, 1, 12);
        Date date1946 = new Date(1946, 1, 1);
        Date date1947 = new Date(1947, 0, 21);
        Date date1948 = new Date(1948, 1, 9);

        Date date1949 = new Date(1949, 0, 28);
        Date date1950 = new Date(1950, 1, 16);
        Date date1951 = new Date(1951, 1, 5);
        Date date1952 = new Date(1952, 0, 26);
        Date date1953 = new Date(1953, 1, 13);
        Date date1954 = new Date(1954, 1, 2);
        Date date1955 = new Date(1955, 0, 23);
        Date date1956 = new Date(1956, 1, 11);
        Date date1957 = new Date(1957, 0, 30);
        Date date1958 = new Date(1958, 1, 17);
        Date date1959 = new Date(1959, 1, 7);
        Date date1960 = new Date(1960, 0, 27);

        Date date1961 = new Date(1961, 1, 14);
        Date date1962 = new Date(1962, 1, 4);
        Date date1963 = new Date(1963, 0, 24);
        Date date1964 = new Date(1964, 1, 12);
        Date date1965 = new Date(1965, 1, 1);
        Date date1966 = new Date(1966, 0, 20);
        Date date1967 = new Date(1967, 1, 8);
        Date date1968 = new Date(1968, 0, 29);
        Date date1969 = new Date(1969, 1, 16);
        Date date1970 = new Date(1970, 1, 5);
        Date date1971 = new Date(1971, 0, 26);
        Date date1972 = new Date(1972, 1, 14);

        Date date1973 = new Date(1973, 1, 2);
        Date date1974 = new Date(1974, 0, 22);
        Date date1975 = new Date(1975, 1, 10);
        Date date1976 = new Date(1976, 0, 30);
        Date date1977 = new Date(1977, 1, 17);
        Date date1978 = new Date(1978, 1, 6);
        Date date1979 = new Date(1979, 0, 27);
        Date date1980 = new Date(1980, 1, 15);
        Date date1981 = new Date(1981, 1, 4);
        Date date1982 = new Date(1982, 0, 24);
        Date date1983 = new Date(1983, 1, 12);
        Date date1984 = new Date(1984, 1, 1);

        Date date1985 = new Date(1985, 1, 19);
        Date date1986 = new Date(1986, 1, 8);
        Date date1987 = new Date(1987, 0, 28);
        Date date1988 = new Date(1988, 1, 16);
        Date date1989 = new Date(1989, 1, 5);
        Date date1990 = new Date(1990, 0, 26);
        Date date1991 = new Date(1991, 1, 14);
        Date date1992 = new Date(1992, 1, 3);
        Date date1993 = new Date(1993, 0, 22);
        Date date1994 = new Date(1994, 1, 9);
        Date date1995 = new Date(1995, 0, 30);
        Date date1996 = new Date(1996, 1, 18);

        Date date1997 = new Date(1997, 1, 6);
        Date date1998 = new Date(1998, 0, 27);
        Date date1999 = new Date(1999, 1, 15);
        Date date2000 = new Date(2000, 1, 4);
        Date date2001 = new Date(2001, 0, 23);
        Date date2002 = new Date(2002, 1, 11);
        Date date2003 = new Date(2003, 0, 31);
        Date date2004 = new Date(2004, 0, 21);
        Date date2005 = new Date(2005, 1, 8);
        Date date2006 = new Date(2006, 0, 28);
        Date date2007 = new Date(2007, 1, 17);
        Date date2008 = new Date(2008, 1, 6);

        Date date2009 = new Date(2009, 0, 25);
        Date date2010 = new Date(2010, 1, 13);
        Date date2011 = new Date(2011, 1, 2);
        Date date2012 = new Date(2012, 0, 22);
        Date date2013 = new Date(2013, 1, 9);
        Date date2014 = new Date(2014, 0, 30);
        Date date2015 = new Date(2015, 1, 18);
        Date date2016 = new Date(2016, 1, 7);
        Date date2017 = new Date(2017, 0, 27);
        Date date2018 = new Date(2018, 1, 15);
        Date date2019 = new Date(2019, 1, 4);
        Date date2020 = new Date(2020, 0, 24);

        // 5 Şubat 1924'ten küçük tarihler için
        if (date.before(date1925b)) {
            intent.putExtra("burc", "noburc");
        }
        // Döngü 1
        else if (date.before(date1925)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1926)) {
            intent.putExtra("burc", "oküz");
        } else if (date.before(date1927)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date1928)) {
            intent.putExtra("burc", "tavşan");
        } else if (date.before(date1929)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date1930)) {
            intent.putExtra("burc", "eılan");
        } else if (date.before(date1931)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date1932)) {
            intent.putExtra("burc", "keçi");
        } else if (date.before(date1933)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date1934)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date1935)) {
            intent.putExtra("burc", "köpek");
        } else if (date.before(date1936)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 2
        else if (date.before(date1937)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1938)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date1939)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date1940)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date1941)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date1942)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date1943)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date1944)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date1945)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date1946)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date1947)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date1948)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 3
        else if (date.before(date1949)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1950)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date1951)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date1952)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date1953)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date1954)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date1955)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date1956)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date1957)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date1958)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date1959)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date1960)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 4
        else if (date.before(date1961)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1962)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date1963)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date1964)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date1965)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date1966)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date1967)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date1968)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date1969)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date1970)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date1971)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date1972)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 5
        else if (date.before(date1973)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1974)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date1975)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date1976)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date1977)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date1978)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date1979)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date1980)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date1981)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date1982)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date1983)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date1984)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 6
        else if (date.before(date1985)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1986)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date1987)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date1988)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date1989)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date1990)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date1991)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date1992)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date1993)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date1994)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date1995)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date1996)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 7
        else if (date.before(date1997)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date1998)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date1999)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date2000)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date2001)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date2002)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date2003)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date2004)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date2005)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date2006)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date2007)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date2008)) {
            intent.putExtra("burc", "domuz");
        }

        // Döngü 7
        else if (date.before(date2009)) {
            intent.putExtra("burc", "fare");
        } else if (date.before(date2010)) {
            intent.putExtra("burc", "okuz");
        } else if (date.before(date2011)) {
            intent.putExtra("burc", "kaplan");
        } else if (date.before(date2012)) {
            intent.putExtra("burc", "tavsan");
        } else if (date.before(date2013)) {
            intent.putExtra("burc", "ejderha");
        } else if (date.before(date2014)) {
            intent.putExtra("burc", "yilan");
        } else if (date.before(date2015)) {
            intent.putExtra("burc", "at");
        } else if (date.before(date2016)) {
            intent.putExtra("burc", "keci");
        } else if (date.before(date2017)) {
            intent.putExtra("burc", "maymun");
        } else if (date.before(date2018)) {
            intent.putExtra("burc", "horoz");
        } else if (date.before(date2019)) {
            intent.putExtra("burc", "kopek");
        } else if (date.before(date2020)) {
            intent.putExtra("burc", "domuz");
        }
        // 5 şubat 2020'den büyük tarihler için
        else if (date.after(date2020a)) {
            intent.putExtra("burc", "noburc");
        }
    }

    public void showAds() {
        //No luck he will see the ads
        if (MainActivity.facebookInterstitial != null && MainActivity.facebookInterstitial.isAdLoaded()) {
            //Facebook ads loaded he will see Facebook
            startActivity(intent);
            MainActivity.facebookInterstitial.show();
        } else if (MainActivity.admobInterstitial != null && MainActivity.admobInterstitial.isLoaded()) {
            //Facebook ads doesnt loaded he will see AdMob
            startActivity(intent);
            MainActivity.admobInterstitial.show();
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