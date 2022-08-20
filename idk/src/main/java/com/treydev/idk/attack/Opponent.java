package com.treydev.idk.attack;

import java.util.ArrayList;

import com.treydev.idk.IdkController;
import com.treydev.idk.critter.Critter;

public abstract class Opponent {
    /*
     * This class is used to represent the opponent in combat.
     * It will be used by the combat handler 
     */
    

    protected abstract Move selectMove();


     //TODO: static factory method to generate random encounter.
    public static Opponent genWildEncounter(ArrayList<String> outputs)
    {
        
        //TODO: replace stubbed code
        Critter[] party = {Critter.initializeStubbedParty().get(0)};
        outputs.add("A wild " + party[0].name + " appeared!");
        return new Opponent( party , "The wild " + party[0].name)
        {
            @Override
            public Move selectMove()
            {
                return party[0].moveset[0]; //TODO: replace stubbed code
            }
        };
    }

     //Main part of this class:
     private Critter[] party;
     private String name;

     private Opponent(Critter[] party, String name) {
         this.party = party;
         this.name = name;
     }


     //accessors and mutators
     public String getName() {
         return name;
     }
       
     public void takeTurn(ArrayList<String> outputs)
     {
            //This will change the outputs and combatoptions fields.
            outputs.clear();
            // Should just say: you used the action just fricking punch it I guess
            Critter currentCritter = party[0];
            
            Move[] currentMoveset = currentCritter.moveset;
            Move moveUsed = currentMoveset[0];
            
            outputs.add( name + " used " + moveUsed.name);
            moveUsed.Execute(outputs, IdkController.PlayerParty.get(0), currentCritter);
     }

    //  private Move chooseMove()
    //  {
    //   //TODO: replace with basic AI function.
    //     return party[0].moveset[0];
    //  }
}
