package com.treydev.idk.SpecialEffects;

import com.treydev.idk.critter.Critter;

public abstract class EffectEvaluator {
    
    public abstract boolean isEffected(Critter target, Critter user);
    protected int cost; //immutable
    public int getCost() { return cost; }
    public EffectEvaluator(int cost) { this.cost = cost; }
    public static EffectEvaluator generateEffectEvaluator()
    {
        return new EffectEvaluator(0) {
            public boolean isEffected(Critter target, Critter user) { return true; }
        }; //stubbed effect evaluator
    }
}
