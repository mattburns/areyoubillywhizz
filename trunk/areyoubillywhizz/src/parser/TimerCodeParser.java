package parser;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author floater
 *
 */
public class TimerCodeParser {

	public enum TimerState {OFF, RESET, STANDBY, RUNNING, STOPPED, LEFT, RIGHT, BOTH};
	private static Map<String, TimerState> STATE_MAP =  new HashMap<String, TimerState>();
	static {
		STATE_MAP.put("I", TimerState.RESET);
		STATE_MAP.put("A", TimerState.STANDBY);
		STATE_MAP.put(" ", TimerState.RUNNING);
		STATE_MAP.put("S", TimerState.STOPPED);
		STATE_MAP.put("L", TimerState.LEFT);
		STATE_MAP.put("R", TimerState.RIGHT);
		STATE_MAP.put("C", TimerState.BOTH);
	}
	
	public static long getJiffys(String timerCode) {
		if (!isValid(timerCode)) {
			throw new IllegalArgumentException("invalid checksum");
		}
		long jiffys = Long.parseLong(timerCode.substring(2,6)) +
		Long.parseLong(timerCode.substring(1,2)) * 6000;
		return jiffys;
	}
	
	public static String jiffysToDisplay(int jiffys) {
		int mins = jiffys / (100*60);
		int secs = (jiffys/100) % 60;
		int jifs = (jiffys % (100*6))%100;
		
		return String.format("%1$02d:%2$02d.%3$02d", mins, secs, jifs);
	}
	
	public static TimerState getState(String timerCode) {
		if (!isValid(timerCode)) {
			throw new IllegalArgumentException("invalid checksum");
		}
		String state = timerCode.substring(0, 1); 

		return STATE_MAP.get(state);
	}
	
	public static boolean isValid(String timerCode) {
		if (timerCode.length() != 7) {
			return false;
		}
		if (!STATE_MAP.containsKey(String.valueOf(timerCode.charAt(0)))) {
			return false;
		}
		int total = Integer.parseInt(timerCode.substring(1, 2)) +
		Integer.parseInt(timerCode.substring(2, 3)) +
		Integer.parseInt(timerCode.substring(3, 4)) +
		Integer.parseInt(timerCode.substring(4, 5)) +
		Integer.parseInt(timerCode.substring(5, 6));
		total += 64;
		return ((char)total == timerCode.substring(6, 7).toCharArray()[0]);
	}
}
