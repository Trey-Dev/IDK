package com.treydev.idk.support;

import org.junit.jupiter.api.Test;

public class NameGeneratorTests {
    @Test
    void testGenerateRandomName() {
        for (long i = 0; i < 100; i++) {
            Random.Initialize(i);
            String s = NameGenerator.generateRandomName();
            System.out.println(i + " - " + s);
        }
    }
}
