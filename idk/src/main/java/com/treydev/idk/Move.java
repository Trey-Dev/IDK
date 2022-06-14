package com.treydev.idk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class Move {

    
    //  P & Sp: "Execute" function runs private "Damage" function, then call "Effect"
    //  Status can just be implemented as a base move.

    private Element moveType;
    public String name;
    private static Random random;

    private static ArrayList<Move> allMoves;

    //TODO: Generate something that creates a random move

    static{
        long seed = 0; //TODO: remove hardcoded stub!
        random = new Random(seed);
        allMoves = new ArrayList<>();
        allMoves.add(new PhysicalAttack("Just hit it.", 15, 80));
        allMoves.add(new PhysicalAttack("Hit it harder!", 30, 40));
        allMoves.add(new PhysicalAttack("Hit it softer...?", 5, 100));
        allMoves.add(new PhysicalAttack("Don't hit it?", 123, 0));
        allMoves.add(new PhysicalAttack("Really risky, don't try it.", 80, 10));
    }

    //TODO: something that creates a (stubbed) movelist
    //TODO: something that generates a nonstubbed moveset

    public abstract void Execute(ArrayList<String> outputs, Critter target, Critter user);
    
    public Move(String name)
    {
        this.name = name;
    }

    public static Move[] implementStubbedMoveList()
    {
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


    public static HashMap<Integer,Move> genMoveList()
    {
        HashMap<Integer,Move> value = new HashMap<Integer,Move>();
        for(int i = 0; i < 25; i++)
            value.put(random.nextInt(100), Move.getRandomMove());
        return value;
    }
}
