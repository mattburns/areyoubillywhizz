package gui;

public interface DisplayUpdateHandler {

    public void newTimeString(String time);
    public void newLeftState(boolean isPressed);
    public void newRightState(boolean isPressed);
}
