package com.treydev.idk.critter;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.treydev.idk.attack.Move;
import com.treydev.idk.critter.Critter;

//import jakarta.security.auth.message.MessagePolicy.Target;

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
    void testAttackStages()
    {
        ArrayList<Critter> party = Critter.initializeStubbedParty();
        Critter attacker = party.get(0);
        Critter defender = party.get(1); //Poor guy
        ArrayList<String> outputs = new ArrayList<>();
        for(Move move: attacker.moveset)
        {
            //No buffs
            if(move == null) break;
            System.out.println("Testing no buffs!");
            int baseDamage = defender.hitpoints;
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
            } while(outputs.contains("You missed!")); //Execute move until hit!
            for(String output : outputs) System.out.println(output);
            baseDamage -= defender.hitpoints;

            //one buff
            System.out.println("Testing one buff!");
            attacker.increaseAtk();
            int currentDamage = defender.hitpoints;
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output);
            currentDamage -= defender.hitpoints;
            Assert.isTrue(currentDamage > baseDamage, "Increasing attack did not increase damage");
            // Assert.isTrue((currentDamage - 2) == baseDamage * 1.5 - 2, "Increasing attack did not increase damage by the ratio expected");

            //remove buff, back to base.
            currentDamage = defender.hitpoints;
            System.out.println("Testing debuff, back to base!");
            attacker.decreaseAtk();
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output); 
            currentDamage -= defender.hitpoints;
            Assert.isTrue(baseDamage == currentDamage, "Adding and subtracting a buff did not zero out to base damage");
            
            currentDamage = defender.hitpoints;
            System.out.println("Testing one more debuff, lower than original!");
            attacker.decreaseAtk();
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output); 
            currentDamage -= defender.hitpoints;
            Assert.isTrue(currentDamage < baseDamage, "Decreasing attack did not decrease damage.");
            // Assert.isTrue(currentDamage > baseDamage * 0.66, "Decreasing attack by one stage decreased damage too much (expected around 2/3, got " + (float)currentDamage / baseDamage + ")");
            // Assert.isTrue(currentDamage < baseDamage * 0.67, "Decreasing attack did not decrease damage enough (expected around 2/3, got " + (float)currentDamage / baseDamage + ")");
        }
    }

    @Test
    void testDefenceStages()
    {
        ArrayList<Critter> party = Critter.initializeStubbedParty();
        Critter attacker = party.get(0);
        Critter defender = party.get(1); //Poor guy
        ArrayList<String> outputs = new ArrayList<>();
        for(Move move: attacker.moveset)
        {
            //No buffs
            if(move == null) break;
            System.out.println("Testing no buffs!");
            int baseDamage = defender.hitpoints;
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
            } while(outputs.contains("You missed!")); //Execute move until hit!
            for(String output : outputs) System.out.println(output);
            baseDamage -= defender.hitpoints;

            //one buff
            System.out.println("Testing one buff!");
            defender.increaseDefense();
            int currentDamage = defender.hitpoints;
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output);
            currentDamage -= defender.hitpoints;
            Assert.isTrue(currentDamage < baseDamage, "Increasing attack did not increase damage");
            // Assert.isTrue((currentDamage - 2) == baseDamage * 1.5 - 2, "Increasing attack did not increase damage by the ratio expected");

            //remove buff, back to base.
            currentDamage = defender.hitpoints;
            System.out.println("Testing debuff, back to base!");
            defender.decreaseDefense();
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output); 
            currentDamage -= defender.hitpoints;
            Assert.isTrue(baseDamage == currentDamage, "Adding and subtracting a buff did not zero out to base damage");
            
            currentDamage = defender.hitpoints;
            System.out.println("Testing one more debuff, lower than original!");
            defender.decreaseDefense();
            do{
                outputs.clear();
                move.Execute(outputs, defender, attacker);
                } while(outputs.contains("You missed!"));
            for(String output : outputs) System.out.println(output); 
            currentDamage -= defender.hitpoints;
            Assert.isTrue(currentDamage > baseDamage, "Decreasing attack did not decrease damage.");
        }
    }

}
