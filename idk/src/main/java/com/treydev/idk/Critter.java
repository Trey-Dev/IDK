package com.treydev.idk;

import java.util.ArrayList;

public class Critter {
    private static ArrayList<Critter> critters = new ArrayList<Critter>();

    public String name;
    public int baseAttack;
    public int baseDefense;

    private Critter(String name, int baseAttack, int baseDefense)
    {
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.name = name;
    }

    public Critter()
    {
        // Initialize critter library
        critters.add(new Critter("Widget", 10, 5));
        critters.add(new Critter("Gadget", 5, 10));
    }

    public static Critter GetRandomCritter()
    {
        int random = (int)(Math.random() * critters.size());
        Critter critter = Critter.critters.get(random);
        critter.baseAttack += (int)(Math.random() - Math.random())*5;
        critter.baseDefense += (int)(Math.random() - Math.random())*5;
        return critter;
    }
}
