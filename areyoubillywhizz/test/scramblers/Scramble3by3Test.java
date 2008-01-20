package scramblers;

import junit.framework.TestCase;

public class Scramble3by3Test extends TestCase {
    
    Scramble3by3 threeByThree;

    public void setUp() {
        threeByThree = new Scramble3by3();
    }
    
    /*
     * Test method for 'scramblers.Scramble3by3.getName()'
     */
    public void testNameIsCorrect() {
        assertEquals("3x3 Cube", threeByThree.getName());
    }

    /*
     * Test method for 'scramblers.Scramble3by3.getScramble()'
     */
    public void testScrambleIsRightLength() {
        String scramble = threeByThree.getScramble();
        int moves = scramble.split(" ").length;
        System.out.println(scramble);
        assertEquals(scramble + " should have had 30 moves", 30, moves);
    }
    
    /*
     * Test method for 'scramblers.Scramble3by3.getScramble()'
     */
    public void testScrambleHasNoMovesOfSameFaceAdjacent() {
        String scramble = threeByThree.getScramble();
        String moves[] = scramble.split(" ");
        for (int i = 0; i < moves.length - 1; i++) {
            if (moves[i].charAt(0) == moves[i + 1].charAt(0)) {
                fail("adjacent face turning at index " + i + ": " + scramble);
            }
        }
    }

}
