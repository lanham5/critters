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
public class Snake extends Critter{
    @Override
    public String toString() { return "S"; }
	
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
