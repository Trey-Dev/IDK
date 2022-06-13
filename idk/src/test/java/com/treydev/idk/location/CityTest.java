package com.treydev.idk.location;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.treydev.idk.location.City.ElementAffinity;

public class CityTest {
    @Test
    public void testCityByLevel() {
        City city = City.getCityByLevel(1);
        Assert.isTrue(city.getName().length() > 0, "City name is empty");
    }

    @Test
    void testCityById() {
        City.clearMap();
        City city1 = City.getCityByLevel(1);
        City city2 = City.getCityByLevel(10);
        City city3 = City.getCityByLevel(50);

        Assert.isTrue(City.getCityCount() == 3, "Should have three unique cities");
        Assert.isTrue(City.getCityById(0).equals(city1), "First city should have Id=0");
        Assert.isTrue(City.getCityById(1).equals(city2), "Second city should have Id=1");
        Assert.isTrue(City.getCityById(2).equals(city3), "Third city should have Id=2");
        Assert.isNull(City.getCityById(3), "Should return null for invalid Id");
    }

    @Test
    public void testCityCount() {
        // This will inherently create a city and add it to the list
        City.getCityByLevel(1);
        Assert.isTrue(City.getCityCount() > 0, "City count is zero");
    }

    @Test
    public void testGetCityReturnsExisting() {
        int testCount = 10;
        City.clearMap();
        for (int i = 0; i < testCount; i++) {
            // Each time we get a city, there is a decreasing chance that it will be new
            City.getCity(10000*i, 50);
        }
        // NOTE: Based on current logic - 1000 attempts returns 44, 100 returns 15, and 10 returns 3
        Assert.isTrue(City.getCityCount() < testCount, "All cities were unique");
    }

    @Test
    public void testGenerateExtendedCityName() {
        String cityName;

        // Seed of 3 will generate a non-extended name
        cityName = City.generateExtendedCityName("Core",3);
        Assert.isTrue(cityName.equals("Core"), "Received city name " + cityName + " instead of 'Core'");

        // Seed of 23 will generate a suffixed name
        cityName = City.generateExtendedCityName("Suffix",23);
        Assert.isTrue(cityName.equals("Suffixtown"), "Received city name " + cityName + " instead of 'Suffixshire'");

        // Seed of 10 will generate a prefixed name
        cityName = City.generateExtendedCityName("Prefix",10);
        Assert.isTrue(cityName.equals("City of Prefix"), "Received city name " + cityName + " instead of 'City of Prefix'");
    }

    @Test
    public void testCityListByLevel() {
        // This will create a series of cities based on target levels
        // Start with a seeded city for level 1, to control randomness
        City c = City.getCity(1000, 1);
        verifyCityLevel(1, c);

        for(int i=2;i<100; i++) {
            c = City.getCityByLevel(i);
            verifyCityLevel(i, c);
        }
    }

    private void verifyCityLevel(int targetLevel, City c) {
        // System.out.println(Integer.toString(targetLevel) + " - " + c.getName() + " - " + c.getLevel());
        boolean valid = (c.getLevel() >= targetLevel*(1-City.LEVEL_TOLERANCE)) && (c.getLevel() <= targetLevel*(1+City.LEVEL_TOLERANCE));
        Assert.isTrue(valid, "Invalid city for target level " + targetLevel);
    }
    
    @Test
    void testGetCityElements() {
        City c = City.getCity(1000, 1);
        ElementAffinity[] elements = c.getElements();
        // For this version, we wil have exactly one element with 90% affinity
        Assert.isTrue(elements.length == 1, "City should have exactly one element");
        Assert.isTrue(elements[0].affinity == 0.9, "Element affinity should be 90%");
        Assert.isTrue(elements[0].element != null, "Element should be valid");
    }
}
