package com.treydev.idk.critter;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import com.treydev.idk.support.Element;

public class SpeciesTests {
    @Test
    void testGenRandomSpecies() {
        int averageStat = 50;
        Species s = Species.GenRandomSpecies(averageStat);
        int totalOfStats = s.getBaseAttack() + s.getBaseDefense() + s.getBaseSpecial() + s.getBaseSDefence()
                + s.getBaseSpeed() + s.getBaseHitPoints();

        // Allowing 10% error in the randomness... 90% of target to 110%
        Assert.isTrue(totalOfStats > averageStat * 6 * 0.9, "Average generated stat is below threshold");
        Assert.isTrue(totalOfStats < averageStat * 6 * 1.1, "Average generated stat is above threshold");

        // Test the getters
        Assert.isTrue(s.getBaseAttack() > 0, "BaseAttack is not positive");
        Assert.isTrue(s.getBaseDefense() > 0, "BaseDefense is not positive");
        Assert.isTrue(s.getBaseSpecial() > 0, "BaseSpecial is not positive");
        Assert.isTrue(s.getBaseSDefence() > 0, "BaseSDefence is not positive");
        Assert.isTrue(s.getBaseSpeed() > 0, "BaseSpeed is not positive");
        Assert.isTrue(s.getBaseHitPoints() > 0, "BaseHitPoints is not positive");
    }

    @Test
    void testGetTypes() {
        int averageStat = 50;
        Species s = Species.GenRandomSpecies(averageStat);
        Element e1 = s.getTypeOne();
        Element e2 = s.getTypeTwo();
        Assert.isTrue(e1 != e2, "Types are the same");
        Assert.isTrue(e1 != null, "TypeOne is null");
    }
}
