package cn.sunner.autosmstocalendar;

import android.provider.CalendarContract;

/**
 * Created by Sunner on 6/28/16.
 */
public interface SMSParser {
    public CalendarContract.Events getEventFrom(String text);
}
