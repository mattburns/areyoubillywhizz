package timer;

import java.util.Date;

/**
 * discription of a particular solve attempt
 * @author floater & karen
 *
 */
public class Solve {

	public enum ResultType {SOLVED, DNF, POP};

	// date solved was achieved
	private Date date;

	// time achieved (in jiffys)
	private int time;
	
	// outcome of solve attempt
	private ResultType result;
	
	public Solve(int time, ResultType resultType) {
		setTime(time);
		setResult(resultType);
		setDate(new Date());
	}
	
	public String toString() {
		return time + "jfs";
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ResultType getResult() {
		return result;
	}
	public void setResult(ResultType result) {
		this.result = result;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
}
