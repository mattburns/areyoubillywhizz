/**
 * Copyright 2007 Matt Burns
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package scramblers;


/**
 * @author Matt Burns
 *
 */
public class Scramble3by3 implements ScrambleGenerator {

    private enum FACE {U, D, L, R, F, B};
    private int scrambleLength = 30;
    
    /* (non-Javadoc)
     * @see scramblers.ScrambleGenerator#getName()
     */
    public String getName() {
        return "3x3 Cube";
    }

    /* (non-Javadoc)
     * @see scramblers.ScrambleGenerator#getScramble()
     */
    public String getScramble() {
        String scramble = "";
        FACE lastFace = getRandomFace();
        for (int i = 0 ; i < scrambleLength ; i++) {
            FACE nextFace = getRandomFace();
            while (nextFace.toString().equals(lastFace.toString())) {
                nextFace = getRandomFace();
            }
            lastFace = nextFace;
            scramble += nextFace.toString();
            if (getRandomBoolean()) {
                scramble += "2";
            } else {
                if (getRandomBoolean()) {
                    scramble += "'";
                }
            }
            scramble += " ";
        }
        return scramble.trim();
    }

    private boolean getRandomBoolean() {
        return (Math.random() >= 0.5);
    }
    
    private FACE getRandomFace() {
        int index = (int)Math.floor(Math.random() * FACE.values().length);
        return FACE.values()[index];
    }

}
