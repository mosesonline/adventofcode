package de.mosesonline.adventofcode.puzzle05.almanac;

import java.util.*;

public final class Almanac {
    private final Seeds seeds;
    private final MappingTable mappingTable;

    Almanac(
            Seeds seeds,
            Map<String, MappingTable> mappings) {
        this.seeds = seeds;
        this.mappingTable = findMapper(mappings, "seed", "location");
    }

    public Long minLocationToSeeds() {
        return seeds.seedNumbers().stream()
                .map(mappingTable::map)
                .mapToLong(s -> s.valueFor("location"))
                .min().orElseThrow(IllegalStateException::new);
    }

    static MappingTable findMapper(Map<String, MappingTable> mappings, String from, String to) {
        MappingTable table = mappings.get(from);
        if (table.to().equals(to)) {
            return table;
        } else {
            MappingTable mapper = findMapper(mappings, table.to(), to);
            return new CombinedMappingTable(table, mapper);
        }
    }

    public Long minLocationToSeedsInRange() {
        Iterator<Seed> seeds1 = seeds.asRange();
        long min = Long.MAX_VALUE;
        int concurrent = 5_000_000;
        List<Long> currentValues = Collections.synchronizedList(new ArrayList<>(concurrent));
        List<Thread> threadList = new ArrayList<>(concurrent);
        int round = 0;
        while (seeds1.hasNext()) {
            threadList.add(Thread.startVirtualThread(() -> currentValues.add(mappingTable.map(seeds1.next()).valueFor("location"))));
            if (threadList.size() == concurrent) {
                threadList.forEach((t) -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
                threadList.clear();
                min = Math.min(min, currentValues.stream().mapToLong(Long::longValue).min().orElseThrow());
                currentValues.clear();
                round++;
                System.out.println("Finished: " + round);
            }
        }

        threadList.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        threadList.clear();
        if (!currentValues.isEmpty()) {
            min = Math.min(min, currentValues.stream().mapToLong(Long::longValue).min().orElseThrow());
        }
        return min;
    }

    public record CombinedMappingTable(MappingTable fromTable, MappingTable toTable) implements MappingTable {

        @Override
        public String from() {
            return fromTable.from();
        }

        public String to() {
            return toTable.to();
        }

        @Override
        public Seed map(Seed seed) {
            return toTable.map(fromTable.map(seed));
        }
    }
}
