package de.mosesonline.adventofcode.puzzle11;

import java.util.Objects;

public final class Galaxy extends UniverseSpace {
    private final String name;

    public Galaxy() {
        this.name = "#";
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Galaxy galaxy = (Galaxy) o;
        return Objects.equals(name, galaxy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
