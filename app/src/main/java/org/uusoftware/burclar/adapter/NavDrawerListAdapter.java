package org.uusoftware.burclar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.uusoftware.burclar.MainActivity;
import org.uusoftware.burclar.R;
import org.uusoftware.burclar.model.NavDrawerItem;

import java.util.ArrayList;

public class NavDrawerListAdapter extends BaseAdapter {

    String str1 = "https://www.facebook.com/uusoftware";
    String str2 = "https://twitter.com/uusoftware1";
    String str3 = "https://play.google.com/store/apps/dev?id=9176605091546815752";
    String str4 = "https://www.youtube.com/channel/UCpzVBPCN4XSJt8sL5u8X_FQ";
    String str5 = "https://plus.google.com/115518080824239135242/posts";
    boolean premium;
    int position2;
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        premium = MainActivity.premium;
        if (premium) {
            position2 = 5;
        } else {
            position2 = 6;
        }

        if (convertView == null) {
            if (position == position2) {
                LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.drawer_footer_item, null);

                ImageView facebook = (ImageView) convertView.findViewById(R.id.footerFacebook);
                ImageView twitter = (ImageView) convertView.findViewById(R.id.footerTwitter);
                ImageView googleplay = (ImageView) convertView.findViewById(R.id.footerGooglePlay);
                ImageView youtube = (ImageView) convertView.findViewById(R.id.footerYoutube);
                ImageView googleplus = (ImageView) convertView.findViewById(R.id.footerGooglePlus);

                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(str1));
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);
                    }
                });
                twitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(str2));
                        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent2);
                    }
                });
                googleplay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(str3));
                        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent3);
                    }
                });
                youtube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(str4));
                        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent4);
                    }
                });
                googleplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse(str5));
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent5);
                    }
                });
            } else {
                LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.drawer_list_item, null);

                ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
                TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

                imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
                txtTitle.setText(navDrawerItems.get(position).getTitle());
            }
        }
        return convertView;
    }
}