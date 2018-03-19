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
	
    public boolean fight(String not_used) { return true; }

    @Override
    public void doTimeStep() {
    }
}
