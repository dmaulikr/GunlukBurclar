package org.uusoftware.burclar.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            scheduleAlarms(context);
        } else {
            NotificationGenerator.generateNotification(context);
        }
    }

    public void scheduleAlarms(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent myIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();

        Calendar calendar2 = Calendar.getInstance();
        int hourofday = calendar2.get(Calendar.HOUR_OF_DAY);

        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (hourofday < 10) {
            alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {
            alarmManager.setInexactRepeating(AlarmManager.RTC, 1000 * 60 * 60 * 24 + calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }
}