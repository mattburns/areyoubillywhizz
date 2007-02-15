package timer;

import java.util.ArrayList;
import java.util.List;

//TODO: optimise this code by writing a sort routine instead of my crazy way 
public class Session {

    private List<Solve> attempts = new ArrayList<Solve>();

    private int solvesOnScreen = 9;

    private int discardBest = 1;

    private int discardWorst = 1;

    private enum SortFields {
        DATE, TIME
    };

    public int getCurrentAverage() {
        if (attempts.size() < solvesOnScreen
                || solvesOnScreen - (discardBest + discardWorst) < 1) {
            return 0;
        }

        int average = 0;
        int firstIndex = attempts.size() - solvesOnScreen;
        List<Solve> currentSolves = getDateOrderedAttempts().subList(
                firstIndex, attempts.size());

        List<Solve> sortedCurrentSolves = sortByTime(true, currentSolves);

        List<Solve> trimmedSortedCurrentSolves = sortedCurrentSolves.subList(
                discardBest, (sortedCurrentSolves.size() - discardWorst));

        for (Solve solve : trimmedSortedCurrentSolves) {
            average += solve.getTime();
        }
        average /= trimmedSortedCurrentSolves.size();

        return average;
    }

    public static List<Solve> sortByTime(boolean fastestToSlowest,
            List<Solve> unsorted) {
        return sortSolves(fastestToSlowest, SortFields.TIME, unsorted);
    }

    public static List<Solve> sortByDate(boolean oldestToNewest,
            List<Solve> unsorted) {
        return sortSolves(oldestToNewest, SortFields.DATE, unsorted);
    }

    private static List<Solve> sortSolves(boolean smallestToBiggest,
            SortFields field, List<Solve> unsorted) {
        List<Solve> sorted = new ArrayList<Solve>();
        while (sorted.size() != unsorted.size()) {
            Solve extremeSolveNotAdded = null;
            for (Solve solve : unsorted) {
                if (!sorted.contains(solve)) {
                    if (extremeSolveNotAdded == null) {
                        extremeSolveNotAdded = solve;
                    } else {
                        switch (field) {
                        case DATE:
                            if ((solve.getDate().before(
                                    extremeSolveNotAdded.getDate()) && smallestToBiggest)
                                    || (solve.getDate().after(
                                            extremeSolveNotAdded.getDate()) && !smallestToBiggest)) {
                                extremeSolveNotAdded = solve;
                            }
                            break;
                        case TIME:
                            if ((solve.getTime() > extremeSolveNotAdded
                                    .getTime() && smallestToBiggest)
                                    || (solve.getTime() < extremeSolveNotAdded
                                            .getTime() && !smallestToBiggest)) {
                                extremeSolveNotAdded = solve;
                            }
                            break;
                        }
                    }
                }
            }
            sorted.add(extremeSolveNotAdded);
        }
        return sorted;
    }

    public List<Solve> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Solve> attempts) {
        this.attempts = attempts;
    }

    public List<Solve> getDateOrderedAttempts() {
        return sortByDate(true, attempts);
    }

    public List<Solve> getSpeedOrderedAttempts() {
        return sortByTime(true, attempts);
    }

    public int getDiscardBest() {
        return discardBest;
    }

    public void setDiscardBest(int discardBest) {
        this.discardBest = discardBest;
    }

    public int getDiscardWorst() {
        return discardWorst;
    }

    public void setDiscardWorst(int discardWorst) {
        this.discardWorst = discardWorst;
    }

    public int getSolvesOnScreen() {
        return solvesOnScreen;
    }

    public void setSolvesOnScreen(int solvesOnScreen) {
        this.solvesOnScreen = solvesOnScreen;
    }
}
