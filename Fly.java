/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

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
                    
                    if (CritterWorld.occupied[getX_coord()][getY_coord()] > 1) {
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
            
            if (Critter.getRandomInt(9) >= 0) {   
                walk(Critter.getRandomInt(7));
                setHasMoved(true);
            }
            
	}

}