package cn.sunner.sms2calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import cn.sunner.sms2calendar.parser.SMSParser;

public class SMSReceiver extends BroadcastReceiver {

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs;

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus != null) {
                msgs = new SmsMessage[pdus.length];

                // Combine every SMS message received. big SMS may be separated.
                String messageBody = "";
                for (int i = 0; i < msgs.length; i++) {
                    // Convert Object array
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    messageBody += msgs[i].getMessageBody();
                }

                SMSParser parser = getParser(msgs[0].getOriginatingAddress(), messageBody);
                if (parser != null) {
                    List<Event> events = parser.getEvents();
                    for (Event event : events) {
                        Notification.add(context, event);
                    }
                }
            }
        }
    }

    protected static SMSParser getParser(String fromNumber, String text) {
        try {
            Class <?> cls = Class.forName("cn.sunner.sms2calendar.parser.N" + fromNumber + "Parser");
            return (SMSParser) cls.getDeclaredConstructor(String.class).newInstance(text);
        } catch (ClassNotFoundException e) {
            // Do nothing. Not all phone number has a parser class
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
