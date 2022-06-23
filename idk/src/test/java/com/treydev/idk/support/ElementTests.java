package com.treydev.idk.support;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElementTests {
    @Test
    void testElementCount() {
        Assert.isTrue(Element.getElementCount() > 0, "Element.getElementCount() should return a positive number");
    }

    @Test
    void testGetId() {
        Element element = Element.getRandomElement();
        Assert.isTrue(element.getId() >= 0 && element.getId() < Element.getElementCount(),
                "Element.getId() should return a number between 0 and Element.getElementCount()");
    }

    @Test
    void testGetRandomElement() {
        Element element = Element.getRandomElement();
        Assert.isTrue(element.getId() >= 0 && element.getId() < Element.getElementCount(),
                "Element.getRandomElement() should return an element with an id between 0 and Element.getElementCount()");

        Random.Initialize(100);
        element = Element.getRandomElement();
        Assert.isTrue(element.getId() >= 0 && element.getId() < Element.getElementCount(),
                "Element.getRandomElement() should return an element with an id between 0 and Element.getElementCount()");
    }

    @Test
    void testGetName() {
        // For this seed, we'll get id=5
        Random.Initialize(1000);
        Element element = Element.getRandomElement();
        Assert.isTrue(element.getName().equals("Indigo"),
                "Element.getName() should return the name of the element with id 5");
    }

    @Test
    void testGetByName() {
        Element element = Element.getByName("Orange");
        Assert.isTrue(element.getId() == 1, "Element.getId() should return a known element");

        element = Element.getByName("Unknown");
        Assert.isTrue(element == null, "Element.getByName() should return null for an unknown element");
    }

    @Test
    void testGetById() {
        Element element = Element.getById(0);
        Assert.isTrue(element.getName().equals("Red"), "Element.getById() should return the element with id 0");

        element = Element.getById(Element.getElementCount());
        Assert.isTrue(element == null, "Element.getById() should return null for an unknown element");
    }
}
