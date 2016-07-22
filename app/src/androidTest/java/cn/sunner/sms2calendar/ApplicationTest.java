package cn.sunner.sms2calendar;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void testAddOne() throws Exception {
        SMSParser parser = new N12306Parser("订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertTrue(Notification.add(getSystemContext(), parser));
    }
}