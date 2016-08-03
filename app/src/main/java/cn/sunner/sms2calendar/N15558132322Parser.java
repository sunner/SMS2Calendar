package cn.sunner.sms2calendar;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;

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
    protected boolean parse() {

        // Try all other parsers
        try {
            Class <?> [] parserClasses = {
                    Class.forName("cn.sunner.sms2calendar.N12306Parser"),
                    Class.forName("cn.sunner.sms2calendar.N106980000666Parser"),
                    Class.forName("cn.sunner.sms2calendar.N95539Parser"),
            };

            for (Class <?> parserClass: parserClasses) {
                realParser = (SMSParser) parserClass.getDeclaredConstructor(String.class).newInstance(text);
                if (realParser != null && realParser.isValid()) {
                    return true;
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTitle() {
        return realParser.getTitle();
    }

    @Override
    public Calendar getBeginTime() {
        return realParser.getBeginTime();
    }

    @Override
    public Calendar getEndTime() {
        return realParser.getEndTime();
    }

    @Override
    public String getLocation() {
        return realParser.getLocation();
    }

    @Override
    public boolean isValid() {
        return realParser.isValid();
    }

    @Override
    public String getText() {
        return realParser.getText();
    }
}
