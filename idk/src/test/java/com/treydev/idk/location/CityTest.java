package com.treydev.idk.location;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class CityTest {
    @Test
    public void testCity() {
        City city = City.getCity(1);
        Assert.isTrue(city.getName().length() > 0, "City name is empty");
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
        for (int i = 0; i < testCount; i++) {
            // Each time we get a city, there is a decreasing chance that it will be new
            City.getCity(50);
        }
        // NOTE: Based on current logic - 1000 attempts returns 44, 100 returns 15, and 10 returns 3
        Assert.isTrue(City.getCityCount() < testCount, "All cities were unique");
    }

    @Test
    void testGenerateExtendedCityName() {
        String cityName;

        // Seed of 3 will generate a non-extended name
        cityName = City.generateExtendedCityName("Core",3);
        Assert.isTrue(cityName.equals("Core"), "Received city name " + cityName + " instead of 'Core'");

        // Seed of 23 will generate a suffixed name
        cityName = City.generateExtendedCityName("Suffix",23);
        Assert.isTrue(cityName.equals("Suffixshire"), "Received city name " + cityName + " instead of 'Suffixshire'");

        // Seed of 10 will generate a prefixed name
        cityName = City.generateExtendedCityName("Prefix",10);
        Assert.isTrue(cityName.equals("City of Prefix"), "Received city name " + cityName + " instead of 'City of Prefix'");
    }
}
