package model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {


    @Test
    void Constructor() {
        Event event = new Event("yes");
        Date date = Calendar.getInstance().getTime();
        assertEquals("yes", event.getDescription());
        assertEquals(date.toString(), event.getDate().toString());
    }

    @Test
    void testEquals() {
        Event event1 = new Event("yes");
        Event event2 = event1;
        Event event3 = new Event("no");
        assertFalse(event1.equals(null));
        assertFalse(event1.equals(1));
        assertTrue(event1.equals(event2));
        assertFalse(event1.equals(event3));
        Event event4 = new Event("yes");
        assertFalse(event1.equals(event4));
    }

    @Test
    public void testToString() {
        Event e = new Event("yes");
        Date d = Calendar.getInstance().getTime();
        assertEquals(d.toString() + "\n" + "yes", e.toString());
    }

}
