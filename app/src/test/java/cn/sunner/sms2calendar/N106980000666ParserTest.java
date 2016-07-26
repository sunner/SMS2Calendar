package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Sunner on 7/26/16.
 */
public class N106980000666ParserTest {

    @Test
    public void testParsePattern1() throws Exception {
        SMSParser parser = new N106980000666Parser("已出票：订单9987654321『首都航空 JD5688 杭州萧山T3-海口美兰 10月1日9:00-10月1日11:35 陈丽玮，票号898-1234567890；孙志岗，票号898-1234567890』请提前2小时至机场值机。可在APP『行程』频道查看行程，值机选座、退改点击http://t.ctrip.cn/dFxBvc【携程网】");
        assertTrue(parser.isValid());
        assertEquals("首都航空JD5688", parser.getTitle());
        assertEquals("杭州萧山T3", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10, 1, 9, 0), parser.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10, 1, 11, 35), parser.getEndTime());
    }

    @Test
    public void testParsePatter2() throws Exception {
        SMSParser parser = new N106980000666Parser("订单1234567890已出票 孙志岗 北京首都T3-杭州萧山T1，07月23日21:10-23:30，中国国航CA1714，票号：999-1234567890。建议提前45分钟以上办理值机。【携程】");
        assertTrue(parser.isValid());
        assertEquals("中国国航CA1714", parser.getTitle());
        assertEquals("北京首都T3", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 7, 23, 21, 10), parser.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 7, 23, 23, 30), parser.getEndTime());
    }

    @Test
    public void testNotValid() throws Exception {
        SMSParser parser = new N106980000666Parser("订单1234567890已出票 孙志岗 北京首都T3-杭州萧山T1，07月23日21:10");
        assertFalse(parser.isValid());
    }
}