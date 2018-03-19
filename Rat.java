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
        if (enemy.equals("C") || enemy.equals("S")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void doTimeStep() {
        int direction = Critter.getRandomInt(7);
    }
}
