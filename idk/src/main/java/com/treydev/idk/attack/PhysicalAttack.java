package com.treydev.idk.attack;

import java.util.ArrayList;

import com.treydev.idk.critter.Critter;
import com.treydev.idk.support.Element;
import com.treydev.idk.support.Random;

public class PhysicalAttack extends Move {
    /*
     * There are three types of "Move": physical attacks,
     * special attacks, and status moves. This is the
     * physical attacks section, dealing with punches, kicks
     * and the like. These use "atk" and "def" stats to deal
     * damage.
     */
    private int Power, Accuracy;

    public PhysicalAttack(String name, Element MoveType, int Power, int Accuracy) {
        super(name, MoveType);
        this.Accuracy = Accuracy;
        this.Power = Power;
    }

    @Override
    public void Execute(ArrayList<String> outputs, Critter target, Critter user) {
        outputs.add("You used " + this.name);
        if (checkHit()) {
            dealDamage(outputs, target, user);

        } else
            outputs.add("You missed!");
    }

    private void dealDamage(ArrayList<String> outputs, Critter target, Critter user) {
        outputs.add("You hit!");
        // TODO: (optional) move actual damage calculation to yet another equation so
        // the equation can be overridden.
        int attack = user.getAttack();// TODO: replace base attack with actual attack!
        int defense = target.getDefense();
        int level = 50;// TODO: REPLACE WITH ACTUAL LEVEL!
        int damage = Power;
        damage *= attack;
        damage *= 2 * (level + 10);
        damage /= (defense * 250);
        damage += 2;
        damage = (int) ((float) damage
                * Element.checkEffectiveness(this.moveType, target.species.getTypeOne(), target.species.getTypeTwo()));
        outputs.add("Dealt " + damage + " damage");
        target.hitpoints -= damage;
        outputs.add("Opponent's health: " + target.hitpoints);
        // TODO: Tweak this code until we have something that works!
    }

    private boolean checkHit() {
        return (Random.nextInt(100) < Accuracy);
        // TODO: check and test this and the damage function. Tweak until it feels nice.
    }
    // TODO: create another class that implements additional effects
    // as a method of the yet unimplemented interface or class.
}
