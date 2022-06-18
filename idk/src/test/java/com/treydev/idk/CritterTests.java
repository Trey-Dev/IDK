package com.treydev.idk;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.treydev.idk.attack.Move;
import com.treydev.idk.critter.Critter;

public class CritterTests {
    @Test
    void testEnterCombat() {
        ArrayList<Critter> party = Critter.initializeStubbedParty();
        for(Critter creature : party)
        {
            creature.enterCombat();
            System.out.println("Stats: ");
            System.out.println("A" + creature.getAttack());
            System.out.println("D" + creature.getDefence());
            System.out.println("SD" + creature.getSpecDef());
            System.out.println("SA" + creature.getSpecial());
            System.out.println("S" + creature.getSpeed());
        }
    }

    @Test
    void testCombatStages()
    {
        ArrayList<Critter> party = Critter.initializeStubbedParty();
        Critter Attacker = party.get(0);
        Critter Defender = party.get(1); //Poor guy
        ArrayList<String> outputs = new ArrayList<>();
        for(Move move: Attacker.moveset)
        {
            if(move == null) break;
            System.out.println("Testing no buffs!");
            do{
                outputs.clear();
                move.Execute(outputs, Attacker, Defender);
            } while(outputs.contains("You missed!")); //Execute move until hit!
            for(String output : outputs) System.out.println(output);
            
            System.out.println("Testing one buff!");
            Attacker.increaseAtk();
            do{
                outputs.clear();
                move.Execute(outputs, Attacker, Defender);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output);
            System.out.println("Testing debuff, back to base!");
            Attacker.decreaseAtk();
            do{
                outputs.clear();
                move.Execute(outputs, Attacker, Defender);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output); 
            System.out.println("Testing one more debuff, lower than original!");
            Attacker.decreaseAtk();
            do{
                outputs.clear();
                move.Execute(outputs, Attacker, Defender);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output); 
        }
    }

}
