package com.treydev.idk.location;

import java.util.ArrayList;
import java.util.Random;

public class Path {    
    //For now, we are only supporting city-to-city paths
    private City location1;
    private City location2;
    private int distance;
    public int getDistance() { return distance; }
    public City getOtherCity(City source) {
        if (this.location1.equals(source)) {
            return this.location2;
        }
        else {
            return this.location1;
        }
    }

    private final static int MAX_PATHS = 4;
    private final static double MIN_PATH_DISTANCE = 0.1; // 10% of the level of the current city
    private final static double MAX_PATH_DISTANCE = 1.5; // 150% of the level of the current city
    private static ArrayList<Path> paths = new ArrayList<Path>();

    private Path(City location1, City location2, int distance) {
        this.location1 = location1;
        this.location2 = location2;
        this.distance = distance;
    }

    public String getPathText(City location) {
        if (this.location1.equals(location)) {
            return "It is " + this.distance + " cliques to " + this.location2.getName();
        }
        else {
            return "It is " + this.distance + " cliques to " + this.location1.getName();
        }
    }

    public static ArrayList<Path> getPaths(City location, long seed) {
        ArrayList<Path> possiblePaths = new ArrayList<Path>();
        for (Path path : Path.paths) {
            if (path.location1.equals(location) || path.location2.equals(location)) {
                possiblePaths.add(path);
            }
        }

        // We always need paths to other cities, so if there are no paths, we need to add one
        if (possiblePaths.size() == 0) {
            // Find or create a new city
            City newCity = City.getCity(seed, location.getLevel()+1);
            // Create a new path
            Random generator = new Random(seed);
            int distance = (int)(generator.nextDouble() * (location.getLevel() * Path.MAX_PATH_DISTANCE - location.getLevel() * Path.MIN_PATH_DISTANCE) + location.getLevel() * Path.MIN_PATH_DISTANCE);
            Path newPath = new Path(location, newCity, distance );
            Path.paths.add(newPath);
            possiblePaths.add(newPath);
        }

        return possiblePaths;
    }
}
