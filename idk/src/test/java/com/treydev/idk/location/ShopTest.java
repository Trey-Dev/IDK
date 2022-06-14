package com.treydev.idk.location;

import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;

public class ShopTest {
    @Test
    void testShopConstructor() {
        City city = City.getCity(1000L, 10);
        Shop shop = new Shop(city, 2000);
        Assert.isTrue(shop.getName().length() > 0, "Shop name is empty");
        Assert.isTrue(shop.getLevel() == city.getLevel(), "Shop level is not equal to city level");
        Assert.isTrue(shop.getOwner().length() > 0, "Shop owner is empty");
    }
}
