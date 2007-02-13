package sandpit;

import timer.Session;
import timer.Solve;
import timer.Solve.ResultType;
import junit.framework.TestCase;

public class SessionTest extends TestCase {

	/*
	 * Test method for 'sandpit.Session.getCurrentAverage(int, int, int)'
	 */
	public void testGetCurrentAverage() {
		Session session = new Session();
		session.getAttempts().add(new Solve(98, ResultType.SOLVED));
		session.getAttempts().add(new Solve(199, ResultType.SOLVED));
		session.getAttempts().add(new Solve(123, ResultType.SOLVED));
		session.getAttempts().add(new Solve(100, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		session.setDiscardBest(1);
		session.setDiscardWorst(1);
		session.setSolvesOnScreen(5);
		assertEquals(123, session.getCurrentAverage());
		session.setSolvesOnScreen(3);
		assertEquals(123, session.getCurrentAverage());
		session.setSolvesOnScreen(30);
		assertEquals(0, session.getCurrentAverage()); // if total is > attempts, return zero
		session.setDiscardBest(0);
		session.setDiscardWorst(0);
		session.setSolvesOnScreen(3);
		assertEquals(123, session.getCurrentAverage());
		session.setSolvesOnScreen(2);
		assertEquals(123, session.getCurrentAverage());
		session.setDiscardBest(4);
		session.setDiscardWorst(2);
		session.setSolvesOnScreen(2);
		assertEquals(0, session.getCurrentAverage()); // or if total - (best+worst) <1
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		session.setDiscardBest(0);
		session.setDiscardWorst(0);
		session.setSolvesOnScreen(5);
		assertEquals(146, session.getCurrentAverage());
		session.setSolvesOnScreen(1);
		assertEquals(146, session.getCurrentAverage());
		session.setSolvesOnScreen(3);
		assertEquals(146, session.getCurrentAverage());
		session.setDiscardBest(2);
		session.setDiscardWorst(2);
		session.setSolvesOnScreen(5);
		assertEquals(146, session.getCurrentAverage());
	}

	/*
	 * Test method for 'sandpit.Session.getFastest(int, List<Solve>)'
	 */
	public void testGetFastestIntListOfSolve() {
		Session session = new Session();
		session.getAttempts().add(new Solve(98, ResultType.SOLVED));
		session.getAttempts().add(new Solve(199, ResultType.SOLVED));
		session.getAttempts().add(new Solve(123, ResultType.SOLVED));
		session.getAttempts().add(new Solve(100, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		
		//assertEquals(123, session.getFastest(5, session.getAttempts()));
	}

	/*
	 * Test method for 'sandpit.Session.getSlowest(int, List<Solve>)'
	 */
	public void testGetSlowestIntListOfSolve() {
		Session session = new Session();
		session.getAttempts().add(new Solve(98, ResultType.SOLVED));
		session.getAttempts().add(new Solve(199, ResultType.SOLVED));
		session.getAttempts().add(new Solve(123, ResultType.SOLVED));
		session.getAttempts().add(new Solve(100, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		
		//assertEquals(123, session.getFastest(5, session.getAttempts()));
	}

	/*
	 * Test method for 'sandpit.Session.getFastest(List<Solve>)'
	 */
	public void testGetFastestListOfSolve() {
		Session session = new Session();
		session.getAttempts().add(new Solve(98, ResultType.SOLVED));
		session.getAttempts().add(new Solve(199, ResultType.SOLVED));
		session.getAttempts().add(new Solve(123, ResultType.SOLVED));
		session.getAttempts().add(new Solve(100, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		
		assertEquals(98, session.getSpeedOrderedAttempts().get(session.getAttempts().size() - 1));
	}

	/*
	 * Test method for 'sandpit.Session.getSlowest(List<Solve>)'
	 */
	public void testGetSlowestListOfSolve() {
		Session session = new Session();
		session.getAttempts().add(new Solve(98, ResultType.SOLVED));
		session.getAttempts().add(new Solve(199, ResultType.SOLVED));
		session.getAttempts().add(new Solve(123, ResultType.SOLVED));
		session.getAttempts().add(new Solve(100, ResultType.SOLVED));
		session.getAttempts().add(new Solve(146, ResultType.SOLVED));
		
		assertEquals(199, session.getSpeedOrderedAttempts().get(0));
	}

}
