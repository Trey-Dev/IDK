package com.treydev.idk.location;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.treydev.idk.support.ElementAffinity;
import com.treydev.idk.support.Random;

public class CityTest {
    @Test
    public void testCityByLevel() {
        City city = City.getCity(1);
        Assert.isTrue(city.getName().length() > 0, "City name is empty");
    }

    @Test
    void testCityById() {
        City.clearMap();
        City city1 = City.getCity(1);
        City city2 = City.getCity(10);
        City city3 = City.getCity(50);

        Assert.isTrue(City.getCityCount() == 3, "Should have three unique cities");
        Assert.isTrue(City.getCityById(0).equals(city1), "First city should have Id=0");
        Assert.isTrue(City.getCityById(1).equals(city2), "Second city should have Id=1");
        Assert.isTrue(City.getCityById(2).equals(city3), "Third city should have Id=2");
        Assert.isNull(City.getCityById(3), "Should return null for invalid Id");
    }

    @Test
    public void testCityCount() {
        // This will inherently create a city and add it to the list
        City.getCity(1);
        Assert.isTrue(City.getCityCount() > 0, "City count is zero");
    }

    @Test
    public void testGetCityReturnsExisting() {
        int testCount = 10;
        City.clearMap();
        for (int i = 0; i < testCount; i++) {
            // Each time we get a city, there is a decreasing chance that it will be new
            City.getCity(50);
        }
        // NOTE: Based on current logic - 1000 attempts returns 44, 100 returns 15, and
        // 10 returns 3
        Assert.isTrue(City.getCityCount() < testCount, "All cities were unique");
    }

    @Test
    public void testGenerateExtendedCityName() {
        String cityName;

        // All of the below will change if the seed changes or the procssing around that
        // seed changes
        // Seed of 3 will generate a non-extended name
        Random.Initialize(3);
        cityName = City.generateExtendedCityName("Core");
        Assert.isTrue(cityName.equals("Core"), "Received city name " + cityName + " instead of 'Core'");

        // Seed of 23 will generate a suffixed name
        Random.Initialize(23);
        cityName = City.generateExtendedCityName("Suffix");
        Assert.isTrue(cityName.equals("Suffixtown"), "Received city name " + cityName + " instead of 'Suffixshire'");

        // Seed of 10 will generate a prefixed name
        Random.Initialize(10);
        cityName = City.generateExtendedCityName("Prefix");
        Assert.isTrue(cityName.equals("The City of Prefix"),
                "Received city name " + cityName + " instead of 'City of Prefix'");
    }

    @Test
    public void testCityListByLevel() {
        // This will create a series of cities based on target levels
        // Start with a seeded city for level 1, to control randomness
        City c = City.getCity(1);
        verifyCityLevel(1, c);

        for (int i = 2; i < 100; i++) {
            c = City.getCity(i);
            verifyCityLevel(i, c);
        }
    }

    private void verifyCityLevel(int targetLevel, City c) {
        // System.out.println(Integer.toString(targetLevel) + " - " + c.getName() + " -
        // " + c.getLevel());
        boolean valid = (c.getLevel() >= targetLevel * (1 - City.LEVEL_TOLERANCE))
                && (c.getLevel() <= targetLevel * (1 + City.LEVEL_TOLERANCE));
        Assert.isTrue(valid, "Invalid city for target level " + targetLevel);
    }

    @Test
    void testGetCityElements() {
        City c = City.getCity(1);
        Random.Initialize(1000);
        ElementAffinity[] elements = ElementAffinity.GetRandomAffinity(1);
        // For this version, we wil have exactly one element with 90% affinity
        Assert.isTrue(elements.length == 1, "City should have exactly one element");
        Assert.isTrue(elements[0].getAffinity() >= 0.0, "Element affinity should be positive");
        Assert.isTrue(elements[0].getElement() != null, "Element should be valid");
    }

    @Test
    void testGetGyms() {
        City c = City.getCity(1);
        // For this release, we will always have exactly one gym
        Assert.isTrue(c.getGyms().size() == 1, "City should have exactly one gym");
    }

    @Test
    void testGetShops() {
        City c = City.getCity(1);
        // For this release, we will always have exactly one shop
        Assert.isTrue(c.getShops().size() == 1, "City should have exactly one shop");
    }

    @Test
    void testGetLeader() {
        Random.Initialize(2000);
        City c = City.getCity(1);
        // For this release, we will always have exactly one leader
        Assert.isTrue(c.getLeader() != null, "City should have exactly one leader");
        // At low levels, the leader will not have a title and we assume this works for
        // the rest
        String leaderName = c.getLeader();
        Random.Initialize(2000);
        c = City.getCity(20);
        Assert.isTrue(c.getLeader().contains(leaderName), "Higher level cities should use the same name");
        Assert.isTrue(c.getLeader().length() > leaderName.length(), "Higher level cities should include a prefix");
    }

    @Test
    void testGetId() {
        City.clearMap();
        Random.Initialize(1000);
        City c1 = City.getCity(1);
        Assert.isTrue(c1.getId() == 0, "First city should have Id=0");
        Random.Initialize(2000);
        City c2 = City.getCity(20);
        Assert.isTrue(c2.getId() == 1, "Second city should have Id=1");
    }

}
