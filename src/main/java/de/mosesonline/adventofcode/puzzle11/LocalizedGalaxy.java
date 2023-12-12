package de.mosesonline.adventofcode.puzzle11;

import java.awt.*;
import java.util.Objects;

public final class LocalizedGalaxy  extends UniverseSpace {
    private final String name;
    private final LocationPoint location;

    public LocalizedGalaxy(LocationPoint location, String name) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public LocationPoint getLocation(){
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizedGalaxy that = (LocalizedGalaxy) o;
        return Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}
