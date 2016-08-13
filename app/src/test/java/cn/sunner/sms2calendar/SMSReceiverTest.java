package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Sunner on 7/26/16.
 */
public class SMSReceiverTest {

    @Test
    public void testGet12306Parser() throws Exception {
        SMSParser parser = SMSReceiver.getParser("12306", "订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertNotNull(parser);

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("D2245次14车13F号", event.getTitle());
        assertEquals("南京南站", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1 - 1, 24, 19, 11), event.getBeginTime());
    }

    @Test
    public void testGet1558132322Parser() throws Exception {
        SMSParser parser = SMSReceiver.getParser("15558132322", "订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertNotNull(parser);

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("D2245次14车13F号", event.getTitle());
        assertEquals("南京南站", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1 - 1, 24, 19, 11), event.getBeginTime());
    }

    @Test
    public void testGetNonexistParser() throws Exception {
        SMSParser parser = SMSReceiver.getParser("119", "订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertNull(parser);
    }

}