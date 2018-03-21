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
    
    public static void initialize(){
        numCritters = 0;
        
        for (String[] row : critterGrid) {
            Arrays.fill(row, "");
        }
        for (int[] row : occupied) {
            Arrays.fill(row, 0);
        }
    }
}
