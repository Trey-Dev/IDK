package com.treydev.idk;

public class CritterDetailsModel {
    public String  D, SA, SD, SPD, HP;
    public String A = "";
    public String name, SpeciesName;
    //TODO: when ability is implemented, add ability details!
    public CritterDetailsModel(Critter C)
    {
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
        name = C.name;
        SpeciesName = C.species.name;
    }
}
