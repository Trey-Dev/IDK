package com.treydev.idk;

public class Species {
    /*
     * This is a basic species class, handed to the Critter class. It contatins base stats,
     * abilities, and potential movelists.
     */
    //base stats
    private int baseAttack;
    private int baseHPStat; //NOTE! HP stat DERIVES base hit points, but it's not the SAME!!!!
    private int baseDefense;
    private int baseSpecial;
    private int baseSDefence;
    private int baseSpeed;

    public Species(int Atk, int Def, int  HP, int SpA, int Spd, int Spf)
    {
        this.baseAttack = Atk;
        this.baseDefense = Def;
        this.baseHPStat = HP;
        this.baseSpecial = SpA;
        this.baseSpeed = Spd;
        this.baseSDefence = Spf;
    }

    //TODO: NULL SPECIES constructor, randomizes
    
    

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
        return new Species(20, 40, 30, 30, 30, 30);
    }
    public static Species stubGadget()
    {
        return new Species( 40, 20, 40, 30, 20, 30);
    }
    public static Species stubWadget()
    {
        return new Species(1,1,1,1,1,1);
    }


}
