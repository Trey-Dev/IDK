package com.treydev.idk.location;

import java.util.ArrayList;

import com.treydev.idk.support.Item;
import com.treydev.idk.support.Random;

public class Shop extends LocationBase {
    public String getOwner() {
        return this.locationLeader;
    }

    private static ArrayList<Shop> allShops = new ArrayList<Shop>();

    // TO DO: This is very much a place holder for items in this Shop
    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public Shop(City city, long seed) {
        super(seed, city.getLevel());
        this.name = this.generateShopName(seed, city);
        this.items = new ArrayList<Item>();
        // TODO: Populate Shop items
        Shop.allShops.add(this);
    }

    public Shop(City city) {
        this(city, (long) (Math.random() * Long.MAX_VALUE));
    }

    private String generateShopName(long seed, City city) {
        Random.Initialize(seed);
        int i = Random.nextInt(4);
        switch (i) {
            case 0:
                return city.getName() + " Shop";
            case 1:
                return city.getName() + " Marketplace";
            case 2:
                return this.getOwner() + "'s Market";
            default:
                return city.getName() + "'s Exchange";
        }
    }

    public static Shop getById(int id) {
        return Shop.allShops.get(id);
    }

    @Override
    public int getId() {
        return Shop.allShops.indexOf(this);
    }

    public static void clearShops() {
        Shop.allShops.clear();
    }
}
