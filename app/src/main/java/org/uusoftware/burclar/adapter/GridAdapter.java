package org.uusoftware.burclar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import org.uusoftware.burclar.R;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.burc_koc, R.drawable.burc_boga,
            R.drawable.burc_ikizler, R.drawable.burc_yengec,
            R.drawable.burc_aslan, R.drawable.burc_basak,
            R.drawable.burc_terazi, R.drawable.burc_akrep,
            R.drawable.burc_yay, R.drawable.burc_oglak,
            R.drawable.burc_kova, R.drawable.burc_balik
    };

    public GridAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(335, 335));
            imageView.setScaleType(ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}