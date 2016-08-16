package cn.sunner.sms2calendar.parser;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sunner.sms2calendar.Event;

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
    protected void parse() {
        final boolean b = parse1() || parse2();
    }

    protected boolean parse1() {
        String pattern = "（*\\d*）*(\\S+) ([A-Z0-9]{5,}) (\\S+)-\\S+ (\\d+)月(\\d+)日(\\d+):(\\d+)-(\\d+)月(\\d+)日(\\d+):(\\d+)";
        Matcher m = Pattern.compile(pattern)
                .matcher(text.substring(text.indexOf("『") + 1));    // Keep meaningful substring

        while (m.find()) {
            Event event = new Event();
            event.setText(text);

            event.setTitle(m.group(1) + m.group(2));
            event.setLocation(m.group(3));

            event.setBeginTime(new GregorianCalendar(
                            Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                            Integer.parseInt(m.group(4)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                            Integer.parseInt(m.group(5)),   // Day
                            Integer.parseInt(m.group(6)),   // Hour
                            Integer.parseInt(m.group(7))    // Minute
                    )
            );
            event.setEndTime(new GregorianCalendar(
                            Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                            Integer.parseInt(m.group(8)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                            Integer.parseInt(m.group(9)),   // Day
                            Integer.parseInt(m.group(10)),   // Hour
                            Integer.parseInt(m.group(11))    // Minute
                    )
            );

            events.add(event);
        }

        return !events.isEmpty();
    }

    protected boolean parse2() {
        String pattern = ".* (.+)-.+，(\\d+)月(\\d+)日(\\d+):(\\d+)-(\\d+):(\\d+)，(.+)，.*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);

        if (!m.matches()) {
            return false;
        }

        Event event = new Event();
        event.setText(text);

        event.setTitle(m.group(8));
        event.setLocation(m.group(1));

        event.setBeginTime(new GregorianCalendar(
                        Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                        Integer.parseInt(m.group(2)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                        Integer.parseInt(m.group(3)),   // Day
                        Integer.parseInt(m.group(4)),   // Hour
                        Integer.parseInt(m.group(5))    // Minute
                )
        );
        event.setEndTime(new GregorianCalendar(
                        Calendar.getInstance().get(Calendar.YEAR),   // Use this year
                        Integer.parseInt(m.group(2)) - 1,   // Month. It is 0-based Σ( ° △ °|||)︴
                        Integer.parseInt(m.group(3)),   // Day
                        Integer.parseInt(m.group(6)),   // Hour
                        Integer.parseInt(m.group(7))    // Minute
                )
        );

        events.add(event);
        return true;
    }

}
