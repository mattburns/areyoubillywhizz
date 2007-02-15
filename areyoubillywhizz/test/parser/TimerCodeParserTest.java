package parser;

import parser.TimerCodeParser.TimerState;
import junit.framework.TestCase;

public class TimerCodeParserTest extends TestCase {

    /*
     * Test method for 'parser.TimerCodeParser.getMillis(String)'
     */
    public void testGetMillis() {
        long jiffys = TimerCodeParser.getJiffys("R00175M");
        assertEquals(175, jiffys);
        jiffys = TimerCodeParser.getJiffys("R10003D");
        assertEquals(6003, jiffys);
    }

    /*
     * Test method for 'parser.TimerCodeParser.getState(String)'
     */
    public void testGetState() {
        TimerState state = TimerCodeParser.getState("I00175M");
        assertEquals(TimerState.RESET, state);
        state = TimerCodeParser.getState("A00175M");
        assertEquals(TimerState.STANDBY, state);
        state = TimerCodeParser.getState(" 00175M");
        assertEquals(TimerState.RUNNING, state);
        state = TimerCodeParser.getState("S00175M");
        assertEquals(TimerState.STOPPED, state);
        state = TimerCodeParser.getState("L00175M");
        assertEquals(TimerState.LEFT, state);
        state = TimerCodeParser.getState("R00175M");
        assertEquals(TimerState.RIGHT, state);
        state = TimerCodeParser.getState("C00175M");
        assertEquals(TimerState.BOTH, state);
    }

    public void testIsValid() {
        assertTrue(TimerCodeParser.isValid("R00175M"));
        assertFalse(TimerCodeParser.isValid("R00175N"));
        assertFalse(TimerCodeParser.isValid("R00176M"));
    }

    public void testJiffysToString() {
        int min = 100 * 60;
        assertEquals("01:00.00", TimerCodeParser.jiffysToDisplay(min));
        assertEquals("01:12.34", TimerCodeParser.jiffysToDisplay(min + 1234));
        assertEquals("00:12.34", TimerCodeParser.jiffysToDisplay(1234));
        assertEquals("00:00.00", TimerCodeParser.jiffysToDisplay(0));
        int max = 100 * 60 * 9 + 5999;
        assertEquals("09:59.99", TimerCodeParser.jiffysToDisplay(max));
    }
}
