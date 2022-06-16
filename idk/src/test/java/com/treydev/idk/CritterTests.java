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
            System.out.println(creature.getAttack());
            System.out.println(creature.getDefence());
            System.out.println(creature.getSpecDef());
            System.out.println(creature.getSpecial());
            System.out.println(creature.getSpeed());
        }
    }

    @Test
    void testGetAttack() {

    }

    @Test
    void testGetDefence() {

    }

    @Test
    void testGetSpecDef() {

    }

    @Test
    void testGetSpecial() {

    }

    @Test
    void testGetSpeed() {

    }
}
