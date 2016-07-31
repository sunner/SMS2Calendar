package cn.sunner.sms2calendar;

import android.app.Application;
import android.content.Context;
import android.provider.CalendarContract.Events;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Sunner on 6/28/16.
 */
public abstract class SMSParser {
    protected String text;
    protected String title;
    protected Calendar beginTime;
    protected Calendar endTime;
    protected String location = "";
    protected boolean valid = false;

    public SMSParser(String text) {
        this.text = text;
        this.valid = parse();
    }

    protected abstract boolean parse();

    public String getTitle() {
        return title;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public boolean isValid() {
        return valid;
    }

    public String getText() {
        return text;
    }

    public String getFormattedText(Context context) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String formattedText = "";

        formattedText += context.getString(R.string.event) + ": " + getTitle() + "\n";
        formattedText += context.getString(R.string.location) + ": " + getLocation() + "\n";
        formattedText += context.getString(R.string.time) + ": " + dateFormat.format(getBeginTime().getTime());
        formattedText += " - " + dateFormat.format(getEndTime().getTime());

        return formattedText;
    }
}
