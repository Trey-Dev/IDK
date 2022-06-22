package com.treydev.idk.SpecialEffects;

import java.util.ArrayList;

import com.treydev.idk.critter.Critter;

public class SpecialEffect {
    public EffectEvaluator effectEvaluator;
    public EffectExecution effectExecution;
    public SpecialEffect(EffectEvaluator effectEvaluator, EffectExecution effectExecution) {
        this.effectEvaluator = effectEvaluator;
        this.effectExecution = effectExecution;
    }
    public void activate(Critter target, Critter user, ArrayList<String> outputs)
    {
        if(effectEvaluator.isEffected(target, user))
            effectExecution.execute(target, user, outputs);
    }
}
