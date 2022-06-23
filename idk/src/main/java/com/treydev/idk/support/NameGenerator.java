package com.treydev.idk.support;

import com.treydev.idk.support.Random;

public class NameGenerator {
    public static String[] StartConsonantGroups = { "T", "D", "TH", "ST", "H", "G", "GR", "TR", "C", "K",
            "S", "FR", "CHR", "KR", "W", "WR", "P", "PR", "PL", "BL", "B", "BR" };
    public static String[] EndConsonantGroups = { "T", "D", "NT", "TH", "ND", "ST", "RD", "RT", "LD", "LT", "CK", "LK",
            "FT", "RST", "RLD", "Z", "RZ", "W" };
    public static String[] VowelGroups = { "A", "E", "I", "O", "U", "AE", "AI", "AU", "AY", "EI", "EU",
            "OU", "OY", "OO", "EE", "OE", "IA" };

    public static String generateRandomName() {
        /*
         * Current implementation: 30% chance to end the word on any syllable
         */
        // Get the first character
        // Randomly choose first is const or vowel
        // If const, select first from start list

        String value = begin(0);

        return value.charAt(0) + value.substring(1).toLowerCase();
    }

    private static String begin(int syllables) {
        if (Random.nextDouble() > 0.5)
            return GenStartConsonant(syllables + 1);
        else
            return GenVowel(syllables + 1);
    }

    private static String GenStartConsonant(int syllables) {
        String value = StartConsonantGroups[Random.nextInt(0, StartConsonantGroups.length)];
        return value + GenVowel(syllables + 1);
    }

    private static String GenVowel(int syllables) {
        String value = VowelGroups[Random.nextInt(0, VowelGroups.length)];
        double RNG = Random.nextDouble();
        if (RNG < 0.2 || syllables > 6)
            return value;
        else if (RNG < 0.6)
            return value + GenEndConsonant(syllables + 1);
        else
            return value + GenStartConsonant(syllables + 1);
    }

    private static String GenEndConsonant(int syllables) {
        String value = EndConsonantGroups[Random.nextInt(0, EndConsonantGroups.length)];
        double RNG = Random.nextDouble();
        if (RNG < 0.2 || syllables > 6)
            return value;
        else if (RNG < 0.6)
            return value + GenStartConsonant(syllables + 1);
        else
            return value + GenVowel(syllables + 1);
    }
}
