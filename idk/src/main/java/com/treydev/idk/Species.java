package com.treydev.idk;

import java.util.Random;

public class Species {
    /*
     * This is a basic species class, handed to the Critter class. It contatins base stats,
     * abilities, and potential movelists.
     */
    //Name and description
    public String name;
    public String description;
    //TODO implement types!!!
    
     //base stats
    private int baseAttack;
    private int baseHPStat; //NOTE! HP stat DERIVES base hit points, but it's not the SAME!!!!
    private int baseDefense;
    private int baseSpecial;
    private int baseSDefence;
    private int baseSpeed;


    private static Random random;

    public Species(int Atk, int Def, int  HP, int SpA, int Spd, int Spf, String name, String description)
    {
        this.baseAttack = Atk;
        this.baseDefense = Def;
        this.baseHPStat = HP;
        this.baseSpecial = SpA;
        this.baseSpeed = Spd;
        this.baseSDefence = Spf;
        this.name = name;
        this.description = description;
    }

    public static void initializeRandom(long seed)
    {
        Species.random = new Random(seed);
    }

    //TODO: implement static method that generates evolutions with differing statpools
    public Species(int individualStatSize)
    {
        //This is the randomized constructor
        int statpoolSize = individualStatSize * 6;
        if(Species.random == null) initializeRandom(0); //TODO: NOTE THIS IS STUBBED CODE WITH SEED 0
        int AtkPercent = random.nextInt(100); //0 to 99
        int DefPercent = random.nextInt(100);
        int SAPercent = random.nextInt(100);
        int SpdPercent = random.nextInt(100);
        int SDPercent = random.nextInt(100);
        int HPPercent = random.nextInt(100);
        int total = AtkPercent + DefPercent + SAPercent + SpdPercent + SDPercent + HPPercent;
        this.baseAttack = statpoolSize * AtkPercent / total;
        this.baseDefense = statpoolSize * DefPercent / total;
        this.baseSpecial = statpoolSize * SAPercent / total;
        this.baseSpeed = statpoolSize * SpdPercent / total;
        this.baseSDefence = statpoolSize * SDPercent / total;
        this.baseHPStat = statpoolSize * HPPercent / total;
        // String name = ""; //TODO: implement generate name function!!!
        for(int i = 0; i < 10; i++)
            name += (char) random.nextInt(50) + 65;
        this.description = "STUBBED DESCRIPTION!! IMPLEMENT LATER"; //TODO: implement description generator!!
    }

    //base stat accessors, NO MUTATORS! these variables are IMMUTABLE!
    public int getBaseAttack()
    {
        return baseAttack;
    }
    public int getBaseDefense()
    {
        return baseDefense;
    }
    public int getBaseHitPoints()
    {
        //TODO: CALCULATE BASE HIT POINTS
        return baseHPStat;
    }
    public int getBaseSpecial()
    {
        return baseSpecial;
    }
    public int getBaseSDefence()
    {
        return baseSDefence;
    }
    public int getBaseSpeed()
    {
        return baseSpeed;
    }

    //Stubs, as method so they don't have to be instantiated.
    public static Species stubWidget()
    {
        return new Species(20, 40, 30, 30, 30, 30, "Widget", "A widgety type critter, the widget widgets widgely");
    }
    public static Species stubGadget()
    {
        return new Species( 40, 20, 40, 30, 20, 30, "Gadget", "Like, you know, a doohickey.");
    }
    public static Species stubWadget()
    {
        return new Species(1,1,1,1,1,1, "Wadget", "A failure. Don't catch it or you will be called names by your friends, and rightly so.");
    }

}