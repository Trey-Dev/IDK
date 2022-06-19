package com.treydev.idk.location;

import java.util.ArrayList;
import com.treydev.idk.critter.Critter;

public class Gym extends LocationBase {
    private static ArrayList<Gym> allGyms = new ArrayList<Gym>();

    public static void clearGyms() {
        allGyms.clear();
    }

    public String getOwner() {
        return this.locationLeader;
    }

    private ArrayList<Critter> critters;

    public ArrayList<Critter> getCritters() {
        return critters;
    }

    private City city;

    public City getCity() {
        return city;
    }

    public Gym(City city, long seed) {
        super(seed, city.getLevel());
        this.name = Gym.generateGymName(city);
        this.city = city;
        this.critters = new ArrayList<Critter>();
        Gym.allGyms.add(this);
        // TODO: Populate Gym critters
    }

    public Gym(City city) {
        this(city, (long) (Math.random() * Long.MAX_VALUE));
    }

    private static String generateGymName(City city) {
        // Simple for now... future iterations could support thing like "Owner's Gym",
        // "The Best City Gym", "City Arena", etc.
        return "The " + city.getName() + " Gym";
    }

    public static Gym getById(int id) {
        return Gym.allGyms.get(id);
    }

    @Override
    public int getId() {
        return Gym.allGyms.indexOf(this);
    }
}
