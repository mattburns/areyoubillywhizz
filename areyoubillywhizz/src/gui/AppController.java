package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import timer.SimpleTimerEventHandler;
import timer.Timer;

public class AppController {

    /**
     * Auto-generated main method to display this
     * org.eclipse.swt.widgets.Composite inside a new Shell.
     */
    public static void main(String[] args) {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        
        Timer t = new Timer();        
        SimpleTimerEventHandler steh = new SimpleTimerEventHandler();
        App appWindow = new App(shell, SWT.NULL, steh);        
        t.registerTimerEventHandler(steh);
        steh.registerDisplayUpdateHandler(appWindow);
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        t.stop();
    }
}
