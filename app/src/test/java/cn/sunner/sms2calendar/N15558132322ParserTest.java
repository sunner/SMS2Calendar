package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Sunner on 7/27/16.
 */
public class N15558132322ParserTest {

    @Test
    public void test12306Parse() throws Exception {
        SMSParser parser = new N15558132322Parser("订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertTrue(parser.isValid());
        assertEquals("D2245次14车13F号", parser.getTitle());
        assertEquals("南京南站", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1 - 1, 24, 19, 11), parser.getBeginTime());
    }

    @Test
    public void testCtripParse() throws Exception {
        SMSParser parser = new N15558132322Parser("已出票：订单1234567890『首都航空 JD5688 杭州萧山T3-海口美兰 10月1日9:00-10月1日11:35 陈美人，票号898-1234567890；孙志岗，票号898-1234567890』请提前2小时至机场值机。可在APP『行程』频道查看行程，值机选座、退改点击http://t.ctrip.cn/dFxBvc【携程网】");
        assertTrue(parser.isValid());
        assertEquals("首都航空JD5688", parser.getTitle());
        assertEquals("杭州萧山T3", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10 - 1, 1, 9, 0), parser.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10 - 1, 1, 11, 35), parser.getEndTime());
    }
}