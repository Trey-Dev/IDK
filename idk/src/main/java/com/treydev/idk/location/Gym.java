package com.treydev.idk.location;

import java.util.ArrayList;
import com.treydev.idk.*;

public class Gym extends LocationBase {
    public String getOwner() { return this.locationLeader; }

    private ArrayList<Object> critters;
    public ArrayList<Object> getCritters() { return critters; }

    public Gym(City city, long seed) {
        super(seed, city.getLevel());
        this.name = Gym.generateGymName(city);
        this.critters = new ArrayList<Object>(); // Place holder for critters in this Gym
    }

    public Gym(City city) {
        this(city, (long)(Math.random() * Long.MAX_VALUE));
    }

    private static String generateGymName(City city) {
        // Simple for now... future iterations could support thing like "Owner's Gym", "The Best City Gym", "City Arena", etc.
        return "The " + city.getName() + " Gym";
    }
}
