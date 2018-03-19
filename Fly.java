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
            if (enemy.equals("@")) {
                return true; 
            } else {
                return false; 
            }
        }

	@Override
	public void doTimeStep() {
            if (Critter.getRandomInt(10) >= 5) {   
                walk(Critter.getRandomInt(7));
                setHasMoved(true);
            }   
	}

}