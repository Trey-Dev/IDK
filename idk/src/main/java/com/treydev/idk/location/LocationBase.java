package com.treydev.idk.location;

import com.treydev.idk.support.NameGenerator;

public abstract class LocationBase {
    protected String name;

    public String getName() {
        return name;
    }

    protected int level;

    public int getLevel() {
        return level;
    }

    protected String locationLeader;

    public abstract int getId();

    protected LocationBase(int level) {
        this.locationLeader = NameGenerator.generateRandomName();
        // For now, we set them as equal, not +/- 10% requested level
        this.level = level;
        // We allow locations to be +/- 10% of the requested level
        // this.level = (int)(level * (10.0 + generator.nextDouble() -
        // generator.nextDouble()) / 10.0);
    }
}
