package com.treydev.idk;

import java.util.ArrayList;

public class Critter {

    

    public String name;
    private int Level; //TODO: Watch the keyword "private". If it's not useful, don't keep it private.
    public Move[] moveset;
    public int hitpoints;
    private int maxHitpoints;
    private int AttackStage;
    private int SpecialAttackStage;
    private int DefStage;
    private int SpecDefStage; 
    private int speedStage;

    public Species species; //TODO: make private, immutable

    //Constructor (Public for now)
    //TODO: make private constructor, factory methods?
    public Critter(String name, Species species,  Move[] moveset, int Level)
    {
        this.name = name;
        this.moveset = moveset;
        this.species = species;
        this.hitpoints = this.species.getBaseHitPoints();
        this.Level = Level;
    }
    public static ArrayList<Critter> initializeStubbedParty()
    {
        ArrayList<Critter> value = new ArrayList<>();
        value.add(new Critter("Widget", Species.stubWidget(), Move.implementStubbedMoveList(), 5));
        value.add(new Critter("Gadget", Species.stubGadget(), new Move[4], 5));
        value.get(1).moveset[0] = Move.getRandomMove();
        return value;
    }
    
    //Return critters' status to normal at the beginning of combat.
    public void enterCombat()
    {
        //Zero the "stages", returning stats to base values.
        this.AttackStage = 0;
        this.DefStage = 0;
        this.SpecDefStage = 0;
        this.SpecialAttackStage = 0;
        this.speedStage = 0;
        //TODO: CLEAR Status Effects
    }
    //TODO: Rest function that returns critters to max HP.

    //internal logic for base stat calculation in accessors
    private float getPercent(int stage)
    {
        //TODO: IMPLEMENT ACTUAL STAT EQUATION! (2/2 = 1 base, add 1 to numerator for each 
        //      positive stage, denominator for negative)
        return (5 + stage) / 5;
    }
    //"Accessors" for base stats! (note, these aren't the exact same as accessors, because these aren't their own variables.)
    public int getAttack()
    {
        return (int) ((float) (species.getBaseAttack() * 1) * getPercent(AttackStage)); 
        //TODO: The "* 1" is for the STUBBED nature!!!!!
    }
    public int getDefence()
    {
        return (int) ((float) (species.getBaseDefense() * 1) * getPercent(DefStage)); 
    }
    public int getSpeed()
    {
        return (int) ((float) (species.getBaseSpeed() * 1) * getPercent(speedStage)); 
    }
    public int getSpecial()
    {
        return (int) ((float) (species.getBaseSpecial() * 1) * getPercent(SpecialAttackStage)); 
    }
    public int getSpecDef()
    {
        return (int) ((float) (species.getBaseSDefence() * 1) * getPercent(SpecDefStage)); 
    }
    private int getMaxHP()
    {
        return species.getBaseHitPoints(); //TODO: actually CALCULATE base HP!!!
    }

}
