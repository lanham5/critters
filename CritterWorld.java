/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4;

import java.util.Arrays;

/**
 *
 * @author Michael
 */
public class CritterWorld {
    public static int[][] occupied = new int[Params.world_height][Params.world_width];
    public static int numCritters;
    public static String[][] critterGrid = new String[Params.world_height][Params.world_width];  
    private static String myPackage;
    
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
    
    public static void initialize(){
        numCritters = 0;
        empty();
        
        try{
            for(int i = 0; i < 100; i++){
                Critter.makeCritter("Algae");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Craig");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Fly");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Snake");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Cat");

            }
            for(int i = 0; i < 25; i++){
                Critter.makeCritter("Rat");

            }
        } catch (InvalidCritterException ex) {
            System.out.println("Error in Initialization of Critter World");;
        }

    }
    
    public static void empty(){
        for (String[] row : critterGrid) {
            Arrays.fill(row, "");
        }
        for (int[] row : occupied) {
            Arrays.fill(row, 0);
        }
    }
    
}
