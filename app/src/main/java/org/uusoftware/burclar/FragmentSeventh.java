package org.uusoftware.burclar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FragmentSeventh extends Fragment {

    int selectedburc;
    String link;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general, container, false);

        // Premium & AdMob
        boolean premium = MainActivity.premium;
        AdView adView = (AdView) v.findViewById(R.id.adMob);
        if (premium) {
            adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().addTestDevice("0A83AF9337EAE655A7B29C5B61372D84").build();
            adView.loadAd(adRequest);
        }

        selectedburc = SecondActivity.burcid;
        ImageView image = (ImageView) v.findViewById(R.id.imageView1);

        if (selectedburc == 0) {
            image.setImageResource(R.drawable.burc_koc);
            link = "http://uusoftware.org/burclar/kochaftalik.php";
        } else if (selectedburc == 1) {
            image.setImageResource(R.drawable.burc_boga);
            link = "http://uusoftware.org/burclar/bogahaftalik.php";
        } else if (selectedburc == 2) {
            image.setImageResource(R.drawable.burc_ikizler);
            link = "http://uusoftware.org/burclar/ikizlerhaftalik.php";
        } else if (selectedburc == 3) {
            image.setImageResource(R.drawable.burc_yengec);
            link = "http://uusoftware.org/burclar/yengechaftalik.php";
        } else if (selectedburc == 4) {
            image.setImageResource(R.drawable.burc_aslan);
            link = "http://uusoftware.org/burclar/aslanhaftalik.php";
        } else if (selectedburc == 5) {
            image.setImageResource(R.drawable.burc_basak);
            link = "http://uusoftware.org/burclar/basakhaftalik.php";
        } else if (selectedburc == 6) {
            image.setImageResource(R.drawable.burc_terazi);
            link = "http://uusoftware.org/burclar/terazihaftalik.php";
        } else if (selectedburc == 7) {
            image.setImageResource(R.drawable.burc_akrep);
            link = "http://uusoftware.org/burclar/akrephaftalik.php";
        } else if (selectedburc == 8) {
            image.setImageResource(R.drawable.burc_yay);
            link = "http://uusoftware.org/burclar/yayhaftalik.php";
        } else if (selectedburc == 9) {
            image.setImageResource(R.drawable.burc_oglak);
            link = "http://uusoftware.org/burclar/oglakhaftalik.php";
        } else if (selectedburc == 10) {
            image.setImageResource(R.drawable.burc_kova);
            link = "http://uusoftware.org/burclar/kovahaftalik.php";
        } else {
            image.setImageResource(R.drawable.burc_balik);
            link = "http://uusoftware.org/burclar/balikhaftalik.php";
        }

        WebView myWebView = (WebView) v.findViewById(R.id.webView1);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.loadUrl(link);

        return v;
    }
}