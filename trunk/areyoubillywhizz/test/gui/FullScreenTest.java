package gui;

import junit.framework.TestCase;
import timer.Session;
import timer.SimpleTimerEventHandler;
import timer.Timer;

public class FullScreenTest extends TestCase {

    /*
     * Test method for 'gui.FullScreen.FullScreen()'
     */
    public void testFullScreen() throws Exception {
        Timer t = new Timer();
        FullScreen fullScreen = new FullScreen();
        Session session = new Session();
        SimpleTimerEventHandler steh = new SimpleTimerEventHandler(session);

        t.registerTimerEventHandler(steh);
        steh.registerDisplayUpdateHandler(fullScreen);
        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
        }

    }

}
