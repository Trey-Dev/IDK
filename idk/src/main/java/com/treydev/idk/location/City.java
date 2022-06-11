package com.treydev.idk.location;

import java.util.ArrayList;
import java.util.Random;

public class City {
    private static ArrayList<City> cities = new ArrayList<City>();

    private String name;
    public String getName() { return name; }

    private ArrayList<City> neighbors = new ArrayList<City>();
    public ArrayList<City> getNeighbors() { return neighbors; }
    
    // private Region region;
    // private String description;
    // private ArrayList<Gym> gyms = new ArrayList<Gym>();

    private City(long seed) {
        this.name = City.generateCityName(seed);
        // TO DO: Generate the rest of the city attributes
    }

    public static City getCity() {
        // Generate a random seed to use for the predictable random number generator
        long seed = (long)(Math.random() * Long.MAX_VALUE);
        return getCity(seed);
    }

    public static City getCity(long seed) {
        // First determine if a new or existing - as the list gets longer, new will be less often
        Random generator = new Random(seed);
        int index = generator.nextInt(cities.size()+1);
        if (index >= cities.size()) {
            City city = new City(generator.nextLong());
            cities.add(city);
        }
        return cities.get(index);
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
                String[] suffix = new String[]{"ton", " City", "town", "shire", " Township"};
                int suffixIndex = generator.nextInt(suffix.length);
                return coreName + suffix[suffixIndex];
            default:
                return coreName;
        }
    }
}
