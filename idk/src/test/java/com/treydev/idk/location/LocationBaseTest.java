package com.treydev.idk.location;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class LocationBaseTest {
    private class TestLocation extends LocationBase {
        public TestLocation(int level) {
            super(level);
            this.name = "Test Location";
        }
    }

    @Test
    void testGetLevel() {
        TestLocation location = new TestLocation(10);
        Assert.isTrue(location.getLevel() == 10, "Location level is not 10");
    }

    @Test
    void testGetName() {
        TestLocation location = new TestLocation(10);
        Assert.isTrue(location.getName().length() > 0, "Location name is not set");
    }
}
