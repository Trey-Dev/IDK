package com.treydev.idk;

import java.util.ArrayList;

import jakarta.security.auth.message.MessagePolicy.Target;

public abstract class PhysicalAttack extends Move {

    private int Power, Accuracy;

    public PhysicalAttack(String name, int Power, int Accuracy)
    {
        super(name);
        this.Accuracy = Accuracy;
        this.Power = Power;
    }

    @Override
    public void Execute(ArrayList<String> outputs, Critter target) {
        outputs.add("You used " + this.name);
        //TODO: THIS IS STUBBED CODE, replace later.
        if(checkHit())
        {
            target.health -= this.Power;
            this.AddEffects(outputs,target);
        }
    }

    private boolean checkHit()
    {
        return (Math.random() * 100 < Accuracy);
    }
    protected abstract void AddEffects(ArrayList<String> outputs, Critter Target);
}
