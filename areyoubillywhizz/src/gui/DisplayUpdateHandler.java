package gui;

import parser.TimerCodeParser.TimerState;
import timer.Session;

public interface DisplayUpdateHandler {

    public void newTimeString(String time);
    public void newLeftState(boolean isPressed);
    public void newRightState(boolean isPressed);
    public void newSession(Session session);
    public void newState(TimerState state);
}
