package com.treydev.idk.SpecialEffects;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.treydev.idk.critter.Critter;

public class SpecialEffectTest {
    @Test
    void testActivate() {
        ArrayList<String> outputs = new ArrayList();
        ArrayList<Critter> sampleParty = Critter.initializeStubbedParty();
        Critter attacker = sampleParty.get(0);
        Critter defender = sampleParty.get(1);
        //Test activate with a stubbed special effect
        SpecialEffect stubbedEffect = SpecialEffect.stubSpecialEffect();
        //Effect should execute the effect execution when the effect evaluator returns true.
        stubbedEffect.activate(defender, attacker, outputs);
        //TODO: if stubbed code changes or more stubs are added, edit;
        //  HOWEVER, for this run: evaluator always returns true, and execution adds "It did nothing!" to outputs.
        Assert.isTrue(outputs.size() == 1, "There is more or less than one output");
        Assert.isTrue(outputs.get(0) == "It did nothing!", "The output was different than the stubbed output");
        Assert.isTrue(stubbedEffect.getDescription().equals(" always does nothing"), "Description was different than what was expected.");
    }

    @Test
    void testGenRandomEffect()
    {
        //initialize combat variables, as usual.
        ArrayList<String> outputs = new ArrayList();
        ArrayList<Critter> sampleParty = Critter.initializeStubbedParty();
        Critter attacker = sampleParty.get(0);
        Critter defender = sampleParty.get(1);
        System.out.println("vvvvvvvvvv TESTING RANDOM EFFECTS vvvvvvvvvv");
        //Test genRandomEffect a few times. 
        ArrayList<SpecialEffect> effects = new ArrayList<>();
        for(int i = 0; i < 10; i++)
            effects.add(SpecialEffect.genRandomSpecialEffect());

        //For each effect:
        for(SpecialEffect effect : effects)
        {
            //Effect should not be null
            Assert.notNull(effect, "genRandomSpecialEffect returned null!");
            //Evaluator should not be null
            Assert.notNull(effect.effectEvaluator, "genRandomSpecialEffect returned effect with null evaluator!");
            //Execution should not be null
            Assert.notNull(effect.effectExecution, "genRandomSpecialEffect returned effect with null execution!");
            //If evaluator executes, should print something to outputs. Some should pass, others should fail.
            effect.activate(defender, attacker, outputs);
        }
        //It should be impossible for there to be more outputs than effects
        Assert.isTrue(!(outputs.size() > 10), "There are more outputs than effects.");
        //Not all of the effects should have passed. NOTE: FLAKY
        Assert.isTrue(outputs.size() < 10, "All effects have executed successfully. Some should have failed.");
        //At least one of the effects should have passed. NOTE:FLAKY
        Assert.isTrue(outputs.size() > 0, "No effects have executed successfully.");
        for(String output: outputs) System.out.println(output);
    }
}
