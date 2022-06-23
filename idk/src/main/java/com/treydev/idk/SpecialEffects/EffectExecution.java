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
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's defense") {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseDefense();
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's speed") {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseSpeed();
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's special attack")
            {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseSpAtk();
                }
            }
        );
        executions.add(
            new EffectExecution(3, " increase user's special defense")
            {
                @Override
                public void execute(Critter target, Critter user, ArrayList<String> outputs) {
                    user.increaseSpDefense();
                }
            }
        );
    }

    //public static function to get random execution.
    public static EffectExecution getEffectExecution() {
        int index = random.nextInt(executions.size());
        return executions.get(index);
    }

    public abstract void execute(Critter target, Critter user, ArrayList<String> outputs);
    public String getDescription() {
        return description;
    }
}
