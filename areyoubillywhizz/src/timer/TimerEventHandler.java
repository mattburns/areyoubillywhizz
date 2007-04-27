package timer;

import gui.DisplayUpdateHandler;

public interface TimerEventHandler {
    /**
     * receive fresh data from the timer.
     * 
     * @param scannedInput
     */
    public void processScannedInput(String scannedInput);
    
    public void registerDisplayUpdateHandler(DisplayUpdateHandler displayUpdateHandler);
}
