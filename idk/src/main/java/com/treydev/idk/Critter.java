package com.treydev.idk;

import java.util.ArrayList;

public class Critter {
    

    public String name;
    //TODO: IMPLEMENT LEVEL!!!
    public Move[] moveset;
    public int hitpoints;
    public Species species; //TODO: make private, immutable

    public Critter(String name, Species species,  Move[] moveset)
    {
        // this.baseAttack = baseAttack;
        // this.health = Health;
        this.name = name;
        this.moveset = moveset;
        this.species = species;
        this.hitpoints = this.species.getBaseHitPoints();
    }

    public static ArrayList<Critter> initializeStubbedParty()
    {
        ArrayList<Critter> value = new ArrayList<>();
        value.add(new Critter("Widget", Species.stubWidget(), Move.implementStubbedMoveList()));
        // value.get(0).moveset[0] = new Move("just punch him I guess") {

        //     @Override
        //     public void Execute(ArrayList<String> outputs, Critter target, Critter user) {
        //         outputs.add("You used" + this.name);
        //         target.health -= 15;
        //     }
            
        //};
        value.add(new Critter("Gadget", Species.stubGadget(), new Move[4]));
        value.get(1).moveset[0] = new Move("just punch him I guess") {

            @Override
            public void Execute(ArrayList<String> outputs, Critter target, Critter user) {
                outputs.add("You used" + this.name);
                target.hitpoints -= 15;
            }
            
        };
        return value;
    }
}
