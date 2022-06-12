package com.treydev.idk.location;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class PathTest {
    @Test
    void testGetPathText() {
        // Starting from scratch, try to get a path to a city (should create a second)
        City city1 = City.getCity(10, 25);
        ArrayList<Path> paths = Path.getPaths(city1, 1000);
        Path foundPath = paths.get(0);
        City city2 = foundPath.getOtherCity(city1);

        // Check text for direction 1
        String pathText = paths.get(0).getPathText(city1);
        Assert.isTrue(pathText.contains("It is "), "Path text should contain 'It is'");
        Assert.isTrue(pathText.contains(foundPath.getDistance() + " cliques to "), "Path text should contain 'cliques to'");
        Assert.isTrue(pathText.endsWith(city2.getName()), "Path text should contain the destination city name");
        
        // Check text for direction 1
        pathText = paths.get(0).getPathText(city2);
        Assert.isTrue(pathText.contains("It is "), "Path text should contain 'It is'");
        Assert.isTrue(pathText.contains(foundPath.getDistance() + " cliques to "), "Path text should contain 'cliques to'");
        Assert.isTrue(pathText.contains(city1.getName()), "Path text should contain the destination city name");
    }

    @Test
    void testGetSinglePath() {
        // Starting from scratch, try to get a path to a city (should create a second)
        City city1 = City.getCityByLevel(50);

        ArrayList<Path> paths = Path.getPaths(city1, 1000);

        // Verify we have a new city with 1 to MAX paths
        Assert.isTrue(paths.size() >= 1, "We should have exactly 1 path");
        Assert.isTrue(paths.size() <= 6, "We should have 6 or fewer paths");
    }
}