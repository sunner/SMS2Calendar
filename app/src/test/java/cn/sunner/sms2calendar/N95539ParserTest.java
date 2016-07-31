package cn.sunner.sms2calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Created by Sunner on 7/31/16.
 *
 * Parse SMS from China Southern Airline
 */
public class N95539ParserTest {

    @Test
    public void testParsePattern1() throws Exception {
        SMSParser parser = new N95539Parser("尊敬的旅客，您的订单C1234567890123支付成功。10月04日 CZ6665航班，海口(HAK)08:30-杭州(HGH)11:05，请前往机场南航柜台,提前45分钟完成办理乘机手续。乘机人孙志岗，票号1234567890123. 祝您旅途愉快。 南航App推出移动值机服务，助您提前选座，省心出行。请戳： dwz.cn/1xMVMs 。-【南航】");
        assertTrue(parser.isValid());
        assertEquals("南方航空CZ6665", parser.getTitle());
        assertEquals("海口(HAK)", parser.getLocation());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10 - 1, 4, 8, 30), parser.getBeginTime());
        assertEquals(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), 10 - 1, 4, 11, 5), parser.getEndTime());
    }

    @Test
    public void testNotValid() throws Exception {
        SMSParser parser = new N95539Parser("订单1234567890已出票 孙志岗 北京首都T3-杭州萧山T1，07月23日21:10");
        assertFalse(parser.isValid());
    }
}