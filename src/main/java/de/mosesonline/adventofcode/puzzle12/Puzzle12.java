package de.mosesonline.adventofcode.puzzle12;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle12 {
    Puzzle12() {
    }

    public static void runPart1() {
        try {
            final var puzzle = new Puzzle12();
            BigInteger result = puzzle.sumOfPossibleArrangements(getInstance().loadFromResource("20231212_realdata.txt"));
            System.out.println("20231212_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void runPart2() {
        try {
            final var puzzle = new Puzzle12();
            BigInteger result = puzzle.sumOfPossibleFoldedArrangements(getInstance().loadFromResource("20231212_realdata.txt"));
            System.out.println("20231212_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    BigInteger sumOfPossibleArrangements(File file) throws IOException {
        ConditionRecord conditionRecord = new ConditionRecordParser().parse(file);
        return conditionRecord.sumOfPossibleArrangements();
    }

    BigInteger sumOfPossibleFoldedArrangements(File file) throws IOException {
        ConditionRecord conditionRecord = new ConditionRecordParser().parseFolded(file);
        return conditionRecord.sumOfPossibleArrangements();
    }
}
