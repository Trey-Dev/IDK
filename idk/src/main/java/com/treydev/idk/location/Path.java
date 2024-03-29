package com.treydev.idk.location;

import java.util.ArrayList;

import com.treydev.idk.support.Element;
import com.treydev.idk.support.Random;
import com.treydev.idk.attack.Move;
import com.treydev.idk.critter.*;

public class Path {
    // For now, we are only supporting city-to-city paths - no forks
    private City location1;
    private City location2;
    private int distance;
    private Species[] species;
    private Element[] elements;

    public int getDistance() {
        return distance;
    }

    public City getOtherCity(City source) {
        if (this.location1.equals(source)) {
            return this.location2;
        } else {
            return this.location1;
        }
    }

    private final static int MAX_PATHS = 6;
    private final static double MIN_PATH_DISTANCE = 0.1; // 10% of the level of the current city
    private final static double MAX_PATH_DISTANCE = 1.5; // 150% of the level of the current city
    private static ArrayList<Path> paths = new ArrayList<Path>();

    private Path(City location1, City location2, int distance) {
        this.location1 = location1;
        this.location2 = location2;
        this.distance = distance;
        this.species = new Species[distance]; //Note: we used distance because the number of species likely scales with it. 
                        //It was a somewhat arbitrary choice to make them the exact same.
        this.elements = new Element[2];
        this.elements[0] = Element.getRandomElement();
        this.elements[1] = Element.getRandomElement();
    }

    public String getPathText(City location) {
        if (this.location1.equals(location)) {
            return "It is " + this.distance + " cliques to " + this.location2.getName();
        } else {
            return "It is " + this.distance + " cliques to " + this.location1.getName();
        }
    }

    public static ArrayList<Path> getPaths(City location) {
        ArrayList<Path> possiblePaths = new ArrayList<Path>();
        for (Path path : Path.paths) {
            if (path.location1.equals(location) || path.location2.equals(location)) {
                possiblePaths.add(path);
            }
        }

        // We always need paths to other cities, so if there are no paths, we need to
        // add one
        if (possiblePaths.size() == 0) {
            Path newPath = generateNewPath(location);
            Path.paths.add(newPath);
            possiblePaths.add(newPath);
        }

        if (possiblePaths.size() < Path.MAX_PATHS) {
            // If we have under the max number of paths, we are allowed to add more
            // We will try to find an existing city in the right level range that doesn't
            // have too many connections

            // The higher the level, the less likely you'll find additional paths
            double likelyhood = 1.5 - (location.getLevel() / 100);
            // And the more existing paths, the less ikely to find more
            likelyhood /= possiblePaths.size();
            if (Random.nextDouble() < likelyhood) {
                Path newPath = generateNewPath(location);
                if (!pathAlreadyExists(newPath.location1, newPath.location2)) {
                    Path.paths.add(newPath);
                    possiblePaths.add(newPath);
                }
            }
        }

        return possiblePaths;
    }

    public Critter findCritter()
    {
        Species species = findSpecies();
        String name = "Unnnamed";
        int level = ((this.location1.getLevel() + this.location2.getLevel()) / 2) + Random.nextInt(10) - 5; //replace hardcoded randomness
        Move [] moves = new Move[4]; //TODO: make function to get random moves in species
        return new Critter(name, species, moves, level);
    }

    public Species findSpecies()
    {
        int index = Random.nextInt(species.length);
        Species value = species[index];
        if(value == null)
        {
            int elementSelection = Random.nextInt(10) / 4;
            if(elementSelection < 2)
                value = Species.findSpecies(elements[elementSelection]);
            else
                value = Species.findSpecies();
            species[index] = value;
        }
        return value;
    }

    public int getId() {
        return Path.paths.indexOf(this);
    }

    public static void clearPaths() {
        Path.paths.clear();
    }

    private static boolean pathAlreadyExists(City location1, City location2) {
        for (Path path : Path.paths) {
            if (path.location1.equals(location1) && path.location2.equals(location2)) {
                return true;
            }
            if (path.location1.equals(location2) && path.location2.equals(location1)) {
                return true;
            }
        }
        return false;
    }

    private static Path generateNewPath(City location) {
        // Find or create a new city
        City newCity = null;
        while (newCity == null) {
            newCity = City.getCity(location.getLevel() + 1);
            if (newCity.equals(location)) {
                newCity = null;
            }
        }
        // Create a new path
        int distance = 2 + (int) (Random.nextDouble()
                * (location.getLevel() * Path.MAX_PATH_DISTANCE - location.getLevel() * Path.MIN_PATH_DISTANCE)
                + location.getLevel() * Path.MIN_PATH_DISTANCE);
        Path newPath = new Path(location, newCity, distance);
        return newPath;
    }

    public static Path getById(String id) {
        String pathId = id.substring(id.indexOf(".") + 1);
        // String sourceCityId = id.substring(0, id.indexOf("."));
        return Path.paths.get(Integer.parseInt(pathId));
    }
}
