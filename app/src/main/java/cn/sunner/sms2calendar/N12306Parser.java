package cn.sunner.sms2calendar;

import android.provider.CalendarContract;

/**
 * Created by Sunner on 6/29/16.
 */
public class N12306Parser implements SMSParser {
    @Override
    public CalendarContract.Events getEventFrom(String text) {
        return null;
    }
}
