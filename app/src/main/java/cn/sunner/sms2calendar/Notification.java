package cn.sunner.sms2calendar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Sunner on 7/21/16.
 *
 * Notification manager
 */
public class Notification {
    public static boolean add(Context context, Event event){
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Make a big notification
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(context.getString(R.string.notify_title))
                .setContentText(event.getFormattedText(context))
                .setSmallIcon(R.drawable.ic_stat_icon)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(event.getFormattedText(context)));
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        notifyBuilder.setLargeIcon(bm);

        // Add event intent
        Intent addEvent = new Intent(Intent.ACTION_INSERT)
                .setData(Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.getBeginTime().getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.getEndTime().getTimeInMillis())
                .putExtra(Events.TITLE, event.getTitle())
                .putExtra(Events.DESCRIPTION, event.getText())
                .putExtra(Events.EVENT_LOCATION, event.getLocation());

        // An unique id
        int uniqueId = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, uniqueId, addEvent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifyBuilder.setContentIntent(pendingIntent);
        // NotificationCompat.Builder builder = notifyBuilder.addAction(R.drawable.ic_stat_add, context.getString(R.string.add), pendingIntent);

        notificationManager.notify(
                uniqueId, // So no notifications have the same id
                notifyBuilder.build());

        return true;
    }
}
