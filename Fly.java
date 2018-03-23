/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Fly extends Critter {
	
	@Override
	public String toString() { return "*"; }
		
	public Fly() {
            
	}
	
	public boolean fight(String enemy) { 
            int direction = Critter.getRandomInt(7);
 
            if (getHasMoved() == false) {
                if (enemy.equals("@")) {
                    return true; 
                } else {
                    walk(direction);
                    
                    if (CritterWorld.occupied[getY_coord()][getX_coord()] > 0) {
                        undoWalk();
                        return true;
                    }                                         
                    return false; 
                }
            } else {
                return true;
            }
        }

	@Override
	public void doTimeStep() {
            if (Critter.getRandomInt(9) >= 5) {   
                walk(Critter.getRandomInt(7));
                setHasMoved(true);
            } 
            
	}

}