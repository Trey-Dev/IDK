package com.treydev.idk.SpecialEffects;

import com.treydev.idk.critter.Critter;
import java.util.ArrayList;

public abstract class EffectExecution {
    public abstract void execute(Critter target, Critter user, ArrayList<String> outputs);
}
