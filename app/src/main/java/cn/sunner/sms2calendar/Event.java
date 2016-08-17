package cn.sunner.sms2calendar;

import android.content.Context;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Sunner on 8/13/16.
 *
 * Calendar events.
 */
public class Event {
    protected String text = "";
    protected String title = "";
    protected Calendar beginTime;
    protected Calendar endTime;
    protected String location = "";

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public String getText() {
        return text;
    }

    public String getFormattedText(Context context) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String formattedText = "";

        formattedText += context.getString(R.string.event) + ": " + getTitle() + "\n";
        formattedText += context.getString(R.string.location) + ": " + getLocation() + "\n";
        formattedText += context.getString(R.string.begin) + ": " + dateFormat.format(getBeginTime().getTime()) + "\n";
        formattedText += context.getString(R.string.end) + ": " + dateFormat.format(getEndTime().getTime());

        return formattedText;
    }
}
