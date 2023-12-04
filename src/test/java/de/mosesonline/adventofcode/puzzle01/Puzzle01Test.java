package de.mosesonline.adventofcode.puzzle01;

import de.mosesonline.adventofcode.common.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle01Test {
    private static final FileLoader FILE_LOADER = new FileLoader();

    @Test
    void testSumPart1() throws IOException, URISyntaxException {
        Puzzle01 puzzle01 = new Puzzle01(Puzzle01.Part.ONE);
        int resultingSum = puzzle01.sum(FILE_LOADER.loadFromResource("20231201_test_part1.txt"));

        assertEquals(142, resultingSum);
    }

    @Test
    void testSumPart2() throws IOException, URISyntaxException {
        Puzzle01 puzzle01 = new Puzzle01(Puzzle01.Part.TWO);
        int resultingSum = puzzle01.sum(FILE_LOADER.loadFromResource("20231201_test_part2.txt"));

        assertEquals(281, resultingSum);
    }
}
