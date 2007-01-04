package formatter;

import junit.framework.TestCase;

public class TimeFormatterTest extends TestCase {

	/*
	 * Test method for 'formatter.TimeFormatter.formatTime(long)'
	 */
	public void testFormatTime() {
		String formatted = TimeFormatter.formatTime(123);
		assertEquals("00:01.23", formatted);

		formatted = TimeFormatter.formatTime(6003);
		assertEquals("01:00.03", formatted);
	}

}
