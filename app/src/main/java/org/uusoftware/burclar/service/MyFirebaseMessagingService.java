package org.uusoftware.burclar.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.uusoftware.burclar.MainActivity;
import org.uusoftware.burclar.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    SharedPreferences prefs;
    boolean alarm;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);
        alarm = prefs.getBoolean("Alarm", true);
        if (alarm) {
            if (remoteMessage.getNotification() != null) {
                //Consoledan mesaj gönderildiğinde burası tetiklenecektir
                String title = remoteMessage.getNotification().getTitle();
                sendNotification("Günlük Burçlar", title);
            }
        }
    }

    private void sendNotification(String messageTitle, String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bm)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
