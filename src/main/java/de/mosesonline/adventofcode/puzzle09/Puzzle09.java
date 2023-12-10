package de.mosesonline.adventofcode.puzzle09;

import java.io.File;
import java.io.IOException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle09 {
    Puzzle09() {

    }

    public static void runPart1() {
        try {
            final var puzzle = new Puzzle09();
            int result = puzzle.sum(getInstance().loadFromResource("20231209_realdata.txt"));
            System.out.println("20231209_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runPart2() {

    }

    int sum(File file) throws IOException {
        final var historyData = new SensorDataParser().parse(file);

        return historyData.sumNextExtrapolatedValues();
    }
    int sumPrevious(File file) throws IOException {
        final var historyData = new SensorDataParser().parse(file);

        return historyData.sumPreviousExtrapolatedValues();
    }
}
