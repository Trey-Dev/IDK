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
        allMoves.add(new PhysicalAttack("Just hit it.", null, 60, 80));
        allMoves.add(new PhysicalAttack("Hit it harder!", null, 120, 40));
        allMoves.add(new PhysicalAttack("Hit it softer...?", null, 40, 100));
        allMoves.add(new PhysicalAttack("Don't hit it?", null, 1000, 0));
        allMoves.add(new PhysicalAttack("Really risky, don't try it.", null, 800, 10));
    }


    public static Move []genMoveset(int level, HashMap<Integer,Move> moveList)
    {
        Move []value = new Move[4];
        for(int i = 0; i < 4; i++)
        {
            int currentMoveLevel;
            Boolean canContinue;
            //fill out a single move.
            do{
                canContinue = true;
                currentMoveLevel = getMoveInList(moveList);
                for(Move valueMove : value)
                {
                    if(moveList.get(currentMoveLevel) == valueMove)
                    {
                        canContinue = false;
                        break;
                    }
                }
            } while(currentMoveLevel > level || !canContinue);
            value[i] = moveList.get(currentMoveLevel);
        }
        return value;
    }
    public abstract void Execute(ArrayList<String> outputs, Critter target, Critter user);
    
    public Move(String name, Element Movetype)
    {
        this.name = name;
        this.moveType = Movetype;
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
    public static int getMoveInList(HashMap<Integer, Move> movelist)
    {
        int value = 0;
        while(movelist.get(value) == null)
            value = random.nextInt(100);
        return value;
    }


    public static HashMap<Integer,Move> genMoveList()
    {
        //TODO: add something that generates a random move
        HashMap<Integer,Move> value = new HashMap<Integer,Move>();
        for(int i = 0; i < 21; i++) //TODO: Undo hardcoding of 25
            {
                value.put(random.nextInt(100), Move.getRandomMove()); //TODO: Make sure no moves can be obtained for lower/higher level than evolutions!
                System.out.println(value.keySet().toString());
                //TODO: check for collisions!
            }
        return value;
    }
}
