package com.treydev.idk;

import java.util.Random;

public class NameGenerator {
    public static String[] StartConsonantGroups = { "T", "D", "TH", "ST", "H", "G", "GR", "TR", "C", "K",
            "S", "FR", "CHR", "KR", "W", "WR", "P", "PR", "PL", "BL", "B", "BR" };
    public static String[] EndConsonantGroups = { "T", "D", "NT", "TH", "ND", "ST", "RD", "RT", "LD", "LT", "CK", "LK",
            "FT", "RST", "RLD", "Z", "RZ", "W" };
    public static String[] VowelGroups = { "A", "E", "I", "O", "U", "AE", "AI", "AU", "AY", "EI", "EU",
            "OU", "OY", "OO", "EE", "OE", "IA" };

    public static String generateRandomName(long seed) {
        Random random = new Random(seed);
        /*
         * Current implementation: 30% chance to end the word on any syllable
         */
        // Get the first character
        // Randomly choose first is const or vowel
        // If const, select first from start list

        String value = begin(random, 0);

        return value.charAt(0) + value.substring(1).toLowerCase();
    }

    private static String begin(Random random, int syllables) {
        if (random.nextDouble() > 0.5)
            return GenStartConsonant(random, syllables + 1);
        else
            return GenVowel(random, syllables + 1);
    }

    private static String GenStartConsonant(Random random, int syllables) {
        String value = StartConsonantGroups[random.nextInt(0, StartConsonantGroups.length)];
        return value + GenVowel(random, syllables + 1);
    }

    private static String GenVowel(Random random, int syllables) {
        String value = VowelGroups[random.nextInt(0, VowelGroups.length)];
        double RNG = random.nextDouble();
        if (RNG < 0.2 || syllables > 6)
            return value;
        else if (RNG < 0.6)
            return value + GenEndConsonant(random, syllables + 1);
        else
            return value + GenStartConsonant(random, syllables + 1);
    }

    private static String GenEndConsonant(Random random, int syllables) {
        String value = EndConsonantGroups[random.nextInt(0, EndConsonantGroups.length)];
        double RNG = random.nextDouble();
        if (RNG < 0.2 || syllables > 6)
            return value;
        else if (RNG < 0.6)
            return value + GenStartConsonant(random, syllables + 1);
        else
            return value + GenVowel(random, syllables + 1);
    }

}
