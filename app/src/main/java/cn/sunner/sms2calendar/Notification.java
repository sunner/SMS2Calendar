package cn.sunner.sms2calendar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Sunner on 7/21/16.
 */
public class Notification {
    public static boolean add(Context context, SMSParser parser){
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Make a big notification with two button
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.notify_title))
                .setContentText(parser.getText())
                .setSmallIcon(R.drawable.ic_stat_icon)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(parser.getText()));

        // Add event intent
        Intent addEvent = new Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, parser.getBeginTime().getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, parser.getEndTime().getTimeInMillis())
                .putExtra(Events.TITLE, parser.getTitle())
                .putExtra(Events.DESCRIPTION, parser.getText())
                .putExtra(Events.EVENT_LOCATION, parser.getLocation());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, addEvent, PendingIntent.FLAG_ONE_SHOT);
        notifyBuilder.setContentIntent(pendingIntent);
        // NotificationCompat.Builder builder = notifyBuilder.addAction(R.drawable.ic_stat_add, context.getString(R.string.add), pendingIntent);

        notificationManager.notify(
                (int) (System.currentTimeMillis() & 0x00000000FFFFFFFF), // So no notifications have the same id
                notifyBuilder.build());

        return true;
    }
}
