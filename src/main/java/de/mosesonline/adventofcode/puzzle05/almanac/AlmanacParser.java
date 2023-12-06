package de.mosesonline.adventofcode.puzzle05.almanac;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class AlmanacParser {
    private ParseStatus status = ParseStatus.SEEDS;

    public Almanac from(File file) throws IOException {
        List<Seed> seeds = new ArrayList<>();
        List<AlmanacMapEntry> f2w = new ArrayList<>();
        List<AlmanacMapEntry> h2l = new ArrayList<>();
        List<AlmanacMapEntry> l2t = new ArrayList<>();
        List<AlmanacMapEntry> t2h = new ArrayList<>();
        List<AlmanacMapEntry> s2s = new ArrayList<>();
        List<AlmanacMapEntry> s2f = new ArrayList<>();
        List<AlmanacMapEntry> w2l = new ArrayList<>();

        getInstance().parseLineByLine(file, (currentLine) -> {
            if (ParseStatus.SEEDS == status && seeds.isEmpty()) {
                seeds.addAll(parseSeeds(currentLine));
            } else if (ParseStatus.SEED_TO_SOIL == status && isMapping(currentLine)) {
                s2s.add(AlmanacMapEntry.from(currentLine));
            } else if (ParseStatus.SOIL_TO_FERTILIZER == status && isMapping(currentLine)) {
                s2f.add(AlmanacMapEntry.from(currentLine));
            } else if (ParseStatus.FERTILIZER_TO_WATER == status && isMapping(currentLine)) {
                f2w.add(AlmanacMapEntry.from(currentLine));
            } else if (ParseStatus.WATER_TO_LIGHT == status && isMapping(currentLine)) {
                w2l.add(AlmanacMapEntry.from(currentLine));
            } else if (ParseStatus.LIGHT_TO_TEMPERATURE == status && isMapping(currentLine)) {
                l2t.add(AlmanacMapEntry.from(currentLine));
            } else if (ParseStatus.TEMPERATURE_TO_HUMIDITY == status && isMapping(currentLine)) {
                t2h.add(AlmanacMapEntry.from(currentLine));
            } else if (ParseStatus.HUMIDITY_TO_LOCATION == status && isMapping(currentLine)) {
                h2l.add(AlmanacMapEntry.from(currentLine));
            } else {
                checkStatusSwitch(currentLine);
            }
        });
        return new Almanac(new Seeds(seeds),
                Map.of("seed", new DirectMappingTable("seed", "soil", s2s),
                        "soil", new DirectMappingTable("soil", "fertilizer", s2f),
                        "fertilizer", new DirectMappingTable("fertilizer", "water", f2w),
                        "water", new DirectMappingTable("water", "light", w2l),
                        "light", new DirectMappingTable("light", "temperature", l2t),
                        "temperature", new DirectMappingTable("temperature", "humidity", t2h),
                        "humidity", new DirectMappingTable("humidity", "location", h2l)));
    }

    private static boolean isMapping(String currentLine) {
        return !currentLine.isEmpty() && Character.isDigit(currentLine.charAt(0));
    }

    private void checkStatusSwitch(String currentLine) {
        switch (currentLine.trim()) {
            case "seed-to-soil map:" -> status = ParseStatus.SEED_TO_SOIL;
            case "soil-to-fertilizer map:" -> status = ParseStatus.SOIL_TO_FERTILIZER;
            case "fertilizer-to-water map:" -> status = ParseStatus.FERTILIZER_TO_WATER;
            case "water-to-light map:" -> status = ParseStatus.WATER_TO_LIGHT;
            case "light-to-temperature map:" -> status = ParseStatus.LIGHT_TO_TEMPERATURE;
            case "temperature-to-humidity map:" -> status = ParseStatus.TEMPERATURE_TO_HUMIDITY;
            case "humidity-to-location map:" -> status = ParseStatus.HUMIDITY_TO_LOCATION;

        }
    }

    List<Seed> parseSeeds(String currentLine) {
        return Arrays.stream(currentLine.split(":")[1]
                .trim()
                .split(" +")).map(Long::parseLong).map(Seed::new).toList();
    }

    enum ParseStatus {
        SEEDS,
        SEED_TO_SOIL,
        SOIL_TO_FERTILIZER,
        FERTILIZER_TO_WATER,
        WATER_TO_LIGHT,
        LIGHT_TO_TEMPERATURE,
        TEMPERATURE_TO_HUMIDITY,
        HUMIDITY_TO_LOCATION
    }
}
