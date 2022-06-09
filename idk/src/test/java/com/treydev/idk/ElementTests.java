package com.treydev.idk;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import com.treydev.idk.Element.Type;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElementTests
{
	@Test
    /// Tests that the getElement() method returns the correct element
	void returnsElementName()
    {
        Assert.isTrue(Element.getElement(Type.NONE).getName() == "", "Failed NONE name test");
        Assert.isTrue(Element.getElement(Type.FIRE).getName().equals("fire"), "Failed FIRE name test");
        Assert.isTrue(Element.getElement(Type.AIR).getName().equals("air"), "Failed AIR name test");
	}

	@Test
    /// Tests that the getCombatMultiplier() method returns the correct value for two different elements
	void returnsCombatMultiplier()
    {
        Element fire = Element.getElement(Type.FIRE);
        Element water = Element.getElement(Type.WATER);
        Element none = Element.getElement(Type.NONE);

        Assert.isTrue(fire.getCombatMultiplierFor(water) == 1.0, "Wrong multiplier for Fire/Water");
        Assert.isTrue(water.getCombatMultiplierFor(water) == 1.0, "Wrong multiplier for Water/Water");
        Assert.isTrue(none.getCombatMultiplierFor(fire) == 1.0, "Wrong multiplier for None/Fire");
        Assert.isTrue(fire.getCombatMultiplierFor(none) == 1.0, "Wrong multiplier for Fire/None");
	}

	@Test
    /// Tests that the getRandomElement() method returns the correct element based on a known seed number
	void returnsRandomElement()
    {
        Element e = Element.getRandomElement();
        Assert.notNull(e, "Failure getting a random element");

        e = Element.getRandomElement(0.1);
        Assert.isTrue(e.getType() == Type.NONE, "Incorrect name for 'random' NONE");

        e = Element.getRandomElement(0.2);
        Assert.isTrue(e.getType() == Type.FIRE, "Incorrect name for 'random' FIRE");

        e = Element.getRandomElement(0.3);
        Assert.isTrue(e.getType() == Type.WATER, "Incorrect name for 'random' WATER");
    }
}
