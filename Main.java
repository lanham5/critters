package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */

import java.util.Scanner;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    static PrintStream old = System.out;	// if you want to restore output to console
    private static boolean processed = true;

    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args){ 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }
            
        /* Do not alter the code above for your submission. */

        CritterWorld.initialize();       
        kb.useDelimiter("\n");
        String input = kb.next();
        String input1;
        String input2;
        String input3;
        int length;
        
        while(!input.equals("quit")){
            length = 1;
            input1 = input;
            input2 = "";
            input3 = "";
            if(input1.contains(" ")){
                length = input.split(" ").length;
            }
            if(length > 3){
                 processed = false;
            }
            else if(length == 2){
                input1 = input.split(" ")[0];
                input2 = input.split(" ")[1];
            }
            else if(length == 3){
                input1 = input.split(" ")[0];
                input2 = input.split(" ")[1];
                input3 = input.split(" ")[2]; 
            }
            switch(input1){
                case "show":
                    if(length == 1){
                        Critter.displayWorld();
                    } else {
                        processed = false;
                    }                
                    break;
                case "step":
                    if(length <= 2){
                        int count = 1;
                        if(isInteger(input2)){
                            count = Integer.valueOf(input2);
                        } else if (length == 2){
                            processed = false;
                            break;
                        }
                        for(int i = 0; i < count; i++){
                            try {
                                Critter.worldTimeStep();
                            } catch (InvalidCritterException ex) {
                                 processed = false;
                            }
                        }
                    } else {
                        processed = false;
                        break;
                    }
                        
                    break;
                case "seed":
                    if(length == 2 && isInteger(input2)){
                        int seed = Integer.valueOf(input2);
                        Critter.setSeed(seed);
                    } else {
                        processed = false;
                    }         
                    break;
                case "make":
                    if(length == 3 || length == 2){
                        String name = input.split(" ")[1];
                        int count = 1;
                        if(isInteger(input3)){
                            count = Integer.valueOf(input3);
                        }
                        for(int i = 0; i < count; i++){
                            try {
                                Critter.makeCritter(name);
                            } catch (InvalidCritterException ex) {
                                 processed = false;
                            }
                        }
                    } else{
                        processed = false;
                    }                
                    break;
                case "stats":
                    if(length == 2){
                        try {
                            List<Critter> instances = Critter.getInstances(input2);

                            Class<?> c = Class.forName(Critter.class.getPackage().toString().split(" ")[1] + "." + input2);
                            c.getMethod("runStats", List.class).invoke(null, instances);
                        } catch (InvalidCritterException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                             processed = false;
                        }
                    } else {
                        processed = false;
                    }
                    
                    break;

                default:
                    processed = false;
                    break;
            }
            
            if(!processed){
                System.out.println("Error Processing: " + input);
            }
            processed = true;
            input = kb.next();
            
                
        }        
        /* Write your code above */
        System.out.flush();

    }
    
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
