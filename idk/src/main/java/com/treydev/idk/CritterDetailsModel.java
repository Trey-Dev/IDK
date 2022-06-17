package com.treydev.idk;

public class CritterDetailsModel {
    public int A, D, SA, SD, SPD, HP;
    public String name, SpeciesName;
    //TODO: when ability is implemented, add ability details!
    public CritterDetailsModel(Critter C)
    {
        A = C.species.getBaseAttack(); //TODO: WHEN THIS MERGES, IMPLEMENT ACTUAL STAT AQUIRATION.
        D = C.species.getBaseDefense();
        SA = C.species.getBaseSpecial();
        SD = C.species.getBaseSDefence();
        SPD = C.species.getBaseSpeed();
        HP = C.species.getBaseHitPoints();
        name = C.name;
        SpeciesName = C.species.name;
    }
}
