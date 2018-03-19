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
public class Cat extends Critter {
    @Override
    public String toString() { return "C"; }
	
    
    public boolean fight(String not_used) { return true; }

    @Override
    public void doTimeStep() {
        int direction = Critter.getRandomInt(7);
    }
    
}
