package de.mosesonline.adventofcode.puzzle08;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle08 {
    Puzzle08() {

    }

    public static void runPart1() {
        try {
            Puzzle08 puzzle = new Puzzle08();
            int result = puzzle.stepsToReachZZZ(getInstance().loadFromResource("20231208_realdata.txt"));
            System.out.println("20231208_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void runPart2() {
        try {
            Puzzle08 puzzle = new Puzzle08();
            BigInteger result = puzzle.stepsToReachZ(getInstance().loadFromResource("20231208_realdata.txt"));
            System.out.println("20231208_2: " + result.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    int stepsToReachZZZ(File file) throws IOException {
        GameTable gt = new GameTableParser().parse(file);

        return gt.stepsToReach("AAA", "ZZZ");
    }

    BigInteger stepsToReachZ(File file) throws IOException {
        GameTable gt = new GameTableParser().parse(file);
        return gt.stepsToReachFromMatcher("..A");
    }
}
