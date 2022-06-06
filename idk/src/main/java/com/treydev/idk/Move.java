package com.treydev.idk;

import java.util.ArrayList;

public abstract class Move {

    //TODO: Implement "type" (Status, Physical, Special) as subclasses. 
    //  P & S: "Execute" function runs private "Damage" function, then call "Effect"
    //TODO: Implement "Damage" function
    public String name;

    public abstract void Execute(ArrayList<String> outputs, Critter target);
    public Move(String name)
    {
        this.name = name;
    }
}
