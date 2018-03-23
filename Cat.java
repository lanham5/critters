/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment4;


/**
 *
 * @author Michael
 */
public class Cat extends Critter {
    @Override
    public String toString() { return "C"; }
	
    
    public boolean fight(String not_used) { return true; }

    @Override
    public void doTimeStep() {
        if (Critter.getRandomInt(10) >= 5) {   
            walk(Critter.getRandomInt(7));
            setHasMoved(true);
        } else {
            
        }
    }
    
}
