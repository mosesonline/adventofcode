package de.mosesonline.adventofcode.puzzle06;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class RaceParser {
    public List<Race> parse(File input) throws IOException {
        List<Race> result = new ArrayList<>();
        List<String> lines = getInstance().parseLineByLine(input);
        String[] times = lines.get(0).split(" +");
        String[] distances = lines.get(1).split(" +");
        for (int i = 1; i < times.length; i++) {
            result.add(new Race(parseInt(times[i]), parseInt(distances[i])));
        }
        return result;
    }
    public Race parseAsOne(File input) throws IOException {
        Race result = null;
        List<String> lines = getInstance().parseLineByLine(input);
        long time = parseLong(lines.get(0).split(" +",2)[1].replaceAll(" +", ""));
        long distance = parseLong(lines.get(1).split(" +",2)[1].replaceAll(" +", ""));

        return new Race(time, distance);
    }
}
