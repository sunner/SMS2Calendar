package cn.sunner.sms2calendar;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;


/**
 * Created by Sunner on 7/21/16.
 */
public class Notification {
    public static boolean add(Context context, SMSParser parser){
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(parser.getTitle())
                .setContentText(parser.getText())
                .setSmallIcon(R.drawable.ic_notify_icon);

        notificationManager.notify(
                (int) (System.currentTimeMillis() / 65535), // So no notifications have the same id
                notifyBuilder.build());

        return true;
    }
}
