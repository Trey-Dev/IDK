package com.treydev.idk;
import java.util.ArrayList;
import jakarta.security.auth.message.MessagePolicy.Target;

public abstract class PhysicalAttack extends Move {
    /*
     * There are three types of "Move": physical attacks,
     * special attacks, and status moves. This is the 
     * physical attacks section, dealing with punches, kicks
     * and the like. These use "atk" and "def" stats to deal
     * damage.
     */
    private int Power, Accuracy;

    public PhysicalAttack(String name, int Power, int Accuracy)
    {
        super(name);
        this.Accuracy = Accuracy;
        this.Power = Power;
    }

    @Override
    public void Execute(ArrayList<String> outputs, Critter target, Critter user) {
        outputs.add("You used " + this.name);
        if(checkHit())
        {
            outputs.add("You hit!");
            target.health -= (this.Power + user.baseAttack); //TODO: replace base attack with actual attack!
            this.AddEffects(outputs,target, user);
            outputs.add("Opponent's health: "+ target.health);
        }
        else
            outputs.add("You missed!");
    }

    private boolean checkHit()
    {
        return (Math.random() * 100 < Accuracy);
    }
    protected abstract void AddEffects(ArrayList<String> outputs, Critter Target, Critter user);
}
