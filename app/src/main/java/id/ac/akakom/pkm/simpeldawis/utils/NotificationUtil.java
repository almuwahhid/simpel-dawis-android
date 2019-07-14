package id.ac.akakom.pkm.simpeldawis.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import id.ac.akakom.pkm.simpeldawis.R;
import id.ac.akakom.pkm.simpeldawis.menu.main.MainActivity;
import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel;


public class NotificationUtil {
    private Context context;
    private static NotificationUtil instance;
    private PendingIntent pendingIntent;
    NotificationManager notificationManager;
    NotificationCompat.Builder notificationBuilder;

    public NotificationUtil(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void cancelNotif(int id){
        notificationManager.cancel(id);
    }


    public void showPersonChatNotif(NotifikasiModel model){
        String NOTIFICATION_CHANNEL_ID = "112";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            // Configure the notification channel.
            notificationChannel.setDescription("Simpel Dawis");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(ContextCompat.getColor(context, R.color.colorPrimary));
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo)
                .setTicker("Simpel Dawis")
                //     .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(model.getNama_laporan())
                .setContentText(Function.parseTimestampToDate(model.getCreated_at()))
                .setContentInfo("Simpel Dawis")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(Function.parseTimestampToDate(model.getCreated_at())));

        String replyLabel = "Enter your reply here";

        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 112, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(112,
                notificationBuilder.build());
    }
}
