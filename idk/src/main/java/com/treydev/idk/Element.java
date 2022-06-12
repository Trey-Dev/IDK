package com.treydev.idk;

import java.util.Random;

public class Element
{    
    private static final String[] elementNames={"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
    private static final Element elements[] = new Element[elementNames.length];
    private int id;

    // Initialize the elements array to point to the element names
    static {
        for (int i = 0; i < elementNames.length; i++) {
            elements[i] = new Element(i);
        }
    }

    // Static methods
    public static int getElementCount()
    {
        return elements.length;
    }

    public static Element getRandomElement() {
        long seed = (long)(Math.random() * Long.MAX_VALUE);
        return getRandomElement(seed);
    }

    public static Element getRandomElement(long seed)
    {
        Random generator = new Random(seed);
        return elements[generator.nextInt(elementNames.length)];
    }

    public static Element getByName(String name)
    {
        for (int i = 0; i < elementNames.length; i++) {
            if (elementNames[i].equals(name)) {
                return elements[i];
            }
        }
        return null;
    }

    public static Element getById(int id) {
        if (id >= 0 && id < elementNames.length)
            return elements[id];
        else
            return null;
    }

    // Instance methods
    private Element(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName()
    {
        return elementNames[this.id];
    }
}
