package com.creativematrix.noteapp.firebase;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.creativematrix.noteapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    NotificationCompat.Builder mBuilder;
    NotificationManagerCompat nmc;
    NotificationManager manager;

    private void showNotification(int id, String title, String message, Intent intent) {

        String chanelId = "chanelId";
        String chanelName = "chanelName";

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent,
                PendingIntent.FLAG_ONE_SHOT);

        nmc = NotificationManagerCompat.from(this);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(chanelId, chanelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
            mBuilder = new NotificationCompat.Builder(this, chanelId)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.logo)
                    .setContentText(message)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            ;
            manager.notify(id, mBuilder.build());
        } else {
            mBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle(title)
                    .setSmallIcon(R.drawable.logo)
                    .setContentText(message)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            ;
            nmc = NotificationManagerCompat.from(this);
            manager.notify(id, mBuilder.build());
        }
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage == null)
            return;

        if (remoteMessage.getNotification() != null) {

            handleDataMessage(remoteMessage);

        }

        if (remoteMessage.getData().size() > 0) {

            handleDataMessage(remoteMessage);

        }
    }

    private void handleDataMessage(RemoteMessage remoteMessage) {
        int type = -1;
        Long id = -1l;
        Long notificationId = -1l;
        String title = "";
        String body = "";
        try {
            if (remoteMessage.getNotification() != null) {
                if (remoteMessage.getData().size() > 0) {
                    title = remoteMessage.getNotification().getTitle();
                    body = remoteMessage.getNotification().getBody();
                    type = Integer.parseInt(remoteMessage.getData().get("Type"));
                    id = Long.parseLong(remoteMessage.getData().get("id"));
                    notificationId = Long.parseLong(remoteMessage.getData().get("NotificationID"));


                }
            }

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

                Intent resultIntent = null;

                if (resultIntent != null) {
                    resultIntent.putExtra("type", type);
                    resultIntent.putExtra("id", id);
                    resultIntent.putExtra("idNotification", notificationId);
                    showNotification(1, title, body, resultIntent);
                }


            } else {
                // app is in background, show the notification in notification tray

            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }


}
