package gui;

import parser.TimerCodeParser;
import timer.SimpleTimerEventHandler;
import timer.Timer;
import junit.framework.TestCase;

public class FullScreenTest extends TestCase {

    /*
     * Test method for 'gui.FullScreen.FullScreen()'
     */
    public void testFullScreen() throws Exception {
        Timer t = new Timer();        
        FullScreen fullScreen = new FullScreen();
        SimpleTimerEventHandler steh = new SimpleTimerEventHandler();

        
        t.registerTimerEventHandler(steh);
        steh.registerDisplayUpdateHandler(fullScreen);
        for (int i = 0 ; i < 1000 ; i++){


//        for (int i = 0 ; i < 300 ; i++){
//            fullScreen.newTimeString(TimerCodeParser.jiffysToDisplay(i));
//            Thread.sleep(10);
//        }
        Thread.sleep(10);
        }

    }

}
