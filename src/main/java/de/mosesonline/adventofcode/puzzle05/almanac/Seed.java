package de.mosesonline.adventofcode.puzzle05.almanac;

import java.util.HashMap;
import java.util.Map;

public final class Seed {
    private final long seed;
    private final Map<String, Long> values = new HashMap<>();

    public Seed(long seed) {
        this.seed = seed;
        values.put("seed", seed);
    }

    public long seed() {
        return seed;
    }

    public Long valueFor(String step) {
        return values.get(step);
    }

    public void setValue(String s, Long value) {
        values.put(s, value);
    }

    @Override
    public String toString() {
        return "Seed{" +
                "seed=" + seed +
                ", values=" + values +
                '}';
    }
}
