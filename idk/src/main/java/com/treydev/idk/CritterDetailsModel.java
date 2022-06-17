package com.treydev.idk;

public class CritterDetailsModel {
    public class statLine {
        public String attributeName;
        public String attributeValue;
        public String attributeColor;
        public statLine(String name, String value, String color)
        {
            this.attributeName = name;
            this.attributeValue = value;
            this.attributeColor = color;
        }
    }

    // public String  A, D, SA, SD, SPD, HP;
    // public String A = "";
    public String name, SpeciesName;
    public final String[] statNames = {"Attack", "Defense", "Special Attack", "Special Defense", "Speed", "Hit Points"};
    public final String[] colors={"Red", "DarkGrey", "DarkMagenta", "Blue", "Orange", "ForestGreen"};
    public statLine []statValues;
    //TODO: when ability is implemented, add ability details!
    public CritterDetailsModel(Critter C)
    {
        String  A, D, SA, SD, SPD, HP;
        A = D = SA = SD = SPD = HP = "";
        int Atk = C.species.getBaseAttack();//TODO: WHEN THIS MERGES, IMPLEMENT ACTUAL STAT AQUIRATION.
        for(int i = 0; i < Atk; i++)
            A += '█';
        A += " Attack: " + Atk;
        int Def = C.species.getBaseDefense();
        for(int i = 0; i < Def; i++)
            D += '█';
        D += " Defense: " + Def;
        int SpA = C.species.getBaseSpecial();
        for(int i = 0; i < SpA; i++)
            SA += '█';
        SA += " Special Attack: " + SpA;
        int SpD = C.species.getBaseSDefence();
        for(int i = 0; i < SpD; i++)
            SD += '█';
        SD += " Special Defense: " + SpD;
        int Spe = C.species.getBaseSpeed();
        for(int i = 0; i < Spe; i++)
            SPD += '█';
        SPD += " Speed: " + Spe;
        int HiP = C.species.getBaseHitPoints();
        for(int i = 0; i < HiP; i++)
            HP += '█';
        HP += " HP: " + HiP; //TODO: normalize HP so it doesn't take up more than every other stat.
        //TODO: MAYBE replace i++ with i+= 2, scale, then add a half box if i%2==1.
        //      OR simply decrease the font size, maybe fit 6 on a screen!
        String[] s =  {A,D,SA,SD,SPD,HP};
        statValues = new statLine[6];
        for(int i = 0; i < s.length; i++)
            statValues[i] = new statLine(statNames[i], s[i], colors[i]);
        name = C.name;
        SpeciesName = C.species.name;
    }
}
