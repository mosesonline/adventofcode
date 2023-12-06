package de.mosesonline.adventofcode.puzzle04;

import java.io.File;
import java.io.IOException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle04 {
    Puzzle04() {

    }

    public static void runPart1() {
        try {
            Puzzle04 puzzle = new Puzzle04();
            int result = puzzle.calculateWorthPart1(getInstance().loadFromResource("20231204_realdata.txt"));
            System.out.println("20231204_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runPart2() {
        try {
            Puzzle04 puzzle = new Puzzle04();
            int result = puzzle.calculateScratchcards(getInstance().loadFromResource("20231204_realdata.txt"));
            System.out.println("20231204_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    int calculateWorthPart1(File input) throws IOException {
        return getInstance().parseLineByLineToStream(input)
                .map(Card::from)
                .mapToInt(c -> (int) Math.pow(2, c.countMatches() - 1))
                .sum();
    }

    int calculateScratchcards(File input) throws IOException {
        return new Scratchcards()
                .play(getInstance().parseLineByLine(input).stream().map(Card::from).toList());
    }

}
