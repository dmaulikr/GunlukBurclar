package org.uusoftware.burclar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

public class FragmentFifth extends Fragment {

    String selectedburc;
    String link;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fifth, container, false);

        selectedburc = SecondActivity.burcid;
        ImageView image = (ImageView) v.findViewById(R.id.imageView1);
        link = "http://uusoftware.org/gunlukburclar/burcyorumlari/" + selectedburc + ".html";

        if (selectedburc.contains("koc")) {
            image.setImageResource(R.drawable.burc_koc);
            image.setBackgroundColor(Color.parseColor("#341A47"));
        } else if (selectedburc.contains("boga")) {
            image.setImageResource(R.drawable.burc_boga);
            image.setBackgroundColor(Color.parseColor("#3C224F"));
        } else if (selectedburc.contains("ikizler")) {
            image.setImageResource(R.drawable.burc_ikizler);
            image.setBackgroundColor(Color.parseColor("#442B55"));
        } else if (selectedburc.contains("yengec")) {
            image.setImageResource(R.drawable.burc_yengec);
            image.setBackgroundColor(Color.parseColor("#6A3667"));
        } else if (selectedburc.contains("aslan")) {
            image.setImageResource(R.drawable.burc_aslan);
            image.setBackgroundColor(Color.parseColor("#5F3A6E"));
        } else if (selectedburc.contains("basak")) {
            image.setImageResource(R.drawable.burc_basak);
            image.setBackgroundColor(Color.parseColor("#614379"));
        } else if (selectedburc.contains("terazi")) {
            image.setImageResource(R.drawable.burc_terazi);
            image.setBackgroundColor(Color.parseColor("#7C3E75"));
        } else if (selectedburc.contains("akrep")) {
            image.setImageResource(R.drawable.burc_akrep);
            image.setBackgroundColor(Color.parseColor("#82396A"));
        } else if (selectedburc.contains("yay")) {
            image.setImageResource(R.drawable.burc_yay);
            image.setBackgroundColor(Color.parseColor("#8F4073"));
        } else if (selectedburc.contains("oglak")) {
            image.setImageResource(R.drawable.burc_oglak);
            image.setBackgroundColor(Color.parseColor("#C55C73"));
        } else if (selectedburc.contains("kova")) {
            image.setImageResource(R.drawable.burc_kova);
            image.setBackgroundColor(Color.parseColor("#BC4F6C"));
        } else {
            image.setImageResource(R.drawable.burc_balik);
            image.setBackgroundColor(Color.parseColor("#A24F73"));
        }

        WebView myWebView = (WebView) v.findViewById(R.id.webView0);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(link);

        return v;
    }
}