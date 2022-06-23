package com.treydev.idk.support;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class RandomTests {
    @Test
    void testInitialize() {
        Random.Initialize(100);
        int i = Random.nextInt(100);
        Assert.isTrue(i == 15, "Random.nextInt(100) returned wrong value for known seed - " + i);
    }

    @Test
    void testNextDouble() {
        double d = Random.nextDouble();
        Assert.isTrue(d >= 0.0, "Returned negative value for Random.nextDouble()");
        Assert.isTrue(d < 1.0, "Returned value greater than 1.0 for Random.nextDouble()");
    }

    @Test
    void testNextFloat() {
        float f = Random.nextFloat();
        Assert.isTrue(f >= 0.0, "Returned negative value for Random.nextFloat()");
        Assert.isTrue(f < 1.0, "Returned value greater than 1.0 for Random.nextFloat()");
    }

    @Test
    void testNextFloatRanged() {
        float f = Random.nextFloat(1.0f, 1.1f);
        Assert.isTrue(f >= 1.0, "Returned negative value for Random.nextFloat(1.0f, 1.1f)");
        Assert.isTrue(f < 1.1, "Returned value greater than 1.0 for Random.nextFloat(1.0f, 1.1f)");
    }

    @Test
    void testNextInt() {
        int i = Random.nextInt(100);
        Assert.isTrue(i >= 0, "Returned negative value for Random.nextInt(100)");
        Assert.isTrue(i < 100, "Returned value greater than 100 for Random.nextInt(100)");
    }

    @Test
    void testNextIntWithMin() {
        int i = Random.nextInt(100, 200);
        Assert.isTrue(i >= 100, "Returned negative value for Random.nextInt(100, 200)");
        Assert.isTrue(i < 200, "Returned value greater than 200 for Random.nextInt(100, 200)");
    }
}
