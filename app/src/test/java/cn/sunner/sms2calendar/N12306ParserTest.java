package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import cn.sunner.sms2calendar.parser.N12306Parser;
import cn.sunner.sms2calendar.parser.SMSParser;

import static org.junit.Assert.assertEquals;

/**
 * Created by Sunner on 7/16/16.
 */
public class N12306ParserTest {

    @Test
    public void testOneSeat() throws Exception {
        SMSParser parser = new N12306Parser("订单EC11541789,孙先生您已购1月24日D2245次14车13F号南京南19:11开。【铁路客服】");

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("D2245次14车13F号", event.getTitle());
        assertEquals("南京南站", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 1 - 1, 24, 19, 11), event.getBeginTime());
    }

    @Test
    public void testTwoSeatsAndChanged() throws Exception {
        SMSParser parser = new N12306Parser("订单EC71177015,孙先生您已签12月9日D2245次14车12D号、4C号上海虹桥19:11开。【铁路客服】");

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("D2245次14车12D号、4C号", event.getTitle());
        assertEquals("上海虹桥站", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 12 - 1, 9, 19, 11), event.getBeginTime());
    }

    @Test
    public void testTwoBeds() throws Exception {
        SMSParser parser = new N12306Parser("订单EC76805753,孙先生您已购2月1日Z176次12车31号下铺、36号上铺杭州9:30开。【铁路客服】");

        List<Event> events = parser.getEvents();
        assertEquals(1, events.size());

        Event event = events.get(0);
        assertEquals("Z176次12车31号下铺、36号上铺", event.getTitle());
        assertEquals("杭州站", event.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 2 - 1, 1, 9, 30), event.getBeginTime());
    }

    @Test
    public void testNotValid() throws Exception {
        SMSParser parser = new N12306Parser("孙志岗的2月4日G7394次8车15B号车票退票成功。流水号2EC84941031001001222209833。【铁路客服】");

        List<Event> events = parser.getEvents();
        assertEquals(0, events.size());
    }

}