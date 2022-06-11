package com.treydev.idk;

import org.junit.jupiter.api.Test;

public class NameGeneratorTests {
    @Test
    void testGenerateRandomName() { 
        for(int i = 0; i < 100; i++)
        {
            String s = NameGenerator.generateRandomName((long) i);
            System.out.println(i + " - " + s);
        }
    }
}
