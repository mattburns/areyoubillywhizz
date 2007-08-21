package gui;

import timer.Session;
import timer.SimpleTimerEventHandler;
import timer.Timer;

public class FullscreenOnlyApp {

    public static void main(String[] args) {
        
        Timer t = new Timer();  
        Session session = new Session();
        SimpleTimerEventHandler steh = new SimpleTimerEventHandler(session);
        t.registerTimerEventHandler(steh);
        
        FullScreen fullScreen = new FullScreen();
        steh.registerDisplayUpdateHandler(fullScreen);

        while (fullScreen.isActive()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        t.stop();
    }
    
}
