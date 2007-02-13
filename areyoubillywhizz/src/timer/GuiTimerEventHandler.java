package timer;

import gui.App.Time;

import java.util.List;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import com.cloudgarden.resource.SWTResourceManager;

import parser.TimerCodeParser;
import parser.TimerCodeParser.TimerState;
import timer.Solve.ResultType;

public class GuiTimerEventHandler implements TimerEventHandler {

	private Session session = new Session();

	Label currentTimeLabel;

	Label currentAverageLabel;

	List<Time> times;

	Button leftButton;

	Button rightButton;

	long jiffys;

	TimerState state = TimerState.OFF;

	final static Color GREEN = SWTResourceManager.getColor(128, 255, 128);

	final static Color GRAY = SWTResourceManager.getColor(224, 223, 227);

	boolean canAcceptNextTime = false;

	Timer timer;

	public GuiTimerEventHandler() {
		setTimer(new Timer());
		getTimer().registerTimerEventHandler(this);
	}

	public void processScannedInput(String scannedInput) {
		final String si = scannedInput;
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				currentTimeLabel.setText("0" + si.substring(1, 2) + ":"
						+ si.substring(2, 4) + "." + si.substring(4, 6));
				TimerState state = TimerCodeParser.getState(si);
				if (getState() != state) {
					leftButton.setText("left");
					rightButton.setText("right");
					switch (state) {
					case LEFT:
						leftButton.setVisible(true);
						rightButton.setVisible(false);
						break;
					case RIGHT:
						leftButton.setVisible(false);
						rightButton.setVisible(true);
						break;
					case STANDBY:
						leftButton.setText("GO!!");
						rightButton.setText("GO!!");
					case BOTH:
						leftButton.setVisible(true);
						rightButton.setVisible(true);
						break;
					case OFF:
					case STOPPED:
						if (canAcceptNextTime) {
							session.getAttempts().add(
									new Solve((int) TimerCodeParser
											.getJiffys(si), ResultType.SOLVED));
							currentAverageLabel.setText(TimerCodeParser
									.jiffysToDisplay(session
											.getCurrentAverage()));
							updateScreenTimes(session, times);
						}
						canAcceptNextTime = false;
						leftButton.setVisible(false);
						rightButton.setVisible(false);
						break;
					case RUNNING:
					case RESET:
						canAcceptNextTime = true;
						leftButton.setVisible(false);
						rightButton.setVisible(false);
						break;
					}
				}
				setState(state);
			}
		});

		setJiffys(TimerCodeParser.getJiffys(scannedInput));
	}

	public void close() {
		setTimer(null);
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

	public Label getCurrentTimeLabel() {
		return currentTimeLabel;
	}

	public void setCurrentTimeLabel(Label currentTimeLabel) {
		this.currentTimeLabel = currentTimeLabel;
	}

	public long getJiffys() {
		return jiffys;
	}

	public void setJiffys(long jiffys) {
		this.jiffys = jiffys;
	}

	public TimerState getState() {
		return state;
	}

	public void setState(TimerState state) {
		this.state = state;
	}

	public Button getLeftButton() {
		return leftButton;
	}

	public void setLeftButton(Button leftButton) {
		this.leftButton = leftButton;
	}

	public Button getRightButton() {
		return rightButton;
	}

	public void setRightButton(Button rightButton) {
		this.rightButton = rightButton;
	}

	public Label getCurrentAverageLabel() {
		return currentAverageLabel;
	}

	public void setCurrentAverageLabel(Label currentAverageLabel) {
		this.currentAverageLabel = currentAverageLabel;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
