package org.uusoftware.burclar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

public class FragmentSixth extends Fragment {

    int selectedburc;
    String link;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general, container, false);

        selectedburc = SecondActivity.burcid;
        ImageView image = (ImageView) v.findViewById(R.id.imageView1);

        if (selectedburc == 0) {
            image.setImageResource(R.drawable.burc_koc);
            link = "http://uusoftware.org/burclar/kocgunluk.php";
        } else if (selectedburc == 1) {
            image.setImageResource(R.drawable.burc_boga);
            link = "http://uusoftware.org/burclar/bogagunluk.php";
        } else if (selectedburc == 2) {
            image.setImageResource(R.drawable.burc_ikizler);
            link = "http://uusoftware.org/burclar/ikizlergunluk.php";
        } else if (selectedburc == 3) {
            image.setImageResource(R.drawable.burc_yengec);
            link = "http://uusoftware.org/burclar/yengecgunluk.php";
        } else if (selectedburc == 4) {
            image.setImageResource(R.drawable.burc_aslan);
            link = "http://uusoftware.org/burclar/aslangunluk.php";
        } else if (selectedburc == 5) {
            image.setImageResource(R.drawable.burc_basak);
            link = "http://uusoftware.org/burclar/basakgunluk.php";
        } else if (selectedburc == 6) {
            image.setImageResource(R.drawable.burc_terazi);
            link = "http://uusoftware.org/burclar/terazigunluk.php";
        } else if (selectedburc == 7) {
            image.setImageResource(R.drawable.burc_akrep);
            link = "http://uusoftware.org/burclar/akrepgunluk.php";
        } else if (selectedburc == 8) {
            image.setImageResource(R.drawable.burc_yay);
            link = "http://uusoftware.org/burclar/yaygunluk.php";
        } else if (selectedburc == 9) {
            image.setImageResource(R.drawable.burc_oglak);
            link = "http://uusoftware.org/burclar/oglakgunluk.php";
        } else if (selectedburc == 10) {
            image.setImageResource(R.drawable.burc_kova);
            link = "http://uusoftware.org/burclar/kovagunluk.php";
        } else {
            image.setImageResource(R.drawable.burc_balik);
            link = "http://uusoftware.org/burclar/balikgunluk.php";
        }

        WebView myWebView = (WebView) v.findViewById(R.id.webView1);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.loadUrl(link);

        return v;
    }
}