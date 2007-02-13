package timer;

public interface TimerEventHandler {
	/**
	 * receive fresh data from the timer.
	 * @param scannedInput
	 */
	public void processScannedInput(String scannedInput);
}
