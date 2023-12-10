package de.mosesonline.adventofcode.puzzle08;

import de.mosesonline.adventofcode.common.FileLoader;

import java.io.File;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class GameTableParser {
    private static final Pattern pattern = Pattern.compile("(?<key>[0-9A-Z]{3}) = \\((?<left>[0-9A-Z]{3}), (?<right>[0-9A-Z]{3})\\)");

    public GameTable parse(File file) throws IOException {
        List<String> data = FileLoader.getInstance().parseLineByLine(file);
        String instructions = data.getFirst();
        Map<String, Pair> routingData = data.stream().skip(2).filter(s -> !s.isBlank()).map(s -> {
            final var matcher = pattern.matcher(s);
            if (!matcher.find()) {
                throw new IllegalStateException(s);
            }
            return new AbstractMap.SimpleEntry<>(
                    matcher.group("key"),
                    new Pair(matcher.group("left"), matcher.group("right")));
        }).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new GameTable(instructions, routingData);
    }
}
