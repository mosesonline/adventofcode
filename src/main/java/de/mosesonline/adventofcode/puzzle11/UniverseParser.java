package de.mosesonline.adventofcode.puzzle11;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static de.mosesonline.adventofcode.puzzle11.EmptySpace.EMPTY_SPACE;

public class UniverseParser {

    public Universe parse(File file) throws IOException {
        List<String> universeData = getInstance().parseLineByLine(file);
        return new Universe(universeData.stream().map(s -> s.chars().mapToObj(c -> switch (c) {
            case '.' -> EMPTY_SPACE;
            case '#' -> new Galaxy();
            default -> throw new IllegalStateException("Unknown map symbol: " + (char) c);
        }).toArray(UniverseSpace[]::new)).toArray(UniverseSpace[][]::new));
    }
}
