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
            //TODO: Move this to another function, so it can be modified independently.
            outputs.add("You hit!");
            target.hitpoints -= (this.Power + user.species.getBaseAttack()); //TODO: replace base attack with actual attack!
            //this.AddEffects(outputs,target, user);
            outputs.add("Opponent's health: "+ target.hitpoints);
            //TODO: Modify this code until we have something that works!
        }
        else
            outputs.add("You missed!");
    }

    private boolean checkHit()
    {
        return (Math.random() * 100 < Accuracy);
        //TODO: check and test this and the damage function. Tweak until it feels nice.
    }
    //TODO: create another class that implements additional effects.
    //TODO: IN OTHER CLASS (Copy over when new class is implemented) add additional effects 
    //    as a method of the yet unimplemented interface or class.
}
