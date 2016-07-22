package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sunner on 7/16/16.
 */
public class N12306ParserTest {

    @Test
    public void testOneSeat() throws Exception {
        SMSParser parser = new N12306Parser("订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");
        assertEquals("D2245次14车13F号", parser.getTitle());
        assertEquals("南京南", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1, 24, 19, 11), parser.getStartDate());
    }

    @Test
    public void testTwoSeatsAndChanged() throws Exception {
        SMSParser parser = new N12306Parser("订单EC71177015,孙先生您已签12月9日D2245次14车12D号、4C号上海虹桥19:11开。【铁路客服】");
        assertEquals("D2245次14车12D号、4C号", parser.getTitle());
        assertEquals("上海虹桥", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 12, 9, 19, 11), parser.getStartDate());
    }

    @Test
    public void testTwoBeds() throws Exception {
        SMSParser parser = new N12306Parser("订单EC76805753,孙先生您已购2月1日Z176次12车31号下铺、36号上铺杭州9:30开。【铁路客服】");
        assertEquals("Z176次12车31号下铺、36号上铺", parser.getTitle());
        assertEquals("杭州", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 2, 1, 9, 30), parser.getStartDate());
    }

}