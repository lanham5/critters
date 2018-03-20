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
public class Rat extends Critter {
    @Override
    public String toString() { return "R"; }

    public boolean fight(String enemy) { 
        int direction = Critter.getRandomInt(7);

            
        if (getHasMoved() == false) {
            if (enemy.equals("C") || enemy.equals("S")) {
                CritterWorld.occupied[getX_coord()][getY_coord()]--;
                run(direction);

                if (CritterWorld.occupied[getX_coord()][getY_coord()] > 0) {
                    undoRun(direction);
                    CritterWorld.occupied[getX_coord()][getY_coord()]++;
                    return true;
                } else {
                    CritterWorld.occupied[getX_coord()][getY_coord()]++; //new coordinates
                }
                return false;  
            } else {
                return true;
            }
        } else {
            return true;
        }
        
    }

    @Override
    public void doTimeStep() {
        CritterWorld.occupied[getX_coord()][getY_coord()]--;
        walk(Critter.getRandomInt(7));
        CritterWorld.occupied[getX_coord()][getY_coord()]++;
        
        setHasMoved(true);
    }
}
