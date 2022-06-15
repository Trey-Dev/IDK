package com.treydev.idk;

import java.util.HashMap;
import java.util.Random;

public class Species {
    /*
     * This is a basic species class, handed to the Critter class. It contatins base stats,
     * abilities, and potential movelists.
     */
    //Name and description
    public String name;
    public String description;
    public String Ability; //TODO: WHEN ABILITY IS IMPLEMENTED, replace with ACTUAL ABILITY!
    private Species evolution;
    private Element type1;
    private Element type2;
    private HashMap<Integer,Move> Movelist;
    
     //base stats
     //TODO: remove base stats as variables? or remove from constructor? Derived from species.
     //TODO: implement nature
    private int baseAttack;
    private int baseHPStat; //NOTE! HP stat DERIVES base hit points, but it's not the SAME!!!!
    private int baseDefense;
    private int baseSpecial;
    private int baseSDefence;
    private int baseSpeed;

    static{
        initializeRandom(0); //TODO: replace with nonstubbed seed value!
    }

    private static Random random;

    private Species(int Atk, int Def, int  HP, int SpA, int Spd, int Spf, String name, String description, Species evolution, HashMap<Integer,Move> movelist, Element type1, Element type2)
    {
        this.baseAttack = Atk;
        this.baseDefense = Def;
        this.baseHPStat = HP;
        this.baseSpecial = SpA;
        this.baseSpeed = Spd;
        this.baseSDefence = Spf;
        this.name = name;
        this.description = description;
        this.Ability = "";
        this.evolution = evolution;
        this.Movelist = movelist;
        this.type1 = type1;
        this.type2 = type2;
    }

    public static void initializeRandom(long seed)
    {
        Species.random = new Random(seed);
    }

    public static Species GenRandomSpecies(int individualStatSize)
    {
        //This is the randomized constructor
        int statpoolSize = individualStatSize * 6;
        int AtkPercent = random.nextInt(100); //0 to 99
        int DefPercent = random.nextInt(100);
        int SAPercent = random.nextInt(100);
        int SpdPercent = random.nextInt(100);
        int SDPercent = random.nextInt(100);
        int HPPercent = random.nextInt(100);
        int total = AtkPercent + DefPercent + SAPercent + SpdPercent + SDPercent + HPPercent;
        int baseAttack = statpoolSize * AtkPercent / total;
        int baseDefense = statpoolSize * DefPercent / total;
        int baseSpecial = statpoolSize * SAPercent / total;
        int baseSpeed = statpoolSize * SpdPercent / total;
        int baseSDefence = statpoolSize * SDPercent / total;
        int baseHPStat = statpoolSize * HPPercent / total;
        String name = NameGenerator.generateRandomName(random.nextLong(Long.MAX_VALUE)); 
        for(int i = 0; i < 10; i++)
            name += (char) random.nextInt(50) + 65;
        String description = "STUBBED DESCRIPTION!! IMPLEMENT LATER"; 
        return new Species(baseAttack, baseDefense, baseHPStat, baseSpecial, baseSpeed, baseSDefence, name, description, null, null, null, null);
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
    public Element getTypeOne()
    {
        return this.type1;
    }
    public Element getTypeTwo()
    {
        return this.type2;
    }

    //Stubs, as method so they don't have to be instantiated.

    public static Species stubWidget()
    {
        return new Species(20, 40, 30, 30, 30, 30, "Widget", "A widgety type critter, the widget widgets widgely",null, null, null, null);
    }
    public static Species stubGadget()
    {
        return new Species( 40, 20, 40, 30, 20, 30, "Gadget", "Like, you know, a doohickey.", null, null, null, null);
    }
    public static Species stubWadget()
    {
        return new Species(1,1,1,1,1,1, "Wadget", "A failure. Don't catch it or you will be called names by your friends, and rightly so.", null, null, null, null);
    }
}