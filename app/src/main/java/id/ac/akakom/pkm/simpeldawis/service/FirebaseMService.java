package id.ac.akakom.pkm.simpeldawis.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import id.ac.akakom.pkm.simpeldawis.menu.notifikasi.Model.NotifikasiModel;
import id.ac.akakom.pkm.simpeldawis.utils.NotificationUtil;


/**
 * rizmaulana@live.com
 * Mobile Apps Developer
 */
public class FirebaseMService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived: "+remoteMessage.getData().get("body"));
//        showPushNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));

        Gson gson = new Gson();
        new NotificationUtil(getApplicationContext()).showPersonChatNotif(gson.fromJson(""+remoteMessage.getData().get("body"), NotifikasiModel.class));
    }


    /*private void showPushNotification(String title, String message){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentIntent(PendingIntent.getActivity(
                        getApplicationContext(), 0, new Intent(getApplicationContext(), LauncherView.class), PendingIntent.FLAG_NO_CREATE))
                .setContentText(message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
            notificationBuilder.setSmallIcon(R.drawable.ic_mail_outline);
        } else {
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }*/
}
