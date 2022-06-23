package com.treydev.idk.support;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class ElementAffinityTests {
    @Test
    void testGetRandomAffinity() {
        ElementAffinity e[] = ElementAffinity.GetRandomAffinity();
        Assert.isTrue(e.length == 2, "Didn't return default affinity count");
    }

    @Test
    void testGetRandomAffinityWithCount() {
        ElementAffinity e[] = ElementAffinity.getRandoAffinity(5);
        Assert.isTrue(e.length == 5, "Length is not 5");
    }

    @Test
    void testGetRandomAffinityWithSeed() {
        ElementAffinity e[] = ElementAffinity.GetRandomAffinity(12345);
        Assert.isTrue(e.length == 2, "Didn't return default affinity count");
        Assert.isTrue(e[0].getElement() != e[1].getElement(), "Elements are the same");
    }

    @Test
    void testGetRandomElementByAffinity() {
        ElementAffinity ea[] = ElementAffinity.GetRandomAffinity(1000,4);
        Element e = ElementAffinity.GetRandomElementByAffinity(ea, 12345);
        // The following assert is based on the seed and may change as the source data changes
        Assert.isTrue(e.getId() == 6, "Element is " + e.getId() + " instead of 6");
    }
}
