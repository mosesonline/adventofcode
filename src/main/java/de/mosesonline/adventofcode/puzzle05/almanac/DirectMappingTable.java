package de.mosesonline.adventofcode.puzzle05.almanac;

import java.util.Comparator;
import java.util.List;

record DirectMappingTable(String from, String to, List<AlmanacMapEntry> mappings) implements MappingTable {
    DirectMappingTable {
        mappings.sort(Comparator.comparing(AlmanacMapEntry::source));
    }

    @Override
    public Seed map(Seed input) {
        final var sourceValue = input.valueFor(from());
        Long valueTo = mappings.stream()
                .filter(ame -> ame.source().compareTo(sourceValue) <= 0 &&
                        (ame.source() + ame.range() - 1 - sourceValue) >= 0)
                .findFirst().map(ame -> ((ame.destination() - ame.source()) + sourceValue)).orElse(sourceValue);
        input.setValue(to(), valueTo);
        return input;
    }

    @Override
    public String toString() {
        return from + " -> " + to + mappings;
    }
}
