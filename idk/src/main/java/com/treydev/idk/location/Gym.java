package com.treydev.idk.location;

import java.util.ArrayList;

import com.treydev.idk.support.ElementAffinity;
import com.treydev.idk.support.NameGenerator;
import com.treydev.idk.attack.Move;
import com.treydev.idk.critter.Critter;
import com.treydev.idk.critter.Species;

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
        // Capture basic attributes
        super(seed, city.getLevel());
        this.name = Gym.generateGymName(city);
        this.city = city;

        // Generate the critter list
        int critterPower = city.getLevel() / 5 + 20;
        this.critters = new ArrayList<Critter>();
        int numCritters = 1 + (int)(city.getLevel()/20);
        if (numCritters > 6) numCritters=6;
        for (int i = 0; i < numCritters; i++) {
            // TODO: This should call a method to allow existing species and not only new ones
            Species s = Species.GenRandomSpecies(critterPower, ElementAffinity.GetRandomAffinity()[0].getElement());
            String name = NameGenerator.generateRandomName(seed);
            Critter c = new  Critter(name, s, new Move[4],this.city.getLevel());
            this.critters.add(c);
        }
        Gym.allGyms.add(this);
    }

    public Gym(City city) {
        this(city, (long) (Math.random() * Long.MAX_VALUE));
    }

    private static String generateGymName(City city) {
        // Simple for now... future iterations could support thing like "Owner's Gym",
        // "The Best City Gym", "City Arena", etc.
        return city.getName() + " Gym";
    }

    public static Gym getById(int id) {
        return Gym.allGyms.get(id);
    }

    @Override
    public int getId() {
        return Gym.allGyms.indexOf(this);
    }
}
