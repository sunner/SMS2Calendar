package cn.sunner.sms2calendar.parser;

import java.util.ArrayList;
import java.util.List;

import cn.sunner.sms2calendar.Event;

/**
 * Created by Sunner on 6/28/16.
 *
 * Parse SMS and save events
 */
public abstract class SMSParser {
    protected List<Event> events = new ArrayList<>();
    protected String text;

    public SMSParser(String text) {
        this.text = text;
        parse();
    }

    /**
     * Parse text and extract events into this.events
     */
    protected abstract void parse();

    public List<Event> getEvents() {
        return events;
    }
}
