package com.treydev.idk.location;

import java.util.ArrayList;
import java.util.Random;
import com.treydev.idk.Item;

public class Shop extends LocationBase {
    public String getOwner() { return this.locationLeader; }

    private static ArrayList<Shop> allShops = new ArrayList<Shop>();

    // TO DO: This is very much a place holder for items in this Shop
    private ArrayList<Item> items;
    public ArrayList<Item> getItems() { return items; }

    public Shop(City city, long seed) {
        super(seed, city.getLevel());
        this.name = this.generateShopName(seed, city);
        this.items = new ArrayList<Item>();
        // TODO: Populate Shop items
        Shop.allShops.add(this);
    }

    public Shop(City city) {
        this(city, (long)(Math.random() * Long.MAX_VALUE));
    }

    private String generateShopName(long seed, City city) {
        Random generator = new Random(seed);
        int i = generator.nextInt(4);
        switch (i) {
            case 0:
                return "The " + city.getName() + " Shop";
            case 1:
                return "The " + city.getName() + " Marketplace";  
            case 2:
                return this.getOwner() + "'s Market";
            default:
                return city.getName() + "'s Exchange";
        }
    }

    @Override
    public int getId() {
        return Shop.allShops.indexOf(this);
    }

    public static void clearShops() {
        Shop.allShops.clear();
    }
}
