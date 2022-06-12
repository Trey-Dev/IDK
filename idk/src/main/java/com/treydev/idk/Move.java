package com.treydev.idk;

import java.util.ArrayList;

public abstract class Move {

    //TODO: Implement "type" (Status, Physical, Special) as subclasses. 
    //  P & S: "Execute" function runs private "Damage" function, then call "Effect"
    public String name;

    public abstract void Execute(ArrayList<String> outputs, Critter target, Critter user);
    
    public Move(String name)
    {
        this.name = name;
    }

    public static Move[] implementStubbedMoveList()
    {
        Move[] value = new Move[4];
        value[0] = new PhysicalAttack("Just hit it.", 15, 80)
        {
            @Override 
            protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
        };
        value[1] = new PhysicalAttack("Hit it harder!", 30, 40)
        {
            @Override 
            protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
        };
        return value;
    }
}
