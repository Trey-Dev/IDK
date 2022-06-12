package com.treydev.idk;

import java.util.ArrayList;
import java.util.Random;

public abstract class Move {

    //TODO: Implement "type" (Status, Physical, Special) as subclasses. 
    //  P & Sp: "Execute" function runs private "Damage" function, then call "Effect"
    //  Status can just be implemented as a base move.
    public String name;
    private static Random random;

    private static Move[] AllMoves = {
        new PhysicalAttack("Just hit it.", 15, 80)
        {
            @Override 
            protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
        },
        new PhysicalAttack("Hit it harder!", 30, 40)
        {
            @Override 
            protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
        }
    };

    public static void initializeRandom(long seed)
    {
        random = new Random(seed);
    }

    public abstract void Execute(ArrayList<String> outputs, Critter target, Critter user);
    
    public Move(String name)
    {
        this.name = name;
    }

    public static Move[] implementStubbedMoveList()
    {
        Move[] value = new Move[4];
        value[0] = AllMoves[0];
        value[1] = AllMoves[1];
        return value;
    }
    public static Move getRandomMove()
    {
        return AllMoves[random.nextInt(AllMoves.length)];
    }

}
