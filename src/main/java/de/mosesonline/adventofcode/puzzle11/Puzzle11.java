package de.mosesonline.adventofcode.puzzle11;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle11 {
    Puzzle11(){

    }

    public static void runPart1() {
        try {
            final var puzzle = new Puzzle11();
            BigInteger result = puzzle.sumOfShortestPaths(getInstance().loadFromResource("20231211_realdata.txt"));
            System.out.println("20231211_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runPart2() {
        try {
            final var puzzle = new Puzzle11();
            BigInteger result = puzzle.sumOfShortestPaths(getInstance().loadFromResource("20231211_realdata.txt"), 1_000_000);
            System.out.println("20231211_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    BigInteger sumOfShortestPaths(File file) throws IOException {
        final var universe = new UniverseParser().parse(file);
        Universe expandedUniverse = universe.expandAndName();

        return expandedUniverse.sumOfShortestPathsBetweenGalaxies();
    }

    BigInteger sumOfShortestPaths(File file, int expandFactor) throws IOException {
        final var universe = new UniverseParser().parse(file);
        Universe expandedUniverse = universe.expandAndName(expandFactor);

        return expandedUniverse.sumOfShortestPathsBetweenGalaxies();
    }
}
