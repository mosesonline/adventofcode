package de.mosesonline.adventofcode.puzzle05;

import de.mosesonline.adventofcode.puzzle05.almanac.Almanac;
import de.mosesonline.adventofcode.puzzle05.almanac.AlmanacParser;

import java.io.File;
import java.io.IOException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle05 {
    Puzzle05() {

    }

    public static void runPart1() {
        try {
            Puzzle05 puzzle = new Puzzle05();
            Long result = puzzle.lowestLocationNumber(getInstance().loadFromResource("20231205_realdata.txt"));
            System.out.println("20231205_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runPart2() {
        try {
            Puzzle05 puzzle = new Puzzle05();
            Long result = puzzle.lowestLocationNumberInRange(getInstance().loadFromResource("20231205_realdata.txt"));
            System.out.println("20231205_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    Long lowestLocationNumberInRange(File input) throws IOException {
        Almanac almanac = new AlmanacParser().from(input);
        return almanac.minLocationToSeedsInRange();
    }

    Long lowestLocationNumber(File input) throws IOException {
        Almanac almanac = new AlmanacParser().from(input);
        return almanac.minLocationToSeeds();
    }

}
