package com.treydev.idk.location;

import java.util.ArrayList;
import java.util.Random;

public class City {
    public final static double LEVEL_TOLERANCE = 0.25;
    private static ArrayList<City> cities = new ArrayList<City>();

    private String name;
    public String getName() { return name; }

    private int baseLevel;
    public int getLevel() { return baseLevel; }

    // private ArrayList<City> neighbors = new ArrayList<City>();
    // public ArrayList<City> getNeighbors() { return neighbors; }

    // private Region region;
    // private String description;
    // private ArrayList<Gym> gyms = new ArrayList<Gym>();

    private City(long seed, int targetLevel) {
        this.name = City.generateCityName(seed);
        this.baseLevel = targetLevel;
        // TO DO: Generate the rest of the city attributes
    }

    public static City getCity(int targetLevel) {
        // Generate a random seed to use for the predictable random number generator
        long seed = (long)(Math.random() * Long.MAX_VALUE);
        return getCity(seed, targetLevel);
    }

    // This is mainly for testing - hopefully not a real use case
    public static void clearMap() {
        City.cities.clear();
    }

    public static City getCity(long seed, int targetLevel) {
        // Find all existing cities within +/- LEVEL_TOLERANCE of the target level
        ArrayList<City> possibleCities = new ArrayList<City>();
        cities.forEach(city -> {
            if ((city.getLevel() >= targetLevel*(1-City.LEVEL_TOLERANCE)) 
                && (city.getLevel() <= targetLevel*(1+City.LEVEL_TOLERANCE))) {
                possibleCities.add(city);
            }
        });

        // First determine if a new or existing - as the list gets longer, new will be less often
        Random generator = new Random(seed);
        int index = generator.nextInt(possibleCities.size()+2);
        if (index >= possibleCities.size()) {
            // If this is a new city. make it target level to + LEVEL_TOLERANCE%
            int newTarget = targetLevel + (int)(generator.nextDouble() * targetLevel * City.LEVEL_TOLERANCE);
            City city = new City(generator.nextLong(), newTarget);
            cities.add(city);
            return city;
        }
        else
            return possibleCities.get(index);
    }


    public static int getCityCount() {
        return cities.size();
    }

    public static String generateCityName(long seed) {
        String coreName = "Stub"; // TO DO: call Trey's name generator with the seed
        return generateExtendedCityName(coreName, seed);
    }

    public static String generateExtendedCityName(String coreName, long seed) {
        // 33% of the time we'll have a prefix, 33% a suffix, and the remaining neither
        Random generator = new Random(seed);
        int randomNumber = generator.nextInt(3);

        switch (randomNumber) {
            case 0:
                String[] prefix = new String[]{"City of ", "New ", "Village of "};
                int prefixIndex = generator.nextInt(prefix.length);
                return prefix[prefixIndex] + coreName;
            case 1:
                String[] suffix = new String[]{"ton", " City", "town", "shire", " Township", "ville"};
                int suffixIndex = generator.nextInt(suffix.length);
                return coreName + suffix[suffixIndex];
            default:
                return coreName;
        }
    }
}