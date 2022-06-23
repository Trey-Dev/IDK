package com.treydev.idk.location;

import org.springframework.util.Assert;

import com.treydev.idk.support.Random;

import org.junit.jupiter.api.Test;

public class ShopTest {
    @Test
    void testShopConstructor() {
        Random.Initialize(1000);
        City city = City.getCity(10);
        Shop shop = new Shop(city, 2000);
        Assert.isTrue(shop.getName().length() > 0, "Shop name is empty");
        Assert.isTrue(shop.getLevel() == city.getLevel(), "Shop level is not equal to city level");
        Assert.isTrue(shop.getOwner().length() > 0, "Shop owner is empty");
    }
}
