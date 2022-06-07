package com.treydev.idk;

import java.util.ArrayList;

public class CombatHandler {
    public ArrayList<String> outputs;
    public ArrayList<String> CombatOptions;
    //public int enemyHealth; //TODO: Send this to its own class.
    public Critter opponent;

    public CombatHandler()
    {
        this.outputs = new ArrayList<String>();
        this.CombatOptions = new ArrayList<String>();
        this.opponent = new Critter("Wadget",1,1, null);
    }

    public CombatHandler(ArrayList<String> outputs, ArrayList<String> CombatOptions, Critter opponent)
    {
        this.CombatOptions = CombatOptions;
        this.outputs = outputs;
        this.opponent = opponent;
    }

    public static CombatHandler initializeStubbed()
    {
        CombatHandler value = new CombatHandler();
        value.outputs.add("A wild "+ value.opponent.name + " attacks");
        //value.CombatOptions.add("Just fricking punch it I guess");
        value.updateCombatOptions();
        //value.enemyHealth = 45;
        return value;
    }

    public void takeAction(String action)
    {
        //This will change the outputs and combatoptions fields.
        //TODO: This is STUBBED code, it will always just output the command given.
        outputs.clear();
        outputs.add("You used the action " + action);
        //Should just say: you used the action just fricking punch it I guess.
        
        //TODO: in actual implementation, checks CombatOptions. It won't do that here.
        opponent.health -= 15; //TODO: This is also stubbed code, replace with actual execution of combatoptions.
        outputs.add("The opponents health is now "+opponent.health);//TODO: also implement outputs of actions
        updateCombatOptions();
    }
    private void updateCombatOptions()
    {
        CombatOptions.clear();
        Move[] moves = HelloWorldController.PlayerParty.get(0).moveset;
        for (Move option : moves)
        {
            //option = Move.implementStubbedMoveList()[0];
             if(option == null)
                 break;
            CombatOptions.add(option.name);
        }
    }

}
