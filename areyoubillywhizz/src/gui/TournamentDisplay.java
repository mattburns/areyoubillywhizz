package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.cloudgarden.resource.SWTResourceManager;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class TournamentDisplay extends org.eclipse.swt.widgets.Composite {

    {
        // Register as a resource user - SWTResourceManager will
        // handle the obtaining and disposing of resources
        SWTResourceManager.registerResourceUser(this);
    }

    public Label CurrentTime;

    /**
     * Auto-generated main method to display this
     * org.eclipse.swt.widgets.Composite inside a new Shell.
     */
    public static void main(String[] args) {
        showGUI();
    }

    /**
     * Auto-generated method to display this org.eclipse.swt.widgets.Composite
     * inside a new Shell.
     */
    public static void showGUI() {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        TournamentDisplay inst = new TournamentDisplay(shell, SWT.NULL);
        Point size = inst.getSize();
        shell.setLayout(new FillLayout());
        shell.layout();
        if (size.x == 0 && size.y == 0) {
            inst.pack();
            shell.pack();
        } else {
            Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
            shell.setSize(shellBounds.width, shellBounds.height);
        }
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }

    public TournamentDisplay(org.eclipse.swt.widgets.Composite parent, int style) {
        super(parent, style);
        initGUI();
    }

    private void initGUI() {
        try {
            {

            }
            FillLayout thisLayout = new FillLayout(
                    org.eclipse.swt.SWT.HORIZONTAL);
            this.setLayout(thisLayout);
            this.setFont(SWTResourceManager.getFont("DS-Digital", 8, 3, false,
                    false));
            {
                CurrentTime = new Label(this, SWT.VERTICAL);
                CurrentTime.setAlignment(SWT.CENTER);
                CurrentTime.setText("00:00.00");
                CurrentTime.setBackground(SWTResourceManager.getColor(0, 0, 0));
                CurrentTime.setFont(SWTResourceManager.getFont("DS-Digital",
                        72, 3, false, false));
                CurrentTime.setForeground(SWTResourceManager
                        .getColor(255, 0, 0));
            }
            this.layout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
