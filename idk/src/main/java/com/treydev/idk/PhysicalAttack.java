package com.treydev.idk;
import java.util.ArrayList;
//import jakarta.security.auth.message.MessagePolicy.Target; //WHY THE HECK DID I FIND THIS HERE?

public class PhysicalAttack extends Move {
    /*
     * There are three types of "Move": physical attacks,
     * special attacks, and status moves. This is the 
     * physical attacks section, dealing with punches, kicks
     * and the like. These use "atk" and "def" stats to deal
     * damage.
     */
    private int Power, Accuracy;

    public PhysicalAttack(String name, Element MoveType, int Power, int Accuracy)
    {
        super(name, MoveType);
        this.Accuracy = Accuracy;
        this.Power = Power;
    }

    @Override
    public void Execute(ArrayList<String> outputs, Critter target, Critter user) {
        outputs.add("You used " + this.name);
        if(checkHit())
        {
            dealDamage(outputs, target, user);
            
        }
        else
            outputs.add("You missed!");
    }

    private void dealDamage(ArrayList<String> outputs, Critter target, Critter user) {
        outputs.add("You hit!");
        //TODO: (optional) move actual damage calculation to yet another equation so the equation can be overridden.
        int attack = user.species.getBaseAttack();//TODO: replace base attack with actual attack!
        int defense = target.species.getBaseDefense();
        int level = 50;//TODO: REPLACE WITH ACTUAL LEVEL!
        int damage = Power;
        damage *= attack;
        damage *= 2 * (level + 10);
        damage /= (defense * 250);
        damage += 2;
        damage = (int)((float) damage * Element.checkEffectiveness(this.moveType, target.species.getTypeOne(), target.species.getTypeTwo()));
        outputs.add("Dealt " + damage + " damage");
        target.hitpoints -= damage;
        outputs.add("Opponent's health: "+ target.hitpoints);
        //TODO: Tweak this code until we have something that works!
    }

    private boolean checkHit()
    {
        return (Math.random() * 100 < Accuracy); //TODO: Replace with Util.random
        //TODO: check and test this and the damage function. Tweak until it feels nice.
    }
    //TODO: create another class that implements additional effects
    //    as a method of the yet unimplemented interface or class.
}
