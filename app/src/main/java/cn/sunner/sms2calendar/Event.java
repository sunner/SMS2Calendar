package cn.sunner.sms2calendar;

import android.content.Context;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Sunner on 8/13/16.
 * <p>
 * Calendar events.
 */
public class Event {
    protected String text = "";
    protected String title = "";
    protected Calendar beginTime;
    protected Calendar endTime;
    protected String location = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFormattedText(Context context) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String formattedText = "";

        formattedText += String.format("%s: %s\n",
                context.getString(R.string.event),
                getTitle());
        formattedText += String.format("%s: %s\n",
                context.getString(R.string.location),
                getLocation());
        formattedText += String.format("%s: %s\n",
                context.getString(R.string.begin),
                (getEndTime() != null)
                        ? dateFormat.format(getBeginTime().getTime())
                        : context.getString(R.string.na));
        formattedText += String.format("%s: %s",
                context.getString(R.string.end),
                (getEndTime() != null)
                        ? dateFormat.format(getEndTime().getTime())
                        : context.getString(R.string.na));

        return formattedText;
    }
}
