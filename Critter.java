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


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
    private static String myPackage;
    private	static List<Critter> population = new java.util.ArrayList<Critter>();
    private static List<Critter> babies = new java.util.ArrayList<Critter>();

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
    protected int getEnergy() { return energy; }

    private int x_coord;
    private int y_coord;
    private boolean hasMoved;

    protected final void walk(int direction) {
        energy = energy - Params.walk_energy_cost;
        
        switch (direction) {
            case 0:
                x_coord = x_coord + 1;
                break;
            case 1:
                x_coord = x_coord + 1;
                y_coord = y_coord - 1;
                break;
            case 2:
                y_coord = y_coord - 1;
                break;
            case 3:
                x_coord = x_coord - 1;
                y_coord = y_coord - 1;
                break;
            case 4:
                x_coord = x_coord - 1;
                break;
            case 5:
                x_coord = x_coord - 1;
                y_coord = y_coord + 1;
                break;
            case 6:
                y_coord = y_coord - 1;
                break;
            case 7:
                x_coord = x_coord + 1;
                y_coord = y_coord + 1;
                break;
        }

    }

    protected final void run(int direction) {
        energy = energy - Params.run_energy_cost;
        
        switch (direction) {
            case 0:
                x_coord = x_coord + 2;
                break;
            case 1:
                x_coord = x_coord + 2;
                y_coord = y_coord - 2;
                break;
            case 2:
                y_coord = y_coord - 2;
                break;
            case 3:
                x_coord = x_coord - 2;
                y_coord = y_coord - 2;
                break;
            case 4:
                x_coord = x_coord - 2;
                break;
            case 5:
                x_coord = x_coord - 2;
                y_coord = y_coord + 2;
                break;
            case 6:
                y_coord = y_coord - 2;
                break;
            case 7:
                x_coord = x_coord + 2;
                y_coord = y_coord + 2;
                break;
        }
    }

    protected final void undoWalk(int direction) {
        
        switch (direction) {
            case 0:
                x_coord = x_coord - 1;
                break;
            case 1:
                x_coord = x_coord - 1;
                y_coord = y_coord + 1;
                break;
            case 2:
                y_coord = y_coord + 1;
                break;
            case 3:
                x_coord = x_coord + 1;
                y_coord = y_coord + 1;
                break;
            case 4:
                x_coord = x_coord + 1;
                break;
            case 5:
                x_coord = x_coord + 1;
                y_coord = y_coord - 1;
                break;
            case 6:
                y_coord = y_coord + 1;
                break;
            case 7:
                x_coord = x_coord - 1;
                y_coord = y_coord - 1;
                break;
        }

    }

    protected final void undoRun(int direction) {
        
        switch (direction) {
            case 0:
                x_coord = x_coord - 2;
                break;
            case 1:
                x_coord = x_coord - 2;
                y_coord = y_coord + 2;
                break;
            case 2:
                y_coord = y_coord + 2;
                break;
            case 3:
                x_coord = x_coord + 2;
                y_coord = y_coord + 2;
                break;
            case 4:
                x_coord = x_coord + 2;
                break;
            case 5:
                x_coord = x_coord + 2;
                y_coord = y_coord - 2;
                break;
            case 6:
                y_coord = y_coord + 2;
                break;
            case 7:
                x_coord = x_coord - 2;
                y_coord = y_coord - 2;
                break;
        }
    }

    protected final void alive(){
        if(energy <= 0){
            CritterWorld.occupied[x_coord][y_coord]--;
            population.remove(this);
        }
    } 

    protected final void reproduce(Critter offspring, int direction) throws InstantiationException, IllegalAccessException {

            offspring = (Critter) this.getClass().newInstance();
            
            offspring.x_coord = getRandomInt(this.getX_coord() + getRandomInt(2) - 1);
            offspring.y_coord = getRandomInt(this.getY_coord() + getRandomInt(2) - 1);
            CritterWorld.occupied[offspring.x_coord][offspring.y_coord]++;
            CritterWorld.critterGrid[offspring.x_coord][offspring.y_coord] = offspring.toString();
            offspring.energy = Math.floorDiv(this.energy, 2);
            this.energy = this.energy - offspring.energy;
            babies.add(offspring);
        
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
            Class cl = Class.forName(critter_class_name);
            Critter c = (Critter) cl.newInstance();

            c.x_coord = getRandomInt(Params.world_width);
            c.y_coord = getRandomInt(Params.world_height);
            CritterWorld.occupied[c.x_coord][c.y_coord]++;
            //CritterWorld.critterGrid[c.x_coord][c.y_coord] = c.toString();
            c.energy = Params.start_energy;
            population.add(c);

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
        List<Critter> result = new java.util.ArrayList<>();

        return result;
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

    /* the TestCritter class allows some critters to "cheat". If you want to 
     * create tests of your Critter model, you can create subclasses of this class
     * and then use the setter functions contained here. 
     * 
     * NOTE: you must make sure that the setter functions work with your implementation
     * of Critter. That means, if you're recording the positions of your critters
     * using some sort of external grid or some other data structure in addition
     * to the x_coord and y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
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
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        // Complete this method.
        population.clear();
    }

    public static void worldTimeStep() {
        // Complete this method.
        for (Critter c : population) {
            c.doTimeStep();
            if (c.energy <= 0) {
                population.remove(c);
            }          
        }

        //critters list will be updated as fights occur
        for (Critter c : population) {
            for (Critter d : population) {
                if (!c.equals(d)) {
                    if (sameCoords(c,d)) {
                        c.fight(d.toString());
                        d.fight(c.toString());
                        c.alive();
                        d.alive();
                        if (population.contains(c) && population.contains(d) && sameCoords(c,d)) {
                            if (getRandomInt(c.energy) >= getRandomInt(d.energy)) {
                                c.energy = c.energy + (d.energy / 2);
                                CritterWorld.occupied[d.getX_coord()][d.getY_coord()]--;
                                population.remove(d);
                            } else {
                                d.energy = d.energy + (c.energy / 2);
                                CritterWorld.occupied[c.getX_coord()][c.getY_coord()]--;
                                population.remove(c);
                            }                            
                        }
                    }
                }
                
            }
        }
        population.addAll(babies);
        babies.clear();

    }

    public static void displayWorld() {
        // Complete this method.
    }
    
    public static boolean sameCoords(Critter a, Critter b) {
        if ((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
            return true;
        } else {
            return false;
        }               
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
    
    protected void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    protected boolean getHasMoved() {
        return hasMoved;
    }
    
    
}
