package com.treydev.idk;

import java.util.ArrayList;

public class CombatHandler {
    public static ArrayList<String> outputs;
    public static ArrayList<String> CombatOptions;
    public static void initializeStubbed()
    {
        outputs = new ArrayList<String>();
        outputs.add("A wild something attacks");
        CombatOptions = new ArrayList<String>();
        CombatOptions.add("Just fricking punch it I guess");
    }
    public static void takeAction(String action)
    {
        //This will change the outputs and combatoptions fields.
        //TODO: This is STUBBED code, it will always just output the command given.
        outputs.clear();
        outputs.add("You used the action " + action);
        //Should just say: you used the action just fricking punch it I guess.
        
        //TODO: in actual implementation, edits CombatOptions. It won't do that here.
        
    }
    

}
