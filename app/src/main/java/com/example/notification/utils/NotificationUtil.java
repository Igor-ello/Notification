package com.example.notification.utils;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notification.MainActivity;
import com.example.notification.R;

public class NotificationUtil {
    private static final String CHANNEL_ID = "NOT";
    private static final String CHANNEL_NAME = "NOT_NAME";
    private static final Integer NOTIFICATION_ID = 1;
    private static NotificationUtil instance;
    public Integer a = 0;

    private NotificationUtil() {

    }

    public static NotificationUtil getInstance() {
        if (instance == null) {
            instance = new NotificationUtil();

        }
        return instance;
    }

    public Integer increaseA() {
        return a++;
    }

    public void createNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription("ADMIN_CHANNEL");
        adminChannel.enableLights(true);
        adminChannel.enableVibration(true);
        adminChannel.setLightColor(Color.RED);

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        //Replace adminChannel to channel
        notificationManager.createNotificationChannel(adminChannel);
    }

    public void showNotification(Context context, String title, String description) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("text1", "SOMETHING");
        //Попадаем извне в приложение
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat
                .Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                //.setVibrate()
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
