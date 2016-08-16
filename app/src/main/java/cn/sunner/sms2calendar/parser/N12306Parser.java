package cn.sunner.sms2calendar.parser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sunner.sms2calendar.Event;

/**
 * Created by Sunner on 6/29/16.
 *
 * Parse SMS from 12306 (China Railway)
 */
public class N12306Parser extends SMSParser {

    // set end time to 1.5 hour later.
    // Why 1.5 hours? Because it is between Hangzhou and Nanjing.
    public final int DEFAULT_DURATION_IN_MINUTES = 90;

    public N12306Parser(String text) {
        super(text);
    }

    @Override
    protected void parse() {
        String pattern = ".*[购|签](\\d+)月(\\d+)日(.+[号|铺])([^\\d]+)(\\d+):(\\d+)开。.*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);

        if (!m.matches()) {
            return;
        }

        Event event = new Event();
        event.setText(text);

        event.setTitle(m.group(3));
        event.setLocation(m.group(4) + "站");    // Append 站 to make it more accurate for maps

        Calendar beginTime = new GregorianCalendar(
            Calendar.getInstance().get(Calendar.YEAR),   // Use this year
            Integer.parseInt(m.group(1)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
            Integer.parseInt(m.group(2)),   // Day
            Integer.parseInt(m.group(5)),   // Hour
            Integer.parseInt(m.group(6))    // Minute
        );
        event.setBeginTime(beginTime);

        Calendar endTime = (Calendar) beginTime.clone();
        endTime.add(Calendar.MINUTE, DEFAULT_DURATION_IN_MINUTES);
        event.setEndTime(endTime);

        events.add(event);
    }
}
