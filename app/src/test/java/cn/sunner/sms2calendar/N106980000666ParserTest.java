package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sunner on 7/26/16.
 */
public class N106980000666ParserTest {

    @Test
    public void testParsePattern1() throws Exception {
        SMSParser parser = new N106980000666Parser(
                "已出票：订单9987654321『首都航空 JD5688 杭州萧山T3-海口美兰 10月1日9:00-10月1日11:35 陈美人，票号898-1234567890；孙志岗，票号898-1234567890』请提前2小时至机场值机。可在APP『行程』频道查看行程，值机选座、退改点击http://t.ctrip.cn/dFxBvc【携程网】"
        );

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("首都航空JD5688", event.getTitle());
        assertEquals("杭州萧山T3", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10 - 1, 1, 9, 0), event.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10 - 1, 1, 11, 35), event.getEndTime());
    }

    @Test
    public void testParsePatter2() throws Exception {
        SMSParser parser = new N106980000666Parser(
                "订单1234567890已出票 孙志岗 北京首都T3-杭州萧山T1，07月23日21:10-23:30，中国国航CA1714，票号：999-1234567890。建议提前45分钟以上办理值机。【携程】"
        );

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("中国国航CA1714", event.getTitle());
        assertEquals("北京首都T3", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 7 - 1, 23, 21, 10), event.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 7 - 1, 23, 23, 30), event.getEndTime());
    }

    @Test
    public void testNotValid() throws Exception {
        SMSParser parser = new N106980000666Parser(
                "订单1234567890已出票 孙志岗 北京首都T3-杭州萧山T1，07月23日21:10"
        );
        List<Event> events = parser.getEvents();
        assertEquals(0, events.size());
    }
}