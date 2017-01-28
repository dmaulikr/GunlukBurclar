package org.uusoftware.burclar;

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
        link = "http://uusoftware.org/burclar/" + selectedburc + ".html";

        if (selectedburc == "koc") {
            image.setImageResource(R.drawable.burc_koc);
        } else if (selectedburc == "boga") {
            image.setImageResource(R.drawable.burc_boga);
        } else if (selectedburc == "ikizler") {
            image.setImageResource(R.drawable.burc_ikizler);
        } else if (selectedburc == "yengec") {
            image.setImageResource(R.drawable.burc_yengec);
        } else if (selectedburc == "aslan") {
            image.setImageResource(R.drawable.burc_aslan);
        } else if (selectedburc == "basak") {
            image.setImageResource(R.drawable.burc_basak);
        } else if (selectedburc == "terazi") {
            image.setImageResource(R.drawable.burc_terazi);
        } else if (selectedburc == "akrep") {
            image.setImageResource(R.drawable.burc_akrep);
        } else if (selectedburc == "yay") {
            image.setImageResource(R.drawable.burc_yay);
        } else if (selectedburc == "oglak") {
            image.setImageResource(R.drawable.burc_oglak);
        } else if (selectedburc == "kova") {
            image.setImageResource(R.drawable.burc_kova);
        } else {
            image.setImageResource(R.drawable.burc_balik);
        }

        WebView myWebView = (WebView) v.findViewById(R.id.webView1);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        myWebView.loadUrl(link);

        return v;
    }
}