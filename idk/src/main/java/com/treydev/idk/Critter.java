package com.treydev.idk;

import java.util.ArrayList;

public class Critter {
    //private static ArrayList<Critter> critters = new ArrayList<Critter>();

    public String name;
    public int baseAttack;
    public int health;
    public Move[] moveset;

    public Critter(String name, int baseAttack, int Health, Move[] moveset)
    {
        this.baseAttack = baseAttack;
        this.health = Health;
        this.name = name;
        this.moveset = moveset;
    }

    public static ArrayList<Critter> initializeStubbedParty()
    {
        ArrayList<Critter> value = new ArrayList<>();
        value.add(new Critter("Widget", 10, 5, new Move[4]));
        value.get(0).moveset[0] = new Move("just punch him I guess") {

            @Override
            public void Execute(ArrayList<String> outputs, Critter target, Critter user) {
                outputs.add("You used" + this.name);
                target.health -= 15;
            }
            
        };
        value.add(new Critter("Gadget", 5, 10, new Move[4]));
        value.get(1).moveset[0] = new Move("just punch him I guess") {

            @Override
            public void Execute(ArrayList<String> outputs, Critter target) {
                outputs.add("You used" + this.name);
                target.health -= 15;
            }
            
        };
        return value;
    }
}
