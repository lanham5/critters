/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment4;

//3's are fearless critters. They will fight any other critter on the map.
//It will walk around some of the time, choosing its direction randomly.

/**
 *
 * @author Michael
 */
public class Critter3 extends Critter {
    @Override
    public String toString() { return "3"; }
	
    
    public boolean fight(String not_used) { return true; }

    @Override
    public void doTimeStep() {
        if (Critter.getRandomInt(10) >= 5) {   
            walk(Critter.getRandomInt(7));
            setHasMoved(true);
        } 
    }
    
}
