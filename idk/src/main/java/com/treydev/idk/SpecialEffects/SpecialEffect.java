package com.treydev.idk.SpecialEffects;

import java.util.ArrayList;

import com.treydev.idk.critter.Critter;

public class SpecialEffect {
    public EffectEvaluator effectEvaluator;
    public EffectExecution effectExecution;
    //TODO: make private
    public SpecialEffect(EffectEvaluator effectEvaluator, EffectExecution effectExecution) {
        this.effectEvaluator = effectEvaluator;
        this.effectExecution = effectExecution;
    }
    //The actual functions for the effect.
    public void activate(Critter target, Critter user, ArrayList<String> outputs)
    {
        if(effectEvaluator.isEffected(target, user))
            effectExecution.execute(target, user, outputs);
    }
    public String getDescription()
    {
        return effectEvaluator.getDescription() + effectExecution.getDescription();
    }
    //The factory functions for SpecialEffect
    public static SpecialEffect genRandomSpecialEffect()
    {
        EffectEvaluator effectEvaluator = EffectEvaluator.getEffectEvaluator();
        EffectExecution effectExecution = EffectExecution.getEffectExecution();
        
        return new SpecialEffect(effectEvaluator, effectExecution);
    }
    public static SpecialEffect stubSpecialEffect()
    {
        EffectExecution effectExecution = EffectExecution.getStubbed();
        EffectEvaluator effectEvaluator = EffectEvaluator.getStubbed();
        return new SpecialEffect(effectEvaluator, effectExecution);
    }
}
