package org.uusoftware.burclar.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.uusoftware.burclar.R;
import org.uusoftware.burclar.model.FavoritesItem;

import java.io.File;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private List<FavoritesItem> feedItemList;
    private Context mContext;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            final int position = holder.getPosition();
            // Get Uri
            String path = Environment.getExternalStorageDirectory().toString() + "/Günlük Burçlar";
            File f = new File(path);
            File file[] = f.listFiles();
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file[position]), "image/*");
            mContext.startActivity(intent);
        }
    };

    public FavoritesAdapter(Context context, List<FavoritesItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_favorites, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FavoritesItem feedItem = feedItemList.get(i);

        // Setting image
        viewHolder.image.setImageBitmap(feedItem.getThumbnail());

        // Setting text view title
        viewHolder.text.setText(feedItem.getTitle());

        // Handle click event on both title and image click
        viewHolder.text.setOnClickListener(clickListener);
        viewHolder.image.setOnClickListener(clickListener);

        viewHolder.text.setTag(viewHolder);
        viewHolder.image.setTag(viewHolder);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            text = (TextView) itemView.findViewById(R.id.txt_text);
        }
    }
}
