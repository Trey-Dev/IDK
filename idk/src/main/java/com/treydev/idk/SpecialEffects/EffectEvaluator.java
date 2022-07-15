package com.treydev.idk.SpecialEffects;

import java.util.ArrayList;
import java.util.Random;

import com.treydev.idk.critter.Critter;

public abstract class EffectEvaluator {
    
    private static Random random;
    private static ArrayList<EffectEvaluator> evaluators;
    //TODO: Specific lists of effects for the events under which they are called
    //      IE: A list of effects for when a critter is attacked
static{
    Long seed = (long) 0;
    random = new Random(seed);
    evaluators = new ArrayList<EffectEvaluator>();
    int []percentages = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    for(int i : percentages)
    {
        addPercentageEvaluator(i);
    }
    addHPEvaluators();
    //TODO: when user party and target party are both defined, add separate evaluators for the party numbers
    //TODO: add for stat buffs
    //TODO: add for stat debuffs
    //TODO: add for STAB and effectiveness
    //TODO: implement "And" and "Or" evaluators

}
    //Actual methods to add evaluators to list
    private static void addPercentageEvaluator(int percent)
    {
        evaluators.add(
            new EffectEvaluator((percent - 10) / 20, " has a " + percent + "% chance to ")
        {
            @Override
            public boolean isEffected(Critter target, Critter user)
            {
                return random.nextInt(100) < percent;
            }
        });
    }
    private static void addHPEvaluators()
    {
        evaluators.add(
            new EffectEvaluator(10, " if opponent is at less than 10% HP")
        {
            @Override
            public boolean isEffected(Critter target, Critter user)
            {
                return target.hitpoints <= target.getMaxHP() * 0.1;
            }
        });
        evaluators.add(
            new EffectEvaluator(10, " if opponent is at more than 90% HP")
        {
            @Override
            public boolean isEffected(Critter target, Critter user)
            {
                return target.hitpoints >= target.getMaxHP() * 0.9;
            }
        });
        evaluators.add(
            new EffectEvaluator(10, " if user is at more than 90% HP")
            {
                @Override
                public boolean isEffected(Critter target, Critter user)
                {
                    return user.hitpoints >= user.getMaxHP() * 0.9;
                }
            }
        );
        evaluators.add(
            new EffectEvaluator(10, " if user is at less than 10% HP")
            {
                @Override
                public boolean isEffected(Critter target, Critter user)
                {
                    return user.hitpoints <= user.getMaxHP() * 0.1;
                }
            }
        );
        evaluators.add(
            new EffectEvaluator(3, " if user is at less than 50% HP ")
            {
                @Override
                public boolean isEffected(Critter target, Critter user)
                {
                    return user.hitpoints <= user.getMaxHP() * 0.5;
                }
            }
        );
        evaluators.add(
            new EffectEvaluator(3, " if user is at more than 50% HP ")
            {
                @Override
                public boolean isEffected(Critter target, Critter user)
                {
                    return user.hitpoints >= user.getMaxHP() * 0.5;
                }
            }
        );
        evaluators.add(
            new EffectEvaluator(3, " if opponent is at less than 50% HP ")
            {
                @Override
                public boolean isEffected(Critter target, Critter user)
                {
                    return target.hitpoints <= target.getMaxHP() * 0.5;
                }
            }
        );
        evaluators.add(
            new EffectEvaluator(3, " if opponent is at more than 50% HP ")
            {
                @Override
                public boolean isEffected(Critter target, Critter user)
                {
                    return target.hitpoints >= target.getMaxHP() * 0.5;
                }
            }
        );
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
        return evaluators.get(random.nextInt(evaluators.size()));
    }
    public static EffectEvaluator getStubbed()
    {
        return new EffectEvaluator(0, " always") {

            @Override
            public boolean isEffected(Critter target, Critter user) {
                return true;
            }
            
        };
    }
    //TODO: separate evaluators for specific events.


}
