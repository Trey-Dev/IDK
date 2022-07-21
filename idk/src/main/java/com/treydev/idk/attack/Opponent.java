package com.treydev.idk.attack;

import java.util.ArrayList;

import com.treydev.idk.IdkController;
import com.treydev.idk.critter.Critter;

public class Opponent {
    /*
     * This class is used to represent the opponent in combat.
     * It will be used by the combat handler 
     */

     //TODO: static factory method to generate random encounter.
     //Main part of this class:
     private Critter[] party;
     public void takeTurn(ArrayList<String> outputs)
     {
            //This will change the outputs and combatoptions fields.
            outputs.clear();
            // Should just say: you used the action just fricking punch it I guess
            Critter currentCritter = party[0];
            
            Move[] currentMoveset = currentCritter.moveset;
            Move moveUsed = currentMoveset[0];
            
            outputs.add("The opponent used " + moveUsed.name);
            moveUsed.Execute(outputs, IdkController.PlayerParty.get(0), currentCritter);
     }

     private Move chooseMove()
     {
      //TODO: replace with random move
        return party[0].moveset[0];
     }
}
