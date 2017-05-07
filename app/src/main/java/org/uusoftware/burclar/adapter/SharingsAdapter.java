package org.uusoftware.burclar.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
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

public class SharingsAdapter extends RecyclerView.Adapter<SharingsAdapter.ViewHolder> {
    private List<FavoritesItem> feedItemList;
    private Context mContext;

    // Get Uri
    private String path = Environment.getExternalStorageDirectory().toString() + "/Günlük Burçlar/Paylaşılanlar";
    private File f = new File(path);
    private File file[] = f.listFiles();

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            int position = holder.getAdapterPosition();
            Uri photoURI = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".provider", file[position]);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(photoURI, "image/*");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            mContext.startActivity(intent);
        }
    };

    public SharingsAdapter(Context context, List<FavoritesItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_favorites, viewGroup, false);
        return new ViewHolder(v);
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

        ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            text = (TextView) itemView.findViewById(R.id.txt_text);
        }
    }
}
