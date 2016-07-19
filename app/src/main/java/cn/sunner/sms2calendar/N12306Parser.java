package cn.sunner.sms2calendar;

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

        this.title = m.group(3);
        this.location = m.group(4);
        this.date = new GregorianCalendar(
                2016,   //TODO: this year
                Integer.parseInt(m.group(1)),   // Month
                Integer.parseInt(m.group(2)),   // Day
                Integer.parseInt(m.group(5)),   // Hour
                Integer.parseInt(m.group(6))    // Minute
        );

        return true;
    }
}
