package com.treydev.idk.location;

import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;

public class GymTest {
    @Test
    void testGymConstructor() {
        City city = City.getCity(1000L, 10);
        Gym gym = new Gym(city, 2000);
        Assert.isTrue(gym.getName().length() > 0, "Gym name is empty");
        Assert.isTrue(gym.getName().contains(city.getName()), "Gym name does not contain city name");
        Assert.isTrue(gym.getLevel() == city.getLevel(), "Gym level is not equal to city level");
        Assert.isTrue(gym.getOwner().length() > 0, "Gym owner is empty");
        Assert.isTrue(gym.getCritters().size() > 0, "Gym has no critters");
    }

    @Test
    void testGetId() {
        City.clearMap();
        City c1 = City.getCity(1000L, 10);
        Gym g1 = c1.getGyms().get(0);

        City c2 = City.getCity(2000L, 20);
        Gym g2 = c2.getGyms().get(0);

        Assert.isTrue(g1.getId() == 0, "Gym Id is not 0");
        Assert.isTrue(g2.getId() == 1, "Gym Id is not 1");
    }

    @Test
    void testGetCritters() {
        City.clearMap();
        City c1 = City.getCity(1000L, 1);
        Gym g1 = c1.getGyms().get(0);
        // At L1 we should always have 1 critter
        Assert.isTrue(g1.getCritters().size() == 1, "Gym has no critters");

        City c2 = City.getCity(2000L, 99);
        Gym g2 = c2.getGyms().get(0);
        // At L100 we should always have 6 critters
        Assert.isTrue(g2.getCritters().size() == 6, "Gym has no critters");
    }
}
