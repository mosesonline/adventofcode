package de.mosesonline.adventofcode.puzzle10;

import java.io.File;
import java.io.IOException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle10 {
    Puzzle10() {
    }

    public static void runPart1() {
        try {
            final var puzzle = new Puzzle10();
            int result = puzzle.maxStepsFromStart(getInstance().loadFromResource("20231210_realdata.txt"));
            System.out.println("20231210_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runPart2() {
        try {
            final var puzzle = new Puzzle10();
            int result = puzzle.countEnclosed(getInstance().loadFromResource("20231210_realdata.txt"));
            System.out.println("20231210_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    int countEnclosed(File file)  throws IOException{
        PipePlan plan = new PlanParser().parse(file);
        return plan.countEnclosed();
    }

    int maxStepsFromStart(File file) throws IOException {
        PipePlan plan = new PlanParser().parse(file);
        return plan.stepsToFarthestPoint();
    }
}
