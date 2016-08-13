package cn.sunner.sms2calendar;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by Sunner on 7/26/16.
 *
 * I can use this class to test all kinds of patterns
 * and send events to anybody. Especially to my home leader, ;-)
 */
public class N15558132322Parser extends SMSParser {
    SMSParser realParser;

    public N15558132322Parser(String text) {
        super(text);
    }

    @Override
    protected void parse() {
        // Try all other parsers
        try {
            Class <?> [] parserClasses = {
                    Class.forName("cn.sunner.sms2calendar.N12306Parser"),
                    Class.forName("cn.sunner.sms2calendar.N106980000666Parser"),
                    Class.forName("cn.sunner.sms2calendar.N95539Parser"),
            };

            for (Class <?> parserClass: parserClasses) {
                realParser = (SMSParser) parserClass.getDeclaredConstructor(String.class).newInstance(text);
                if (realParser.getEvents().size() != 0)
                    break;
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Event> getEvents() {
        return realParser.getEvents();
    }
}
