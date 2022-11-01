package com.treydev.idk.critter;

import java.util.HashMap;

import com.treydev.idk.attack.Move;
import com.treydev.idk.support.Element;
import com.treydev.idk.support.NameGenerator;
import com.treydev.idk.support.Random;

public class Species {
    /*
     * This is a basic species class, handed to the Critter class. It contatins base
     * stats,
     * abilities, and potential movelists.
     */
    // Name and description
    public String name;
    public String description;
    public String Ability; // TODO: WHEN ABILITY IS IMPLEMENTED, replace with ACTUAL ABILITY!
    private Species evolution;
    private Element type1; // TODO: array?
    private Element type2;
    private HashMap<Integer, Move> Movelist;

    // base stats
    // TODO: remove base stats as variables? or remove from constructor? Derived
    // from species.
    // TODO: implement nature
    private int baseAttack;
    private int baseHPStat; // NOTE! HP stat DERIVES base hit points, but it's not the SAME!!!!
    private int baseDefense;
    private int baseSpecial;
    private int baseSDefence;
    private int baseSpeed;

    private Species(int Atk, int Def, int HP, int SpA, int Spd, int Spf, String name, String description,
            Species evolution, HashMap<Integer, Move> movelist, Element type1, Element type2) {
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

    public static Species GenRandomSpecies(int individualStatSize) {
        Element e = Element.getRandomElement();
        return Species.GenRandomSpecies(individualStatSize, e);
    }

    public static Species GenRandomSpecies(int individualStatSize, Element primaryElement) {
        // This is the randomized constructor
        int statpoolSize = individualStatSize * 6;
        int AtkPercent = Random.nextInt(100); // 0 to 99
        int DefPercent = Random.nextInt(100);
        int SAPercent = Random.nextInt(100);
        int SpdPercent = Random.nextInt(100);
        int SDPercent = Random.nextInt(100);
        int HPPercent = Random.nextInt(100);
        int total = AtkPercent + DefPercent + SAPercent + SpdPercent + SDPercent + HPPercent;
        int baseAttack = statpoolSize * AtkPercent / total + 1;
        int baseDefense = statpoolSize * DefPercent / total + 1;
        int baseSpecial = statpoolSize * SAPercent / total + 1;
        int baseSpeed = statpoolSize * SpdPercent / total + 1;
        int baseSDefence = statpoolSize * SDPercent / total + 1;
        int baseHPStat = statpoolSize * HPPercent / total + 1;
        String name = NameGenerator.generateRandomName();
        String description = "STUBBED DESCRIPTION!! IMPLEMENT LATER";

        Element secondaryElement = Element.getRandomElement();
        if ((Random.nextInt(100) < 50) || (secondaryElement == primaryElement)) {
            secondaryElement = null;
        }

        return new Species(baseAttack, baseDefense, baseHPStat, baseSpecial, baseSpeed, baseSDefence, name, description,
                null, null, primaryElement, secondaryElement);
    }

    public static Species findSpecies()
    {
        Element e = Element.getRandomElement();
        return findSpecies(e);
    }

    public static Species findSpecies(Element e)
    {
        //TODO: get something random from list of species, or generate species
        return null;
    }

    // base stat accessors, NO MUTATORS! these variables are IMMUTABLE!
    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseHitPoints() {
        // TODO: CALCULATE BASE HIT POINTS
        return baseHPStat;
    }

    public int getBaseSpecial() {
        return baseSpecial;
    }

    public int getBaseSDefence() {
        return baseSDefence;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public Element getTypeOne() {
        return this.type1;
    }

    public Element getTypeTwo() {
        return this.type2;
    }

    // Stubs, as method so they don't have to be instantiated.
    public static Species stubWidget() {
        return new Species(20, 40, 30, 30, 30, 30, "Widget", "A widgety type critter, the widget widgets widgely", null,
                null, null, null);
    }

    public static Species stubGadget() {
        return new Species(40, 20, 40, 30, 20, 30, "Gadget", "Like, you know, a doohickey.", null, null, null, null);
    }

    public static Species stubWadget() {
        return new Species(1, 1, 1, 1, 1, 1, "Wadget",
                "A failure. Don't catch it or you will be called names by your friends, and rightly so.", null, null,
                null, null);
    }
}