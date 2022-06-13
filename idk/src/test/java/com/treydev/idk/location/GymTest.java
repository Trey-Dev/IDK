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
    }
}
