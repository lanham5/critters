package assignment4;
/*
 * Critters Assignment
 * Jared Ucherek, JMU329
 * Michael Lanham, ML42972
 */
import assignment4.Critter.TestCritter;

public class Algae extends TestCritter {

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { 
            return false; 
        }
	
	public void doTimeStep() {
            CritterWorld.critterGrid[this.getY_coord()][this.getX_coord()] = this.toString();
            setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
}
