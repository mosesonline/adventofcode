package de.mosesonline.adventofcode;

import de.mosesonline.adventofcode.puzzle01.Puzzle01;
import de.mosesonline.adventofcode.puzzle02.Puzzle02;
import de.mosesonline.adventofcode.puzzle03.Puzzle03;

public class AdventOfCodeApp {

    public static void main(String[] args) {
        try {
            Puzzle01.runPart1();
            Puzzle01.runPart2();
            Puzzle02.runPart1();
            Puzzle02.runPart2();
            Puzzle03.runPart1();
            Puzzle03.runPart2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
