package org.uusoftware.burclar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FragmentFifth extends Fragment {

    int selectedburc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fifth, container, false);

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

        ImageView image = (ImageView) v.findViewById(R.id.imageViewgenelozellikler);
        TextView textview2 = (TextView) v.findViewById(R.id.textView2genelozellikler);
        TextView textview3 = (TextView) v.findViewById(R.id.textView3genelozellikler);

        if (selectedburc == 0) {
            image.setImageResource(R.drawable.burc_koc);
            textview2.setText("21 Mart - 20 Nisan");
            textview3.setText(R.string.koc);
        }
        if (selectedburc == 1) {
            image.setImageResource(R.drawable.burc_boga);
            textview2.setText("21 Nisan - 20 Mayıs");
            textview3.setText(R.string.boga);
        }
        if (selectedburc == 2) {
            image.setImageResource(R.drawable.burc_ikizler);
            textview2.setText("21 Mayıs - 20 Haziran");
            textview3.setText(R.string.ikizler);
        }
        if (selectedburc == 3) {
            image.setImageResource(R.drawable.burc_yengec);
            textview2.setText("21 Haziran - 20 Temmuz");
            textview3.setText(R.string.yengec);
        }
        if (selectedburc == 4) {
            image.setImageResource(R.drawable.burc_aslan);
            textview2.setText("23 Temmuz - 23 Ağustos");
            textview3.setText(R.string.aslan);
        }
        if (selectedburc == 5) {
            image.setImageResource(R.drawable.burc_basak);
            textview2.setText("24 Ağustos - 23 Eylül");
            textview3.setText(R.string.basak);
        }
        if (selectedburc == 6) {
            image.setImageResource(R.drawable.burc_terazi);
            textview2.setText("24 Eylül - 23 Ekim");
            textview3.setText(R.string.terazi);
        }
        if (selectedburc == 7) {
            image.setImageResource(R.drawable.burc_akrep);
            textview2.setText("24 Ekim - 22 Kasım");
            textview3.setText(R.string.akrep);
        }
        if (selectedburc == 8) {
            image.setImageResource(R.drawable.burc_yay);
            textview2.setText("23 Kasım - 21 Aralık");
            textview3.setText(R.string.yay);
        }
        if (selectedburc == 9) {
            image.setImageResource(R.drawable.burc_oglak);
            textview2.setText("22 Aralık - 20 Ocak");
            textview3.setText(R.string.oglak);
        }
        if (selectedburc == 10) {
            image.setImageResource(R.drawable.burc_kova);
            textview2.setText("21 Ocak - 18 Şubat");
            textview3.setText(R.string.kova);
        }
        if (selectedburc == 11) {
            image.setImageResource(R.drawable.burc_balik);
            textview2.setText("19 Şubat - 20 Mart");
            textview3.setText(R.string.balik);
        }

        return v;
    }
}