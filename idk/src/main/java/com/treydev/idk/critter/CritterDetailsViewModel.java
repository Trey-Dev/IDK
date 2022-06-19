package com.treydev.idk.critter;

public class CritterDetailsViewModel {
    public class statLine {
        public String attributeName;
        public String attributeValue;
        public String attributeColor;

        public statLine(String name, String value, String color) {
            this.attributeName = name;
            this.attributeValue = value;
            this.attributeColor = color;
        }
    }

    private static String AttributeToString(int AttributeValue, String name) {
        // TODO: possibly scale to fit!
        String TextValue = "";
        for (int i = 0; i < AttributeValue; i++)
            TextValue += '█';
        TextValue += name + ": " + AttributeValue;
        return TextValue;
    }

    public String name, SpeciesName;
    public final String[] statNames = { "Attack", "Defense", "Special Attack", "Special Defense", "Speed",
            "Hit Points" };
    public final String[] colors = { "Red", "DarkGrey", "DarkMagenta", "Blue", "Orange", "ForestGreen" };
    public statLine[] statValues;

    // TODO: when ability is implemented, add ability details!
    public CritterDetailsViewModel(Critter C) {
        statValues = new statLine[6];
        int[] statIntegers = { C.species.getBaseAttack(), C.species.getBaseDefense(), C.species.getBaseSpecial(),
                C.species.getBaseSDefence(),
                C.species.getBaseSpeed(), C.species.getBaseHitPoints() }; // TODO: REPLACE WITH ACTUAL STATS!!!!
        for (int i = 0; i < 6; i++)
            statValues[i] = new statLine(statNames[i], AttributeToString(statIntegers[i], statNames[i]), colors[i]);
        name = C.name;
        SpeciesName = C.species.name;
    }
}
