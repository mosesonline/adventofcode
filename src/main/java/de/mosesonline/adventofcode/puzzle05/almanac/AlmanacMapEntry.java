package de.mosesonline.adventofcode.puzzle05.almanac;

import java.util.regex.Pattern;

import static java.lang.Long.parseLong;

record AlmanacMapEntry(Long source, Long destination, Long range) {
    private static final Pattern captureValues = Pattern.compile("(?<destination>\\d+) +(?<source>\\d+) +(?<range>\\d+)");

    public static AlmanacMapEntry from(String currentLine) {
        final var matcher = captureValues.matcher(currentLine);
        if (matcher.find()) {
            return new AlmanacMapEntry(parseLong(matcher.group("source")),
                    parseLong(matcher.group("destination")),
                    parseLong(matcher.group("range")));
        }
        return null;
    }
}
