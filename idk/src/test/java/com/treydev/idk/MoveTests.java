package com.treydev.idk;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class MoveTests {
    @Test
    void testGenMoveList() {
        //TODO: when we implement actual generation of code, we'll need to change the seed.
        //It's currently stubbed as 0, so we don't have to change it.
        Assert.isTrue(true, "Wow, how'd you screw THIS up?");
        System.out.println("vvvvvvvvvvvvvvvvvv TESTING GENMOVELIST vvvvvvvvvvvvvvvvvv");
        HashMap<Integer,Move> sampleMoveListOne = Move.genMoveList();
        Assert.isTrue(sampleMoveListOne.size() >= 1, "Sample movelist is empty!");
        Assert.isTrue(sampleMoveListOne.size() <= 25, "Sample movelist is not 25 long.");
        for (Move move : sampleMoveListOne.values())
        {
           System.out.println("!!!!<<===============>>!!!!");
           ArrayList<String> thing = new ArrayList<String>();
           ArrayList<Critter> critters = Critter.initializeStubbedParty();
           move.Execute(thing, critters.get(0), critters.get(1));
           for(String outputString : thing)
                System.out.println(outputString);
        }

        
    }

    @Test
    void testGenMoveset()
    {
        Move []sampleMoveset = Move.genMoveset(75, Move.genMoveList());
        for(Move move : sampleMoveset)
        {
            ArrayList<Critter> StartAParty = Critter.initializeStubbedParty();
            System.out.println("!!!!<<=================>>!!!!");
            ArrayList<String> outputStrings = new ArrayList<>();
            move.Execute(outputStrings, StartAParty.get(0), StartAParty.get(1));
            for(String outputString : outputStrings)
                System.out.println(outputString);
        }
    }

    @Test
    void testGetRandomMove() {
        System.out.println("vvvvvvvvvvv TESTING GETRANDOMMOVE vvvvvvvvvvvvv");
        ArrayList<String> sampleOutputs = new ArrayList<String>();
        Move random = Move.getRandomMove();
        ArrayList<Critter> critters = Critter.initializeStubbedParty();
        random.Execute(sampleOutputs, critters.get(0), critters.get(1));
        for(String output : sampleOutputs)
            System.out.println(output);
    }
}
