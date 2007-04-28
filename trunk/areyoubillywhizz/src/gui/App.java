package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import parser.TimerCodeParser;
import parser.TimerCodeParser.TimerState;
import timer.Session;
import timer.Solve;
import timer.TimerEventHandler;

import com.cloudgarden.resource.SWTResourceManager;

public class App extends org.eclipse.swt.widgets.Composite implements DisplayUpdateHandler {

    private Composite averageTimes;

    private Label currentAverageValue;

    private Label label1;
    
    FullScreen fullScreen;

    private Composite currentAverage;

    private Group rollingAverageGroup;

    private Button rightPad;

    private Button leftPad;

    private List<Time> times;

    private Menu menuBar;

    private MenuItem viewMenuItem;
    private Menu viewMenu;
    private MenuItem fullscreenMenuItem;
    
    private MenuItem aboutMenuItem;

    private MenuItem contentsMenuItem;

    private Menu helpMenu;

    private MenuItem helpMenuItem;

    private MenuItem exitMenuItem;

    private MenuItem openFileMenuItem;

    private Text scrambleTextArea;

    private CCombo eventStyle;

    private CCombo event;

    public Label currentTime;

    private Group scrambleGroup;

    private Group eventGroup;

    private Menu fileMenu;

    private MenuItem menuItem13;

    private MenuItem fileMenuItem;
    
    private TimerEventHandler timerEventHandler;

    {
        // Register as a resource user - SWTResourceManager will
        // handle the obtaining and disposing of resources
        SWTResourceManager.registerResourceUser(this);
    }

    public App(Composite parent, int style, TimerEventHandler timerEventHandler) {
        super(parent, style);
        initGUI();
        this.timerEventHandler = timerEventHandler;
    }

    /**
     * Initializes the GUI.
     */
    private void initGUI() {
        try {
            GridLayout thisLayout = new GridLayout();
            thisLayout.makeColumnsEqualWidth = true;
            thisLayout.numColumns = 5;
            this.setLayout(thisLayout);
            this.setSize(565, 463);
            {
                eventGroup = new Group(this, SWT.NONE);
                RowLayout eventGroupLayout = new RowLayout(
                        org.eclipse.swt.SWT.HORIZONTAL);
                eventGroup.setLayout(eventGroupLayout);
                GridData eventGroupLData = new GridData();
                eventGroupLData.horizontalAlignment = GridData.FILL;
                eventGroupLData.verticalAlignment = GridData.FILL;
                eventGroupLData.grabExcessHorizontalSpace = true;
                eventGroup.setLayoutData(eventGroupLData);
                eventGroup.setText("Event");
                {
                    event = new CCombo(eventGroup, SWT.NONE);
                    FillLayout eventLayout = new FillLayout(
                            org.eclipse.swt.SWT.HORIZONTAL);
                    event.setLayout(eventLayout);
                    event.setText("Event");
                }
                {
                    eventStyle = new CCombo(eventGroup, SWT.NONE);
                    eventStyle.setText("Style");
                }
            }
            {
                scrambleGroup = new Group(this, SWT.NONE);
                FillLayout scrambleGroupLayout = new FillLayout(
                        org.eclipse.swt.SWT.HORIZONTAL);
                scrambleGroup.setLayout(scrambleGroupLayout);
                GridData scrambleGroupLData = new GridData();
                scrambleGroupLData.horizontalSpan = 4;
                scrambleGroupLData.horizontalAlignment = GridData.FILL;
                scrambleGroupLData.verticalAlignment = GridData.FILL;
                scrambleGroup.setLayoutData(scrambleGroupLData);
                scrambleGroup.setText("Scramble");
                {
                    scrambleTextArea = new Text(scrambleGroup, SWT.MULTI
                            | SWT.WRAP);
                    scrambleTextArea.setText("R2 L' U' D2 blah blah blah");
                }
            }
            {
                leftPad = new Button(this, SWT.PUSH | SWT.CENTER);
                GridData leftPadLData = new GridData();
                leftPadLData.verticalAlignment = GridData.FILL;
                leftPadLData.horizontalAlignment = GridData.FILL;
                leftPad.setLayoutData(leftPadLData);
                leftPad.setText("Left");
            }
            {
                currentTime = new Label(this, SWT.VERTICAL);
                currentTime.setAlignment(SWT.CENTER);
                currentTime.setText("00:00.00");
                currentTime.setBackground(SWTResourceManager.getColor(0, 0, 0));
                currentTime.setFont(SWTResourceManager.getFont("DS-Digital",
                        72, 3, false, false));
                GridData CurrentTimeLData = new GridData();
                CurrentTimeLData.horizontalSpan = 3;
                CurrentTimeLData.verticalAlignment = GridData.FILL;
                CurrentTimeLData.horizontalAlignment = GridData.FILL;
                CurrentTimeLData.grabExcessHorizontalSpace = true;
                currentTime.setLayoutData(CurrentTimeLData);
                currentTime.setForeground(SWTResourceManager
                        .getColor(255, 0, 0));
            }
            {
                rightPad = new Button(this, SWT.PUSH | SWT.CENTER);
                GridData rightPadLData = new GridData();
                rightPadLData.verticalAlignment = GridData.FILL;
                rightPadLData.horizontalAlignment = GridData.FILL;
                rightPad.setLayoutData(rightPadLData);
                rightPad.setText("Right");
            }
            {
                rollingAverageGroup = new Group(this, SWT.NONE);
                GridLayout rollingAverageGroupLayout = new GridLayout();
                rollingAverageGroupLayout.makeColumnsEqualWidth = true;
                rollingAverageGroup.setLayout(rollingAverageGroupLayout);
                GridData rollingAverageGroupLData = new GridData();
                rollingAverageGroupLData.horizontalSpan = 5;
                rollingAverageGroupLData.horizontalAlignment = GridData.FILL;
                rollingAverageGroupLData.verticalAlignment = GridData.FILL;
                rollingAverageGroupLData.grabExcessVerticalSpace = true;
                rollingAverageGroupLData.grabExcessHorizontalSpace = true;
                rollingAverageGroup.setLayoutData(rollingAverageGroupLData);
                rollingAverageGroup.setText("Rolling Average");
                {
                    currentAverage = new Composite(rollingAverageGroup,
                            SWT.NONE);
                    GridLayout currentAverageLayout = new GridLayout();
                    currentAverageLayout.makeColumnsEqualWidth = true;
                    GridData currentAverageLData = new GridData();
                    currentAverageLData.heightHint = 86;
                    currentAverageLData.grabExcessHorizontalSpace = true;
                    currentAverageLData.horizontalAlignment = GridData.FILL;
                    currentAverage.setLayoutData(currentAverageLData);
                    currentAverage.setLayout(currentAverageLayout);
                    {
                        label1 = new Label(currentAverage, SWT.NONE);
                        GridData label1LData = new GridData();
                        label1LData.horizontalAlignment = GridData.FILL;
                        label1LData.grabExcessHorizontalSpace = true;
                        label1.setLayoutData(label1LData);
                        label1.setText("Current Average");
                    }
                    {
                        currentAverageValue = new Label(currentAverage,
                                SWT.NONE);
                        GridData currentAverageValueLData = new GridData();
                        currentAverageValueLData.grabExcessHorizontalSpace = true;
                        currentAverageValueLData.horizontalAlignment = GridData.FILL;
                        currentAverageValue
                                .setLayoutData(currentAverageValueLData);
                        currentAverageValue.setText("00:00.00");
                    }
                }
                {
                    averageTimes = new Composite(rollingAverageGroup, SWT.NONE);
                    FillLayout averageTimesLayout = new FillLayout(
                            org.eclipse.swt.SWT.HORIZONTAL);
                    GridData averageTimesLData = new GridData();
                    averageTimesLData.horizontalAlignment = GridData.FILL;
                    averageTimesLData.verticalAlignment = GridData.FILL;
                    averageTimesLData.grabExcessHorizontalSpace = true;
                    averageTimes.setLayoutData(averageTimesLData);
                    averageTimes.setLayout(averageTimesLayout);
                    times = createTimes(averageTimes, 5);

                }
            }

            {
                menuBar = new Menu(getShell(), SWT.BAR);
                getShell().setMenuBar(menuBar);
                fileMenuItem = new MenuItem(menuBar, SWT.CASCADE);
                fileMenuItem.setText("File");
                fileMenu = new Menu(fileMenuItem);
                openFileMenuItem = new MenuItem(fileMenu,
                        SWT.CASCADE);
                openFileMenuItem.setText("Clear");
                menuItem13 = new MenuItem(fileMenu, SWT.SEPARATOR);
                menuItem13.setText("menuItem13");
                exitMenuItem = new MenuItem(fileMenu, SWT.CASCADE);
                exitMenuItem.setText("Exit");
                fileMenuItem.setMenu(fileMenu);
                exitMenuItem.addSelectionListener(new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent event) {
                        getShell().close(); // calls dispose() - see note below
                    }
                });
                viewMenuItem = new MenuItem(menuBar, SWT.CASCADE);
                viewMenuItem.setText("View");
                viewMenu = new Menu(viewMenuItem);
                viewMenuItem.setMenu(viewMenu);
                fullscreenMenuItem = new MenuItem(viewMenu,
                        SWT.CASCADE);
                fullscreenMenuItem.setText("Fullscreen");
                fullscreenMenuItem.addSelectionListener(new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent event) {
                        fullScreen = new FullScreen();
                        timerEventHandler.registerDisplayUpdateHandler(fullScreen);
                        KeyListener listener = new KeyListener();
                        fullScreen.addKeyListener(listener);
                    }
                });
                
                helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
                helpMenuItem.setText("Help");
                helpMenu = new Menu(helpMenuItem);
                contentsMenuItem = new MenuItem(helpMenu,
                        SWT.CASCADE);
                contentsMenuItem.setText("Contents");
                aboutMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
                aboutMenuItem.setText("About");
                helpMenuItem.setMenu(helpMenu);
            }

            Point size = this.getSize();
            Shell shell = (Shell)this.getParent();
            shell.setLayout(null);
            shell.setText("Are You Billy Whizz?");
            shell.setSize(492, 300);
            shell.layout();
            if (size.x == 0 && size.y == 0) {
                this.pack();
                shell.pack();
            } else {
                Rectangle shellBounds = shell.computeTrim(0, 0, size.x, size.y);
                shell.setSize(shellBounds.width, shellBounds.height);
            }
            shell.open();
            this.layout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Time> createTimes(Composite averageTimes, int amount) {
        List<Time> times = new ArrayList<Time>();
        Font myFont = SWTResourceManager.getFont("Tahoma", 8, 1, false, false);

        for (int i = 1; i < amount + 1; i++) {
            Time time = new App.Time();
            time.container = new Composite(averageTimes, SWT.NONE);
            FillLayout time1Layout = new FillLayout(
                    org.eclipse.swt.SWT.VERTICAL);
            time1Layout.type = SWT.VERTICAL;
            time.container.setLayout(time1Layout);
            time.container.setFont(myFont);
            {
                time.label = new Label(time.container, SWT.NONE);
                time.label.setText("" + i);
                time.label.setFont(myFont);
            }
            {
                time.value = new Label(time.container, SWT.NONE);
                time.value.setText("na");
            }
            times.add(time);
        }

        return times;
    }

    public class Time {
        public Label value;

        public Label label;

        public Composite container;
    }
    
    private class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            exitFullscreen();
        }
    }
    
    private void exitFullscreen() {
        timerEventHandler.deregisterDisplayUpdateHandler(fullScreen);
        fullScreen.dispose();
        fullScreen = null;   
    }
    
    private void updateScreenTimes(Session session, List<Time> screenTimes) {
        List<Solve> dateOrderedAttempts = session.getDateOrderedAttempts();

        int first = screenTimes.size() > dateOrderedAttempts.size() ? 0
                : dateOrderedAttempts.size() - screenTimes.size();
        int last = dateOrderedAttempts.size();
        List<Solve> screenSolves = dateOrderedAttempts.subList(first, last);

        List<Solve> speedOrderAttempts = Session.sortByTime(true, screenSolves);
        String newValue = "";
        for (int i = 0; i < screenTimes.size(); i++) {
            Color colour = SWTResourceManager.getColor(0, 0, 0);
            if (i + screenSolves.size() < screenTimes.size()) {
                newValue = "na";
            } else {
                int index = (screenTimes.size() - i);
                Solve solve = screenSolves.get(screenSolves.size() - index);
                int jiffys = solve.getTime();
                if (screenSolves.size() >= screenTimes.size()) {
                    if (solve == speedOrderAttempts.get(speedOrderAttempts
                            .size() - 1))
                        colour = SWTResourceManager.getColor(0, 255, 0);
                    if (solve == speedOrderAttempts.get(0))
                        colour = SWTResourceManager.getColor(255, 0, 0);
                }
                newValue = TimerCodeParser.jiffysToDisplay(jiffys);
            }
            screenTimes.get(i).value.setText(newValue);
            screenTimes.get(i).value.setForeground(colour);
        }
    }

    public void newTimeString(String time) {
        final String finalTime = time;
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                currentTime.setText(finalTime);
            }
        });
    }

    public void newLeftState(boolean isPressed) {
        final boolean finalIsPressed = isPressed;
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                leftPad.setVisible(finalIsPressed);
            }
        });
    }

    public void newRightState(boolean isPressed) {
        final boolean finalIsPressed = isPressed;
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                rightPad.setVisible(finalIsPressed);
            }
        });
    }

    public void newSession(Session session) {
        final Session finalSession = session;
        Display.getDefault().asyncExec(new Runnable() {
            public void run() {
                updateScreenTimes(finalSession, times);
                currentAverageValue.setText(
                        TimerCodeParser.jiffysToDisplay(
                        finalSession.getCurrentAverage()));
            }
        });
    }

    public void newState(TimerState state) {
//        final TimerState finalState = state;
//        Display.getDefault().asyncExec(new Runnable() {
//            public void run() {
//            }
//        });
    }

}
