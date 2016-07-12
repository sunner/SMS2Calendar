package cn.sunner.smstocalendar;

import android.provider.CalendarContract;

/**
 * Created by Sunner on 6/28/16.
 */
public interface SMSParser {
    /**
     * Return the event extracted from text
     *
     * @param text SMS to parse
     * @return return null when mismatch
     */
    public CalendarContract.Events getEventFrom(String text);
}
