package formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimeFormatter {

	public static String formatTime(long jiffys) {
		NumberFormat formatter = new DecimalFormat("00");
	
		String mins = formatter.format(jiffys / 6000);
		String secs = formatter.format((jiffys % 6000) / 100);
		String jifs = formatter.format((jiffys % 6000) % 100);
			
		return mins + ":" + secs + "." + jifs;
	}
}
