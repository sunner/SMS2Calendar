package cn.sunner.sms2calendar;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sunner on 8/17/16.
 */
public class EventTest {
    @Test
    public void testConstructor() throws Exception {
        Event event = new Event();
        assertNotNull(event.getTitle());
        assertNotNull(event.getLocation());
        assertNotNull(event.getText());
        assertNull(event.getBeginTime());
        assertNull(event.getEndTime());
    }
}