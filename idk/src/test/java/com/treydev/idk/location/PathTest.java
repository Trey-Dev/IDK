package com.treydev.idk.location;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.treydev.idk.support.Random;

public class PathTest {
    @Test
    void testGetPathText() {
        // Starting from scratch, try to get a path to a city (should create a second)
        Random.Initialize(10);
        City city1 = City.getCity( 25);
        Random.Initialize(1000);
        ArrayList<Path> paths = Path.getPaths(city1);
        Path foundPath = paths.get(0);
        City city2 = foundPath.getOtherCity(city1);

        // Check text for direction 1
        String pathText = paths.get(0).getPathText(city1);
        Assert.isTrue(pathText.contains("It is "), "Path text should contain 'It is'");
        Assert.isTrue(pathText.contains(foundPath.getDistance() + " cliques to "),
                "Path text should contain 'cliques to'");
        Assert.isTrue(pathText.endsWith(city2.getName()), "Path text should contain the destination city name");

        // Check text for direction 1
        pathText = paths.get(0).getPathText(city2);
        Assert.isTrue(pathText.contains("It is "), "Path text should contain 'It is'");
        Assert.isTrue(pathText.contains(foundPath.getDistance() + " cliques to "),
                "Path text should contain 'cliques to'");
        Assert.isTrue(pathText.contains(city1.getName()), "Path text should contain the destination city name");
    }

    @Test
    void testGetSinglePath() {
        // Starting from scratch, try to get a path to a city (should create a second)
        City city1 = City.getCity(50);

        Random.Initialize(1000);
        ArrayList<Path> paths = Path.getPaths(city1);

        // Verify we have a new city with 1 to MAX paths
        Assert.isTrue(paths.size() >= 1, "We should have at least one path");
        Assert.isTrue(paths.size() <= 6, "We should have 6 or fewer paths");
    }

    @Test
    void testGetId() {
        City.clearMap();
        City city1 = City.getCity(5);
        // For the first city, all paths should be the same as the
        Random.Initialize(1000);
        ArrayList<Path> paths1 = Path.getPaths(city1);
        for (int i = 0; i < paths1.size(); i++) {
            Path path = paths1.get(i);
            Assert.isTrue(path.getId() == i, "Path ID should be " + i);
        }

        // For the second city, paths IDs should continue from the previous city
        City city2 = City.getCity(70);
        Random.Initialize(1000);
        ArrayList<Path> paths2 = Path.getPaths(city2);
        for (int i = 0; i < paths2.size(); i++) {
            Path path = paths2.get(i);
            Assert.isTrue(path.getId() == i + paths1.size(), "Path ID should be " + i);
        }
    }
}