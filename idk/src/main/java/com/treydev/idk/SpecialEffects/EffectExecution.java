package com.treydev.idk.SpecialEffects;

import com.treydev.idk.critter.Critter;
import java.util.ArrayList;
import java.util.Random;

public abstract class EffectExecution {
    protected int cost;
    protected String description; //immutable
    private static ArrayList<EffectExecution> executions;
    private static Random random;
    static{
        Long seed = (long) 0; //TODO: externalize
        random = new Random(seed);
        executions = new ArrayList<EffectExecution>();
        statBuffExecutions();
    }
    
    //constructor
    private EffectExecution(int cost, String description)
    {
        this.cost = cost;
        this.description = description;
    }

    //Static initialization functions
    private static void statBuffExecutions()
    {
        executions.add(
            new EffectExecution(3, " increases attack") {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseAtk();
                    outputs.add(user.name + " boosted their attack");
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's defense") {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseDefense();
                    outputs.add(user.name + " boosted their defense");
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's speed") {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseSpeed();
                    outputs.add(user.name + " boosted their speed");
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's special attack")
            {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseSpAtk();
                    outputs.add(user.name + " boosted their special attack");
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's special defense")
            {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseSpDefense();
                    outputs.add(user.name + " boosted their special defense");
                }
            }
        );
    }

    //public static function to get random execution.
    public static EffectExecution getEffectExecution() {
        int index = random.nextInt(executions.size());
        return executions.get(index);
    }
    public static EffectExecution getStubbed()
    {
        return new EffectExecution(0, " does nothing") {
            public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                //do nothing
                outputs.add("It did nothing!");
            }
        };
    }

    public abstract void execute(Critter target, Critter user, ArrayList<String> outputs);
    public String getDescription() {
        return description;
    }
}
