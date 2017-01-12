package org.uusoftware.burclar.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.uusoftware.burclar.FavoritesActivity;
import org.uusoftware.burclar.R;
import org.uusoftware.burclar.model.GridItem;

import java.io.File;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private List<GridItem> feedItemList;
    private Context mContext;

    public GridAdapter(Context context, List<GridItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        GridItem feedItem = feedItemList.get(i);

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

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            final int position = holder.getPosition();

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(R.string.chooseaction);
            builder.setItems(R.array.choose_actions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Get Uri
                    String path = Environment.getExternalStorageDirectory().toString() + "/Günlük Burçlar";
                    File f = new File(path);
                    File file[] = f.listFiles();
                    Intent intent = new Intent();
                    if (which == 0) {
                        // Show
                        intent.setAction(android.content.Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file[position]), "image/*");
                        mContext.startActivity(intent);
                    } else if (which == 1) {
                        // Share
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file[position]));
                        intent.setType("image/*");
                        mContext.startActivity(intent);
                    } else {
                        // Delete
                        file[position].delete();
                        Toast.makeText(mContext, R.string.deleted, Toast.LENGTH_SHORT).show();
                        intent = new Intent(mContext, FavoritesActivity.class);
                        mContext.startActivity(intent);
                        ((FavoritesActivity) mContext).finish();
                    }
                }
            });
            builder.show();
        }
    };
}