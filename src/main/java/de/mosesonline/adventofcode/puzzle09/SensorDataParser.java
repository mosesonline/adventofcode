package de.mosesonline.adventofcode.puzzle09;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class SensorDataParser {
    public HistoryData parse(File file) throws IOException {
        final var data = new ArrayList<Integer[]>();
        getInstance().parseLineByLine(file, (currentLine) -> {
            data.add(Arrays.stream(currentLine.split(" +")).map(Integer::parseInt).toArray(Integer[]::new));
        });
        return new HistoryData(data);
    }
}
