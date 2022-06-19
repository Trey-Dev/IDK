package com.treydev.idk.critter;

import java.util.ArrayList;

import com.treydev.idk.attack.Move;

public class Critter {
    public String name;
    private int level; // TODO: Watch the keyword "private". If it's not useful, don't keep it private.
    public Move[] moveset;
    public int hitpoints;
    private int maxHitpoints;
    private int AttackStage;
    private int SpecialAttackStage;
    private int DefStage;
    private int SpecDefStage;
    private int speedStage;

    public Species species; // TODO: make private, immutable

    // Constructor (Public for now)
    // TODO: make private constructor, factory methods?
    public Critter(String name, Species species, Move[] moveset, int Level) {
        this.name = name;
        this.moveset = moveset;
        this.species = species;
        this.hitpoints = this.species.getBaseHitPoints();
        this.level = Level;
    }

    public static ArrayList<Critter> initializeStubbedParty() {
        ArrayList<Critter> value = new ArrayList<>();
        value.add(new Critter("Widget", Species.stubWidget(), Move.implementStubbedMoveList(), 5));
        value.add(new Critter("Gadget", Species.stubGadget(), new Move[4], 5));
        value.get(1).moveset[0] = Move.getRandomMove();
        return value;
    }

    // Return critters' status to normal at the beginning of combat.
    public void enterCombat() // NOTE: could also be endCombat()
    {
        // Zero the "stages", returning stats to base values.
        this.AttackStage = 0;
        this.DefStage = 0;
        this.SpecDefStage = 0;
        this.SpecialAttackStage = 0;
        this.speedStage = 0;
        // TODO: CLEAR Status Effects
    }
    // TODO: Rest function that returns critters to max HP.

    // internal logic for base stat calculation in accessors
    private float getPercent(int stage) {
        if (stage > 0)
            return (2f + stage) / 2;
        return 2f / (2 - stage);
    }

    // "Accessors" for base stats! (note, these aren't the exact same as accessors,
    // because these aren't their own variables.)
    public int getAttack() {
        return (int) ((float) (species.getBaseAttack() * 1) * getPercent(AttackStage));
        // TODO: The "* 1" is for the STUBBED nature!!!!!
    }

    public int getDefence() {
        return (int) ((float) (species.getBaseDefense() * 1) * getPercent(DefStage));
    }

    public int getSpeed() {
        return (int) ((float) (species.getBaseSpeed() * 1) * getPercent(speedStage));
    }

    public int getSpecial() {
        return (int) ((float) (species.getBaseSpecial() * 1) * getPercent(SpecialAttackStage));
    }

    public int getSpecDef() {
        return (int) ((float) (species.getBaseSDefence() * 1) * getPercent(SpecDefStage));
    }

    private int getMaxHP() {
        return species.getBaseHitPoints(); // TODO: actually CALCULATE base HP!!!
    }

    // level functions.
    public void levelUp() {
        this.level++;
        // TODO: scan movelist, check evolution.
    }

    public int getLevel() {
        return level;
    }

    // Stat stage modifiers
    public void increaseAtk() {
        AttackStage = Math.min(AttackStage + 1, 6);
    }

    public void decreaseAtk() {
        AttackStage = Math.max(AttackStage - 1, -6);
    }

    public void increaseDefense() {
        DefStage = Math.min(DefStage + 1, 6);
    }

    public void decreaseDefense() {
        DefStage = Math.max(DefStage - 1, -6);
    }

    public void increaseSpAtk() {
        SpecialAttackStage = Math.min(SpecialAttackStage + 1, 6);
    }

    public void decreaseSpAtk() {
        SpecialAttackStage = Math.max(SpecialAttackStage - 1, -6);
    }

    public void increaseSpDefense() {
        SpecDefStage = Math.min(SpecDefStage + 1, 6);
    }

    public void decreaseSpDefense() {
        SpecDefStage = Math.max(SpecDefStage - 1, -6);
    }

    public void increaseSpeed() {
        speedStage = Math.min(speedStage + 1, 6);
    }

    public void decreaseSpeed() {
        speedStage = Math.max(speedStage - 1, -6);
    }

}
