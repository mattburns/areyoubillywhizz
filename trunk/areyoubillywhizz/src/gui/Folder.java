package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

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
public class Folder extends org.eclipse.swt.custom.CTabFolder {
    private CTabItem threeByThreeTab;

    public TournamentDisplay tournamentDisplay1;

    private CTabItem tournamentDisplay;

    public Times magicTimes;

    private CTabItem magicTab;

    public Times threeByThreeTimes;

    /**
     * Auto-generated main method to display this
     * org.eclipse.swt.custom.CTabFolder inside a new Shell.
     */
    public static void main(String[] args) {
        showGUI();
    }

    /**
     * Auto-generated method to display this org.eclipse.swt.custom.CTabFolder
     * inside a new Shell.
     */
    public static void showGUI() {
        Display display = Display.getDefault();
        Shell shell = new Shell(display);
        Folder inst = new Folder(shell, SWT.NULL);
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

    public Folder(org.eclipse.swt.widgets.Composite parent, int style) {
        super(parent, style);
        initGUI();
    }

    private void initGUI() {
        try {
            {

            }
            this.setLayout(new GridLayout());
            {
                threeByThreeTab = new CTabItem(this, SWT.NONE);
                threeByThreeTab.setText("3x3");
                {
                    threeByThreeTimes = new Times(this, SWT.NONE);
                    threeByThreeTab.setControl(threeByThreeTimes);
                }
            }
            {
                magicTab = new CTabItem(this, SWT.NONE);
                magicTab.setText("Magic");
                {
                    magicTimes = new Times(this, SWT.NONE);
                    magicTab.setControl(magicTimes);
                }
            }
            {
                tournamentDisplay = new CTabItem(this, SWT.NONE);
                tournamentDisplay.setText("Tournament Display");
                {
                    tournamentDisplay1 = new TournamentDisplay(this, SWT.NONE);
                    tournamentDisplay1.CurrentTime.setText("00:00.00");
                    tournamentDisplay.setControl(tournamentDisplay1);
                }
            }
            this.setSelection(1);
            this.layout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
