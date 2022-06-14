package com.treydev.idk.location;

import java.util.ArrayList;
import com.treydev.idk.*;

public class Gym {

    private String name;
    public String getName() { return name; }

    private int level;
    public int getLevel() { return level; }

    private String owner;
    public String getOwner() { return owner; }

    private ArrayList<Object> critters;
    public ArrayList<Object> getCritters() { return critters; }

    public Gym(City city, long seed) {
        this.name = Gym.generateGymName(city);
        this.level = city.getLevel();
        this.owner = NameGenerator.generateRandomName(seed);
        this.critters = new ArrayList<Object>(); // Place holder for critters
    }

    public Gym(City city) {
        this(city, (long)(Math.random() * Long.MAX_VALUE));
    }

    private static String generateGymName(City city) {
        // Simple for now... future iterations could support thing like "Owner's Gym", "The Best City Gym", "City Arena", etc.
        return "The " + city.getName() + " Gym";
    }
}
