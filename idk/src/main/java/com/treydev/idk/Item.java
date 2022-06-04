package com.treydev.idk;

import java.util.ArrayList;

public class Item {
    public Item(String name, short quantity, String description)
    {
        this.name=name;
        this.quantity=quantity;
        this.description=description;
    }
    public String name;
    public short quantity;
    public String description;
    public static ArrayList<Item> inventory;
    public static String consume(String name)
    {
       Item item = findByName(name);
       item.quantity--;
       if(item.quantity < 1) //0, or, somehow, negative
            inventory.remove(item);
        return "You consumed a " + name;
    }
    public static Item findByName(String name)
    {
        for(Item item : Item.inventory)
            if(item.name.equals(name))
                return item;
        return null; //TODO: be... worried about this I guess?
    }
    public static void InitializeStubbedInventory()
    {
        if(inventory == null)
        {
             inventory = new ArrayList<Item>();
             inventory.add(new Item("Red Potion", (short) 15, "Smells kinda fishy"));
             inventory.add(new Item("Pink Potion", (short) 11, "Smells kinda beefy"));
        }
    }
}
