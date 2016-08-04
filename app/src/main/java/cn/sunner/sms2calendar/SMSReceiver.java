package cn.sunner.sms2calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;

public class SMSReceiver extends BroadcastReceiver {
    private String TAG = "SMS2Cal";

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            // Combine every SMS message received. big SMS may be separated.
            String messageBody = "";
            for (int i = 0; i < msgs.length; i++) {
                // Convert Object array
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                messageBody += msgs[i].getMessageBody();
            }

            SMSParser parser = getParser(msgs[0].getOriginatingAddress(), messageBody);
            if (parser != null && parser.isValid()) {
                Notification.add(context, parser);
            }
        }
    }

    protected static SMSParser getParser(String fromNumber, String text) {
        try {
            Class <?> cls = Class.forName("cn.sunner.sms2calendar.N" + fromNumber + "Parser");
            return (SMSParser) cls.getDeclaredConstructor(String.class).newInstance(text);
        } catch (ClassNotFoundException e) {
            // Do nothing. Not all phone number has a parser class
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
