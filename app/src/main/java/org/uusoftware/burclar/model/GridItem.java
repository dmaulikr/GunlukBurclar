package org.uusoftware.burclar.model;

import android.graphics.Bitmap;

public class GridItem {
    private String title;
    private Bitmap thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap bitmap) {
        this.thumbnail = bitmap;
    }
}