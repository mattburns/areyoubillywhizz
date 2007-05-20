package timer;

import java.util.ArrayList;
import java.util.List;

import gui.DisplayUpdateHandler;
import parser.TimerCodeParser;
import parser.TimerCodeParser.TimerState;
import timer.Solve.ResultType;

public class SimpleTimerEventHandler implements TimerEventHandler {
    private Session session;

    TimerState state = TimerState.OFF;
    
    long currentJiffys = 0;

    boolean leftPressed;

    boolean rightPressed;

    boolean canAcceptNextTime = false;
    
    List<DisplayUpdateHandler> displayUpdateHandlers;
    
    public SimpleTimerEventHandler(Session session) {
        setSession(session);
    }
    
    public void processScannedInput(String scannedInput) {
        currentJiffys = TimerCodeParser.getJiffys(scannedInput);
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
        notifyListenersOfUpdate();
    }
    
    private void notifyListenersOfUpdate() {
        for (DisplayUpdateHandler handler : displayUpdateHandlers) {
            handler.newTimeString(TimerCodeParser.jiffysToDisplay(currentJiffys));
            handler.newLeftState(leftPressed);
            handler.newRightState(rightPressed);
            handler.newSession(session);
            handler.newState(state);
        }
    }
    
    public void registerDisplayUpdateHandler(DisplayUpdateHandler displayUpdateHandler) {
        if (displayUpdateHandlers == null) {
            displayUpdateHandlers = new ArrayList<DisplayUpdateHandler>();
        }
        displayUpdateHandlers.add(displayUpdateHandler);
    }
    
    public void deregisterDisplayUpdateHandler(DisplayUpdateHandler displayUpdateHandler) {
        displayUpdateHandlers.remove(displayUpdateHandler);
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
