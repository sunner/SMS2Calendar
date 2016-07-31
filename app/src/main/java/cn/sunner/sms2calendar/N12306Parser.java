package cn.sunner.sms2calendar;

import android.nfc.Tag;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sunner on 6/29/16.
 */
public class N12306Parser extends SMSParser {

    public N12306Parser(String text) {
        super(text);
    }

    @Override
    protected boolean parse() {
        String pattern = ".*[购|签](\\d+)月(\\d+)日(.+[号|铺])([^\\d]+)(\\d+):(\\d+)开。.*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);

        if (!m.matches()) {
            return false;
        }

        title = m.group(3);
        location = m.group(4) + "站";   // Append 站 to make it more accurate for maps
        beginTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(1)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(2)),   // Day
                Integer.parseInt(m.group(5)),   // Hour
                Integer.parseInt(m.group(6))    // Minute
        );

        // set end time to 1 hour later
        endTime = (Calendar) beginTime.clone();
        endTime.add(Calendar.HOUR, 1);

        return true;
    }
}
