package assignment4;
/* CRITTERS Critter.java
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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
    private static String myPackage;
    private static List<Critter> population = new java.util.ArrayList<Critter>();
    private static List<Critter> babies = new java.util.ArrayList<Critter>();
    private static List<Critter> dead = new java.util.ArrayList<Critter>();

    // Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    private static java.util.Random rand = new java.util.Random();
    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    public static void setSeed(long new_seed) {
        rand = new java.util.Random(new_seed);
    }


    /* a one-character long string that visually depicts your critter in the ASCII interface */
    public String toString() { return ""; }

    private int energy = 0;
    protected int getEnergy() { 
        return energy; 
    }

    private int x_coord;
    private int y_coord;
    private int prev_x_coord;
    private int prev_y_coord;
    private boolean hasMoved;

    protected final void walk(int direction) {
        energy = energy - Params.walk_energy_cost;
        prev_x_coord = x_coord;
        prev_y_coord = y_coord;
        
        if(alive()){
            CritterWorld.occupied[y_coord][x_coord]--;
            if(CritterWorld.occupied[y_coord][x_coord] == 0){
                CritterWorld.critterGrid[y_coord][x_coord] = "";
            }
            
            switch (direction) {
                case 0:
                    x_coord = (x_coord + 1) % Params.world_width;
                    break;
                case 1:
                    x_coord = (x_coord + 1) % Params.world_width;
                    y_coord = (y_coord == 0) ? Params.world_height-1: y_coord-1;          
                    break;
                case 2:
                    y_coord = (y_coord == 0) ? Params.world_height-1: y_coord-1; 
                    break;
                case 3:
                    x_coord = (x_coord == 0) ? Params.world_width-1: x_coord-1;
                    y_coord = (y_coord == 0) ? Params.world_height-1: y_coord-1; 
                    break;
                case 4:
                    x_coord = (x_coord == 0) ? Params.world_width-1: x_coord-1; 
                    break;
                case 5:
                    x_coord = (x_coord == 0) ? Params.world_width-1: x_coord-1;
                    y_coord = (y_coord + 1) % Params.world_height;
                    break;
                case 6:
                    y_coord = (y_coord + 1) % Params.world_height;
                    break;
                case 7:
                    x_coord = (x_coord + 1) % Params.world_width;
                    y_coord = (y_coord + 1) % Params.world_height;
                    break;
            }

            CritterWorld.occupied[y_coord][x_coord]++;
            CritterWorld.critterGrid[y_coord][x_coord] = this.toString();
            
 //           System.out.println(this.toString() + " just walked, new coords: " + x_coord + "," + y_coord + ". New Energy: " + energy);
        }
    }

    protected final void run(int direction) {
        energy = energy - Params.run_energy_cost;
        prev_x_coord = x_coord;
        prev_y_coord = y_coord;
        
        if(alive()){
            CritterWorld.occupied[y_coord][x_coord]--;
            if(CritterWorld.occupied[y_coord][x_coord] == 0){
                CritterWorld.critterGrid[y_coord][x_coord] = "";
            }
            
            switch (direction) {
                case 0:
                    x_coord = (x_coord + 2) % Params.world_width;
                    break;
                case 1:
                    x_coord = (x_coord + 2) % Params.world_width;
                    y_coord = (y_coord == 0) ? Params.world_height-2: (y_coord == 1) ? Params.world_height-1 : y_coord-2;          
                    break;
                case 2:
                    y_coord = (y_coord == 0) ? Params.world_height-1: (y_coord == 1) ? Params.world_height-1 : y_coord-2; 
                    break;
                case 3:
                    x_coord = (x_coord == 0) ? Params.world_width-2: (x_coord == 1) ? Params.world_width-1 : x_coord-2;
                    y_coord = (y_coord == 0) ? Params.world_height-2: (y_coord == 1) ? Params.world_height-1 : y_coord-2; 
                    break;
                case 4:
                    x_coord = (x_coord == 0) ? Params.world_width-2: (x_coord == 1) ? Params.world_width-1 : x_coord-2; 
                    break;
                case 5:
                    x_coord = (x_coord == 0) ? Params.world_width-2:(x_coord == 1) ? Params.world_width-1 : x_coord-2;
                    y_coord = (y_coord + 2) % Params.world_height;
                    break;
                case 6:
                    y_coord = (y_coord + 2) % Params.world_height;
                    break;
                case 7:
                    x_coord = (x_coord + 2) % Params.world_width;
                    y_coord = (y_coord + 2) % Params.world_height;
                    break;
            }

            CritterWorld.occupied[y_coord][x_coord]++;
            CritterWorld.critterGrid[y_coord][x_coord] = this.toString();
            
//            System.out.println(this.toString() + " just ran, new coords: " + x_coord + "," + y_coord + ". New Energy: " + energy);
        }
    }
    
    protected final void undoWalk() {      
            x_coord = prev_x_coord;
            y_coord = prev_y_coord;

    }

    protected final void undoRun(int direction) {
        x_coord = prev_x_coord;
        y_coord = prev_y_coord;
    }

    protected final boolean alive(){        
        if(energy <= 0){
            CritterWorld.occupied[y_coord][x_coord]--;
            if(CritterWorld.occupied[y_coord][x_coord] == 0){
                CritterWorld.critterGrid[y_coord][x_coord] = "";
            }
            dead.add(this);
 //           System.out.println(this.toString() + " just ran out of energy, it died.");
            return false;
        }     
        return true;
    } 

    protected final void reproduce(Critter offspring, int direction) throws InstantiationException, IllegalAccessException {
            if(this.energy >= Params.min_reproduce_energy){
                offspring = (Critter) this.getClass().newInstance();

                offspring.x_coord = this.getX_coord();
                offspring.y_coord = this.getY_coord();
                switch (direction) {
                    case 0:
                        offspring.x_coord = (offspring.x_coord + 1) % Params.world_width;
                        break;
                    case 1:
                        offspring.x_coord = (offspring.x_coord + 1) % Params.world_width;
                        offspring.y_coord = (offspring.y_coord == 0) ? Params.world_height-1: offspring.y_coord-1;          
                        break;
                    case 2:
                        offspring.y_coord = (offspring.y_coord == 0) ? Params.world_height-1: offspring.y_coord-1; 
                        break;
                    case 3:
                        offspring.x_coord = (offspring.x_coord == 0) ? Params.world_width-1: offspring.x_coord-1;
                        offspring.y_coord = (offspring.y_coord == 0) ? Params.world_height-1: offspring.y_coord-1; 
                        break;
                    case 4:
                        offspring.x_coord = (offspring.x_coord == 0) ? Params.world_width-1: offspring.x_coord-1; 
                        break;
                    case 5:
                        offspring.x_coord = (offspring.x_coord == 0) ? Params.world_width-1: offspring.x_coord-1;
                        offspring.y_coord = (offspring.y_coord + 1) % Params.world_height;
                        break;
                    case 6:
                        offspring.y_coord = (offspring.y_coord + 1) % Params.world_height;
                        break;
                    case 7:
                        offspring.x_coord = (offspring.x_coord + 1) % Params.world_width;
                        offspring.y_coord = (offspring.y_coord + 1) % Params.world_height;
                        break;
                }

                CritterWorld.occupied[offspring.y_coord][offspring.x_coord]++;
                CritterWorld.critterGrid[offspring.y_coord][offspring.x_coord] = offspring.toString();
                offspring.energy = Math.floorDiv(this.energy, 2);
                this.energy = this.energy - offspring.energy;
                babies.add(offspring);
                CritterWorld.numCritters++;
            }
    }

    public abstract void doTimeStep();
    public abstract boolean fight(String oponent);

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
     * an InvalidCritterException must be thrown.
     * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
     * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
     * an Exception.)
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void makeCritter(String critter_class_name) throws InvalidCritterException {
        try {
            Critter c = (Critter) Class.forName(critter_class_name).newInstance();

            c.x_coord = getRandomInt(Params.world_width);
            c.y_coord = getRandomInt(Params.world_height);
            CritterWorld.occupied[c.y_coord][c.x_coord]++;
            CritterWorld.critterGrid[c.y_coord][c.x_coord] = c.toString();
            c.energy = Params.start_energy;
            population.add(c);
            c.prev_x_coord = c.x_coord;
            c.prev_y_coord = c.y_coord;
            CritterWorld.numCritters++;
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new InvalidCritterException(critter_class_name);
        } 
        
        
    }

    /**
     * Gets a list of critters of a specific type.
     * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
     * @return List of Critters.
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
        List<Critter> critters = new ArrayList<>();
        
        try {
            Class cl = Class.forName(critter_class_name);
            Critter c_temp = (Critter) cl.newInstance();

            for(Critter i : population){
                if(c_temp.getClass() == i.getClass()){
                    critters.add(i);
                }
            }

        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new InvalidCritterException(critter_class_name);
        } 
        
        return critters;
    }

    /**
     * Prints out how many Critters of each type there are on the board.
     * @param critters List of Critters.
     */
    public static void runStats(List<Critter> critters) {
        System.out.print("" + critters.size() + " critters as follows -- ");
        java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            Integer old_count = critter_count.get(crit_string);
            if (old_count == null) {
                critter_count.put(crit_string,  1);
            } else {
                critter_count.put(crit_string, old_count.intValue() + 1);
            }
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
            System.out.print(prefix + s + ":" + critter_count.get(s));
            prefix = ", ";
        }
        System.out.println();		
    }

    /**
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        // Complete this method.
        population.clear();
    }

    public static void worldTimeStep() {
        for (Critter c : population) {
            c.setHasMoved(false);
        }
        
        for (Critter c : population) {
            c.doTimeStep();
        }

        //critters list will be updated as fights occur
        for (Critter c : population) {
            for (Critter d : population) {
                if (!c.equals(d)) {
                    if (sameCoords(c,d)) {
                        boolean c_dec = c.fight(d.toString());
                        boolean d_dec = d.fight(c.toString());

                        
                        if (c.alive() && d.alive() && sameCoords(c,d)) {
                            if (((c_dec == true) ? getRandomInt(c.energy) : 0) >= ((d_dec == true) ? getRandomInt(d.energy) : 0)) {
                                c.energy = c.energy + (d.energy / 2);
                                CritterWorld.occupied[d.getY_coord()][d.getX_coord()]--;
                                CritterWorld.critterGrid[c.getY_coord()][c.getX_coord()] = c.toString();
                                dead.add(d);
                            } else {
                                d.energy = d.energy + (c.energy / 2);
                                CritterWorld.occupied[c.getY_coord()][c.getX_coord()]--;
                                CritterWorld.critterGrid[d.getY_coord()][d.getX_coord()] = d.toString();
                                dead.add(c);
                            }                           
                        }
                    }
                }              
            }
        }
        population.addAll(babies);
        population.removeAll(dead);
        CritterWorld.numCritters = CritterWorld.numCritters - dead.size();
        System.out.println("Critters alive: " + CritterWorld.numCritters);
        System.out.println("Critters dead: " + dead.size());
        System.out.println("Critters population: " + population.size());


        babies.clear();
        dead.clear();
    }

    public static void displayWorld() {
        String cap = "+ ";
        for(int i = 0; i < Params.world_width; i++){
            cap += "- ";
        }
        cap += "+";
        System.out.println(cap);

        for(int i = 0; i < Params.world_height; i++){
            System.out.print("| ");
            for(int j = 0; j < Params.world_width; j++){
                String temp = CritterWorld.critterGrid[i][j];
                if(temp.equals("")){
                    temp += " ";
                }
                System.out.print(temp + " ");
            }
            System.out.println("|");
        }
        System.out.println(cap);
    }
    
    public static boolean sameCoords(Critter a, Critter b) {
        if ((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
            return true;
        } else {
            return false;
        }               
    }
    
    /** the TestCritter class allows some critters to "cheat". If you want to 
     * create tests of your Critter model, you can create subclasses of this class
     * and then use the setter functions contained here. 
     * 
     * NOTE: you must make sure that the setter functions work with your implementation
     * of Critter. That means, if you're recording the positions of your critters
     * using some sort of external grid or some other data structure in addition
     * to the x_coord and y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     **/
    static abstract class TestCritter extends Critter {
        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }


        /*
         * This method getPopulation has to be modified by you if you are not using the population
         * ArrayList that has been provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /*
         * This method getBabies has to be modified by you if you are not using the babies
         * ArrayList that has been provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.  Babies should be added to the general population 
         * at either the beginning OR the end of every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
        
        
    }
    
    /**
     * This method getPopulation has to be modified by you if you are not using the population
     * ArrayList that has been provided in the starter code.  In any case, it has to be
     * implemented for grading tests to work.
     **/
    protected static List<Critter> getPopulation() {
        return population;
    }

    /**
     * This method getBabies has to be modified by you if you are not using the babies
     * ArrayList that has been provided in the starter code.  In any case, it has to be
     * implemented for grading tests to work.  Babies should be added to the general population 
     * at either the beginning OR the end of every timestep.
     **/
    protected static List<Critter> getBabies() {
        return babies;
    }
    
    protected void setEnergy(int new_energy_value) {
        energy = new_energy_value;
    }
    protected void setX_coord(int new_x_coord) {
        x_coord = new_x_coord;
    }
    protected void setY_coord(int new_y_coord) {
        y_coord = new_y_coord;
    }
    protected int getX_coord() {
        return x_coord;
    }
    protected int getY_coord() {
        return y_coord;
    }
    protected void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    protected boolean getHasMoved() {
        return hasMoved;
    }
    
    
}