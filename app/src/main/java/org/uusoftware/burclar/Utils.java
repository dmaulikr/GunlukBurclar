package org.uusoftware.burclar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static void generateNotification(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_icon);
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM yyyy EEEE");
        Notification noti = new NotificationCompat.Builder(context).setContentTitle("Günlük Burçlar")
                .setContentText(sdf.format(new Date()) + ": " + "Günlük burç yorumunuz").setSmallIcon(R.drawable.ic_icon)
                .setContentIntent(pIntent).setLargeIcon(bm).setDefaults(Notification.DEFAULT_ALL).setPriority(2).build();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, noti);

    }
}