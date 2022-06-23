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
        ElementAffinity e[] = ElementAffinity.GetRandomAffinity(5);
        Assert.isTrue(e.length == 5, "Length is not 5");
    }

    @Test
    void testGetRandomElementByAffinity() {
        Random.Initialize(1000);
        ElementAffinity ea[] = ElementAffinity.GetRandomAffinity(4);
        Random.Initialize(12345);
        Element e = ElementAffinity.GetRandomElementByAffinity(ea);
        // The following assert is based on the seed and may change as the source data changes
        Assert.isTrue(e.getId() == 5, "Element is " + e.getId() + " instead of 5");
    }
}
