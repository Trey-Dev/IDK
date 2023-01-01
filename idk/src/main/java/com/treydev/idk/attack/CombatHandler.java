package com.treydev.idk.attack;

import java.util.ArrayList;

import com.treydev.idk.IdkController;
import com.treydev.idk.critter.Critter;
import com.treydev.idk.critter.Species;

public class CombatHandler {
    public ArrayList<String> outputs;
    public ArrayList<String> CombatOptions;
    public Critter opponent;

    public CombatHandler() {
        this.outputs = new ArrayList<String>();
        this.CombatOptions = new ArrayList<String>();
        this.opponent = new Critter("Wadget", Species.stubWadget(), null, 5);
    }

    public CombatHandler(ArrayList<String> outputs, ArrayList<String> CombatOptions, Critter opponent) {
        //TODO replace Critter class with Opponent class
        this.CombatOptions = CombatOptions;
        this.outputs = outputs;
        this.opponent = opponent;
    }

    public static CombatHandler initializeStubbed() {
        CombatHandler value = new CombatHandler();
        value.outputs.add("A wild " + value.opponent.name + " attacks");
        // value.CombatOptions.add("Just fricking punch it I guess");
        value.updateCombatOptions();
        return value;
    }

    public void takeAction(String action) {
        // This will change the outputs and combatoptions fields.
        outputs.clear();
        outputs.add("You used the action " + action);
        // Should just say: you used the action just fricking punch it I guess
        Critter currentCritter = IdkController.PlayerParty.get(0);

        Move[] CurrentMoveset = currentCritter.moveset;
        for (Move move : CurrentMoveset) {
            if (move.name.equals(action)) {
                move.Execute(outputs, opponent, currentCritter);
                break;
            }
        }
        //TODO: replace with opponent taking a move.
        updateCombatOptions();
    }

    private void updateCombatOptions() {
        CombatOptions.clear();
        Move[] moves = IdkController.PlayerParty.get(0).moveset;
        for (Move option : moves) {
            // option = Move.implementStubbedMoveList()[0];
            if (option == null)
                break;
            CombatOptions.add(option.name);
        }
    }
}
