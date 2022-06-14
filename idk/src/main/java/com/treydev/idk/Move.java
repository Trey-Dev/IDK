package com.treydev.idk;

import java.util.ArrayList;
import java.util.Random;

public abstract class Move {

    //TODO: Implement "type" (Status, Physical, Special) as subclasses. 
    //  P & Sp: "Execute" function runs private "Damage" function, then call "Effect"
    //  Status can just be implemented as a base move.
    public String name;
    private static Random random;

    private static ArrayList<Move> allMoves;

    //TODO: Generate something that creates a random move
    // private static Move[] AllMoves = {
    //     new PhysicalAttack("Just hit it.", 15, 80)
    //     {
    //         @Override 
    //         protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
    //     },
    //     new PhysicalAttack("Hit it harder!", 30, 40)
    //     {
    //         @Override 
    //         protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
    //     }
    // };

    //TODO: something that creates a (stubbed) movelist
    //TODO: something that generates a nonstubbed moveset
    public static void initializeStatic(long seed)
    {
        random = new Random(seed);
        allMoves = new ArrayList<>();
        allMoves.add(new PhysicalAttack("Just hit it.", 15, 80)
        {
            @Override 
            protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
        });
        allMoves.add(new PhysicalAttack("Hit it harder!", 30, 40)
        {
            @Override 
            protected void AddEffects(ArrayList<String> outputs, Critter Target, Critter user) {}
        });
    }

    public abstract void Execute(ArrayList<String> outputs, Critter target, Critter user);
    
    public Move(String name)
    {
        this.name = name;
    }

    public static Move[] implementStubbedMoveList()
    {
    //if(allMoves == null) initializeStatic(0);//TODO: refactor
        Move[] value = new Move[4];
        value[0] = allMoves.get(0);
        value[1] = allMoves.get(1);
        return value;
    }
    public static Move getRandomMove()
    {
        //TODO: Implement method that either gets or generates a random move.
        return allMoves.get(random.nextInt(allMoves.size()));
    }

}
