/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment4;

//A 2 is a critter that generally avoids conflict. It will walk 
//around but runs if threatened by a 3 or a 4. Otherwise, it 
//will fight.


/**
 *
 * @author Michael
 */
public class Critter2 extends Critter {
    @Override
    public String toString() { return "2"; }
    /**
     * 2's fight method, 2 runs from 3's and 4's, but fights everything else
     * @param enemy
     * @return 
     */
    public boolean fight(String enemy) { 
        int direction = Critter.getRandomInt(7);

            
        if (getHasMoved() == false) {
            if (enemy.equals("3") || enemy.equals("4")) {
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
