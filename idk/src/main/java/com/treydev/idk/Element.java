package com.treydev.idk;

public class Element
{
    public enum Type
    {
        NONE,
        FIRE,
        WATER,
        EARTH,
        AIR,
        MUD,
        STONE,
        ELECTRIC
    }

    private Type type;

    // Constructor
    private Element(Type type)
    {
        this.type = type;
    }

    // Getters
    public static Element getElement(Type element)
    {
        return new Element(element);
    }

    public static Element getRandomElement(double randomNumber)
    {
        int i = (int)(Type.values().length * randomNumber);
        Type t = Type.values()[i];
        return new Element(t);      
    }

    public static Element getRandomElement()
    {
        return getRandomElement(Math.random());
    }

    public double getCombatMultiplierFor(Element e)
    {
        return 1.0;
    }

    public String getName()
    {
        if (this.type == Type.NONE)
            return "";
        else
            return this.type.name().toString().toLowerCase();
    }

    public Type getType()
    {
        return this.type;
    }
}
