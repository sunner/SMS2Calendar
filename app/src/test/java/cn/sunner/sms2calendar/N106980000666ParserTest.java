package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.sunner.sms2calendar.parser.N106980000666Parser;
import cn.sunner.sms2calendar.parser.SMSParser;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sunner on 7/26/16.
 * <p/>
 * Test ctrip.com parser
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
    public void testFourEvents() throws Exception {
        SMSParser parser = new N106980000666Parser(
                "(1/2) 机票已出：订单1708724174已出票。CHEN/MEIREN，票号781-1234567890；SUN/ZHIGANG，票号781-1234567890『（1）东方航空 MU779 上海浦东T1-奥克兰I 4月29日0:05-4月29日17:15（2）新西兰航空 NZ549 奥克兰-基督城克赖斯特彻奇 4月29日19:10-4月29日20:35（3）新西兰航空 NZ532 基督城克赖斯特彻奇-奥克兰 5月8日16:45-5月8日18:05（4）东方航空 MU780 奥克兰I-上海浦东T1 5月8日21:00-5月9日5:30』建议您提前3小时到达机场办理值机。温馨提示：航班4月29日凌晨00:05起飞，请留意起飞日期，以免误机。机票按顺序使用。【携程网】"
        );

        List<Event> events = parser.getEvents();
        assertEquals(4, events.size());

        Event event = events.get(0);
        assertEquals("东方航空MU779", event.getTitle());
        assertEquals("上海浦东T1", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 4 - 1, 29, 0, 5), event.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 4 - 1, 29, 17, 15), event.getEndTime());

        event = events.get(1);
        assertEquals("新西兰航空NZ549", event.getTitle());
        assertEquals("奥克兰", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 4 - 1, 29, 19, 10), event.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 4 - 1, 29, 20, 35), event.getEndTime());

        event = events.get(2);
        assertEquals("新西兰航空NZ532", event.getTitle());
        assertEquals("基督城克赖斯特彻奇", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 5 - 1, 8, 16, 45), event.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 5 - 1, 8, 18, 5), event.getEndTime());

        event = events.get(3);
        assertEquals("东方航空MU780", event.getTitle());
        assertEquals("奥克兰I", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 5 - 1, 8, 21, 0), event.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 5 - 1, 9, 5, 30), event.getEndTime());
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