package timer;

import gui.DisplayUpdateHandler;
import parser.TimerCodeParser;
import parser.TimerCodeParser.TimerState;
import timer.Solve.ResultType;

public class SimpleTimerEventHandler implements TimerEventHandler {
    private Session session = new Session();

    TimerState state = TimerState.OFF;

    boolean leftPressed;

    boolean rightPressed;

    boolean canAcceptNextTime = false;
    
    DisplayUpdateHandler displayUpdateHandler;
    
    public void processScannedInput(String scannedInput) {
        displayUpdateHandler.newTimeString(TimerCodeParser.jiffysToDisplay(
                (int)TimerCodeParser.getJiffys(scannedInput)));
        TimerState newState = TimerCodeParser.getState(scannedInput);
        TimerState oldState = state;
        state = newState;
        if (newState != oldState) {

            switch (state) {
            case LEFT:
                leftPressed = true;
                rightPressed = false;
                break;
            case RIGHT:
                leftPressed = false;
                rightPressed = true;
                break;
            case STANDBY:
            // ready to go
            case BOTH:
                leftPressed = true;
                rightPressed = true;
                break;
            case OFF:
            case STOPPED:
                if (canAcceptNextTime) {
                    session.getAttempts()
                            .add(
                                    new Solve((int) TimerCodeParser
                                            .getJiffys(scannedInput),
                                            ResultType.SOLVED));
                }
                canAcceptNextTime = false;
                leftPressed = false;
                rightPressed = false;
                break;
            case RUNNING:
            case RESET:
                canAcceptNextTime = true;
                leftPressed = false;
                rightPressed = false;
                break;
            }
        }
    }
    
    public void registerDisplayUpdateHandler(DisplayUpdateHandler displayUpdateHandler) {
        this.displayUpdateHandler = displayUpdateHandler;
    }
}
