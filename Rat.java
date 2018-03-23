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
public class Rat extends Critter {
    @Override
    public String toString() { return "R"; }

    public boolean fight(String enemy) { 
        int direction = Critter.getRandomInt(7);

            
        if (getHasMoved() == false) {
            if (enemy.equals("C") || enemy.equals("S")) {
                run(direction);

                if (CritterWorld.occupied[getY_coord()][getX_coord()] > 0) {
                    undoRun(direction);
                    return true;
                } 
                return false;  
            } 
        }
        return true;
    }

    @Override
    public void doTimeStep() {
        walk(Critter.getRandomInt(7));
        
        setHasMoved(true);
    }
}
