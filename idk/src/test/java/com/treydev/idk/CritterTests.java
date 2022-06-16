package com.treydev.idk;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

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
        
    }

}
