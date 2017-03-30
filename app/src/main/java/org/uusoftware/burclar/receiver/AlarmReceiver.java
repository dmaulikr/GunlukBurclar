package org.uusoftware.burclar.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

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
        SharedPreferences prefs = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        boolean alarm = prefs.getBoolean("Alarm", true);
        if (alarm) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Intent myIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            Calendar calendar = Calendar.getInstance();

            int alarmHour = prefs.getInt("alarmHour", 10);
            int alarmMinute = prefs.getInt("alarmMinute", 0);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.HOUR_OF_DAY, alarmHour);
            calendar2.set(Calendar.MINUTE, alarmMinute);

            if (calendar.getTimeInMillis() < calendar2.getTimeInMillis()) {
                alarmManager.setInexactRepeating(AlarmManager.RTC, calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
            } else {
                alarmManager.setInexactRepeating(AlarmManager.RTC, 1000 * 60 * 60 * 24 + calendar2.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        }
    }
}