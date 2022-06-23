package com.treydev.idk.support;

public class Random {
    private static java.util.Random random = new java.util.Random();

    public static void Initialize(long seed) {
        random = new java.util.Random(seed);
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static int nextInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static float nextFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static long nextLong() {
        return random.nextLong();
    }
}
