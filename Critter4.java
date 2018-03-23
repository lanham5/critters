/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
package assignment4;

//4's will not fight other snakes or bother with plants. When unencumbered,
//it walks around and stays in place.

/**
 *
 * @author Michael
 */
public class Critter4 extends Critter{
    @Override
    public String toString() { return "4"; }
	
    public boolean fight(String other) { 
        if(other.equals("@")){
            return false;
        }
        if(other.equals("S")){
            return false;
        }
        return true; 
    }

    @Override
    public void doTimeStep() {
        int direction = Critter.getRandomInt(7);
        if(CritterWorld.occupied[this.getY_coord()][this.getX_coord()] > 1){
            return;
        }
        if (Critter.getRandomInt(9) >= 5) {   
                run(Critter.getRandomInt(7));
                setHasMoved(true);
        } 
    }
}
