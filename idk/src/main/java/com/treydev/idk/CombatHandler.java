package com.treydev.idk;

import java.util.ArrayList;

public class CombatHandler {
    public ArrayList<String> outputs;
    public ArrayList<String> CombatOptions;
    public int enemyHealth; //TODO: Send this to its own class.

    public CombatHandler()
    {
        this.outputs = new ArrayList<String>();
        this.CombatOptions = new ArrayList<String>();
    }

    public CombatHandler(ArrayList<String> outputs, ArrayList<String> CombatOptions)
    {
        this.CombatOptions = CombatOptions;
        this.outputs = outputs;
    }

    public static CombatHandler initializeStubbed()
    {
        CombatHandler value = new CombatHandler();
        value.outputs.add("A wild something attacks");
        value.CombatOptions.add("Just fricking punch it I guess");
        value.enemyHealth = 45;
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
        enemyHealth -= 15; //TODO: This is also stubbed code, replace with 
    }

}