package org.uusoftware.burclar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

public class FragmentSixth extends Fragment {

    String selectedburc;
    String link;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_general, container, false);

        selectedburc = SecondActivity.burcid;
        ImageView image = v.findViewById(R.id.imageView1);
        link = "http://uusoftware.org/gunlukburclar/burcyorumlari/" + selectedburc + "gunluk.php";

        if (selectedburc.contains("koc")) {
            image.setImageResource(R.drawable.burc_koc);
            image.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("boga")) {
            image.setImageResource(R.drawable.burc_boga);
            image.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("ikizler")) {
            image.setImageResource(R.drawable.burc_ikizler);
            image.setBackgroundColor(Color.parseColor("#1E88E5"));
        } else if (selectedburc.contains("yengec")) {
            image.setImageResource(R.drawable.burc_yengec);
            image.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("aslan")) {
            image.setImageResource(R.drawable.burc_aslan);
            image.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("basak")) {
            image.setImageResource(R.drawable.burc_basak);
            image.setBackgroundColor(Color.parseColor("#3949AB"));
        } else if (selectedburc.contains("terazi")) {
            image.setImageResource(R.drawable.burc_terazi);
            image.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("akrep")) {
            image.setImageResource(R.drawable.burc_akrep);
            image.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("yay")) {
            image.setImageResource(R.drawable.burc_yay);
            image.setBackgroundColor(Color.parseColor("#512DA8"));
        } else if (selectedburc.contains("oglak")) {
            image.setImageResource(R.drawable.burc_oglak);
            image.setBackgroundColor(Color.parseColor("#311B92"));
        } else if (selectedburc.contains("kova")) {
            image.setImageResource(R.drawable.burc_kova);
            image.setBackgroundColor(Color.parseColor("#311B92"));
        } else {
            image.setImageResource(R.drawable.burc_balik);
            image.setBackgroundColor(Color.parseColor("#311B92"));
        }

        WebView myWebView = v.findViewById(R.id.webView1);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(link);

        return v;
    }
}