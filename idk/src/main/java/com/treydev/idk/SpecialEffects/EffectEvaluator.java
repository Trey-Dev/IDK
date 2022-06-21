package com.treydev.idk.SpecialEffects;

import java.util.ArrayList;
import java.util.Random;

import com.treydev.idk.critter.Critter;

public abstract class EffectEvaluator {
    
    private static Random random;
    private static ArrayList<EffectEvaluator> evaluators;
static{
    Long seed = (long) 0;
    random = new Random(seed);
    evaluators = new ArrayList<EffectEvaluator>();
    //"Always"

}
    
    public abstract boolean isEffected(Critter target, Critter user);
    protected int cost; //immutable
    protected String description; //immutable
    public int getCost() { return cost; }
    public EffectEvaluator(int cost, String description) { 
        this.cost = cost;
        this.description = description;
    }
    public String getDescription() { return description; }
    public static EffectEvaluator getEffectEvaluator()
    {
        return new EffectEvaluator(0, "") {
            public boolean isEffected(Critter target, Critter user) { return true; }
        }; //default effect evaluator
    }


}
