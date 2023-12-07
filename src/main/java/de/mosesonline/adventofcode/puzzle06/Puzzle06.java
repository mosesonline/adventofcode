package de.mosesonline.adventofcode.puzzle06;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle06 {
    Puzzle06() {

    }

    public static void runPart1() {
        try {
            Puzzle06 puzzle = new Puzzle06();
            int result = puzzle.numberOfWaysToBeatRecord(getInstance().loadFromResource("20231206_realdata.txt"));
            System.out.println("20231206_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runPart2() {
        try {
            Puzzle06 puzzle = new Puzzle06();
            int result = puzzle.numberOfWaysToBeatRecordOneRace(getInstance().loadFromResource("20231206_realdata.txt"));
            System.out.println("20231206_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    int numberOfWaysToBeatRecord(File input) throws IOException {
        List<Race> races = new RaceParser().parse(input);
        return races.stream().map(Race::countPossibilities)
                .mapToInt(Integer::intValue)
                .reduce(1, (a, b) -> a * b);
    }

    int numberOfWaysToBeatRecordOneRace(File input) throws IOException {
        Race race = new RaceParser().parseAsOne(input);
        return race.countPossibilities();
    }
}
