package cn.sunner.sms2calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sunner on 6/29/16.
 *
 * Parse SMS from Ctrip
 */
public class N106980000666Parser extends SMSParser {

    public N106980000666Parser(String text) {
        super(text);
    }

    @Override
    protected boolean parse() {
        return parse1() || parse2();
    }

    protected boolean parse1() {
        String pattern = ".*『(.+) ([A-Z0-9]+) (.+)-.+ (\\d+)月(\\d+)日(\\d+):(\\d+)-(\\d+)月(\\d+)日(\\d+):(\\d+).*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);

        if (!m.matches()) {
            return false;
        }

        title = m.group(1) + m.group(2);
        location = m.group(3);
        beginTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(4)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(5)),   // Day
                Integer.parseInt(m.group(6)),   // Hour
                Integer.parseInt(m.group(7))    // Minute
        );
        endTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(8)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(9)),   // Day
                Integer.parseInt(m.group(10)),   // Hour
                Integer.parseInt(m.group(11))    // Minute
        );

        return true;
    }

    protected boolean parse2() {
        String pattern = ".* (.+)-.+，(\\d+)月(\\d+)日(\\d+):(\\d+)-(\\d+):(\\d+)，(.+)，.*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);

        if (!m.matches()) {
            return false;
        }

        title = m.group(8);
        location = m.group(1);
        beginTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(2)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(3)),   // Day
                Integer.parseInt(m.group(4)),   // Hour
                Integer.parseInt(m.group(5))    // Minute
        );
        endTime = new GregorianCalendar(
                Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                Integer.parseInt(m.group(2)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                Integer.parseInt(m.group(3)),   // Day
                Integer.parseInt(m.group(6)),   // Hour
                Integer.parseInt(m.group(7))    // Minute
        );

        return true;
    }

}
