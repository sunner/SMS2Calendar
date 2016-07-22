package cn.sunner.sms2calendar;

import android.provider.CalendarContract.Events;

import java.util.Calendar;

/**
 * Created by Sunner on 6/28/16.
 */
public abstract class SMSParser {
    protected String text;
    protected String title;
    protected Calendar startDate;
    protected Calendar endDate;
    protected String location;
    protected boolean valid = false;

    public SMSParser(String text) {
        this.text = text;
        this.valid = parse();
    }

    protected abstract boolean parse();

    public String getTitle() {
        return title;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public boolean isValid() {
        return valid;
    }

    /**
     * Return the event extracted from text
     *
     * @return return null when mismatch
     */
    public Events getEvent() {
        Events event = null;
        if (isValid()) {
            // TODO: Forge calendar event
        }
        return event;
    }

    public String getText() {
        return text;
    }
}
