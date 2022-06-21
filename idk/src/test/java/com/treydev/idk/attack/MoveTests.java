package com.treydev.idk.attack;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.treydev.idk.attack.Move;
import com.treydev.idk.critter.Critter;

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
        Assert.isTrue(sampleMoveset[0] != null, "First move in moveset empty in genMoveset function");
        Assert.isTrue(sampleMoveset[1] != null, "Second move in moveset empty in genMoveset function");
        Assert.isTrue(sampleMoveset[2] != null, "Third move in moveset empty in genMoveset function");
        Assert.isTrue(sampleMoveset[3] != null, "Fourth move in moveset empty in genMoveset function");
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
        Assert.isTrue(random != null, "getRandomMove returned null");
    }


    @Test
    void testGenRandomMove()
    {
        //Create 10 different moves, < 0.1% chance none are Special, <.2% chance none are special or physical
        //TODO: when additional functionality is added, recreate tests as seems fitting to test.
        Move []testMoves = new Move[10];
        boolean hasPhysical = false;
        boolean hasSpecial = false;
        ArrayList<String> sampleOutputs = new ArrayList<>();
        ArrayList<Critter> sampleParty = Critter.initializeStubbedParty();
        for(int i = 0; i < 10; i++)
        {
            Move currentTestMove = Move.genRandomMove();
            currentTestMove.Execute(sampleOutputs, sampleParty.get(0), sampleParty.get(1));
            Assert.isTrue(currentTestMove.getClass().getName() == "com.treydev.idk.attack.PhysicalAttack"
                || currentTestMove.getClass().getName() == "com.treydev.idk.attack.SpecialAttack", 
                "The attack is neither physical nor special!"); //TODO: again, when new types are implemented, change this test.
            if(currentTestMove.getClass().getName() == "com.treydev.idk.attack.PhysicalAttack")
                hasPhysical = true;
            if(currentTestMove.getClass().getName() == "com.treydev.idk.attack.SpecialAttack")
                hasSpecial = true;
        }
        for(String output : sampleOutputs) System.out.println(output);
        Assert.isTrue(hasPhysical, "In a test of 10 50/50 moves, all were Special");
        Assert.isTrue(hasSpecial, "In a test of 10 50/50 moves, all were Physical")
    }
}
