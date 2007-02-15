package timer;

import parser.TimerCodeParser;
import parser.TimerCodeParser.TimerState;
import timer.Solve.ResultType;

public class SimpleTimerEventHandler implements TimerEventHandler {
    private Session session = new Session();

    TimerState state = TimerState.OFF;

    boolean leftPressed;

    boolean rightPressed;

    boolean canAcceptNextTime = false;

    public void processScannedInput(String scannedInput) {
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
}
