package com.treydev.idk.location;

import java.util.ArrayList;

import com.treydev.idk.support.Element;
import com.treydev.idk.support.NameGenerator;
import com.treydev.idk.support.Random;

public class City extends LocationBase {
    public final static double LEVEL_TOLERANCE = 0.25;
    private static ArrayList<City> cities = new ArrayList<City>();

    private int population;

    public int getPopulation() {
        if (this.population == 0) {
            double bellcurve = 0;
            for (int i = 0; i < 500; i++) {
                bellcurve += Math.random() * 10;
            }
            this.population = (int) bellcurve;
        }
        return this.population;
    }

    public String getLeader() {
        // We are deriving the leader title from the (fixed) level to make it
        // reproducible without needing another attribute
        final String leaderPrefix[] = { "", "", "", "Mayor", "Sir", "Lady", "Lord", "Baron", "Count", "Countess",
                "Duke", "Prince", "Princess", "Sheik", "Queen", "King" };

        // Lower level cities will have lower levels
        int i = this.getLevel() * leaderPrefix.length / 100;
        if ((i < 0) || (i >= leaderPrefix.length)) {
            i = 0;
        }

        if (leaderPrefix[i].length() > 0)
            return leaderPrefix[i] + " " + this.locationLeader;
        else
            return this.locationLeader;
    }

    private ArrayList<Gym> gyms = null;

    public ArrayList<Gym> getGyms() {
        if (gyms == null) {
            gyms = new ArrayList<Gym>();
            gyms.add(new Gym(this));
        }
        return gyms;
    }

    private ArrayList<Shop> shops = null;

    public ArrayList<Shop> getShops() {
        if (shops == null) {
            shops = new ArrayList<Shop>();
            shops.add(new Shop(this));
        }
        return shops;
    }

    private City(int targetLevel) {
        super(targetLevel);
        this.name = City.generateCityName();
        // TO DO: Generate the rest of the city attributes
    }

    public static City getCityById(int id) {
        // Id is really just the index but we don't say
        if (id >= City.cities.size())
            return null;
        else
            return City.cities.get(id);
    }

    // This is mainly for testing - hopefully not a real use case
    public static void clearMap() {
        City.cities.clear();
        Gym.clearGyms();
        Shop.clearShops();
        Path.clearPaths();
    }

    public static City getCity(int targetLevel) {
        // Find all existing cities within +/- LEVEL_TOLERANCE of the target level
        ArrayList<City> possibleCities = new ArrayList<City>();
        cities.forEach(city -> {
            if ((city.getLevel() >= targetLevel * (1 - City.LEVEL_TOLERANCE))
                    && (city.getLevel() <= targetLevel * (1 + City.LEVEL_TOLERANCE))) {
                possibleCities.add(city);
            }
        });

        // First determine if a new or existing - as the list gets longer, new will be
        // less often
        int index = Random.nextInt(possibleCities.size() + 2);
        if (index >= possibleCities.size()) {
            // If this is a new city. make it target level to + LEVEL_TOLERANCE%
            int newTarget = targetLevel + (int) (Random.nextDouble() * targetLevel * City.LEVEL_TOLERANCE);
            City city = new City(newTarget);
            cities.add(city);
            return city;
        } else
            return possibleCities.get(index);
    }

    public static int getCityCount() {
        return cities.size();
    }

    public static String generateCityName() {
        String coreName = NameGenerator.generateRandomName();
        return generateExtendedCityName(coreName);
    }

    public static String generateExtendedCityName(String coreName) {
        // 33% of the time we'll have a prefix, 33% a suffix, and the remaining neither
        int randomNumber = Random.nextInt(3);

        switch (randomNumber) {
            case 0:
                String[] prefix = new String[] { "The City of ", "New ", "The Village of " };
                int prefixIndex = Random.nextInt(prefix.length);
                return prefix[prefixIndex] + coreName;
            case 1:
                String[] suffix = new String[] { "ton", " City", "town", "shire", " Township", "ville" };
                int suffixIndex = Random.nextInt(suffix.length);
                return coreName + suffix[suffixIndex];
            default:
                return coreName;
        }
    }

    @Override
    public int getId() {
        return cities.indexOf(this);
    }
}
