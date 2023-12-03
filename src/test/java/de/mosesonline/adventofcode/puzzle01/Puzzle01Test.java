package de.mosesonline.adventofcode.puzzle01;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle01Test {
    @Test
    void testSumPart1() throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("20231201_test_part1.txt");
        Puzzle01 puzzle01 = new Puzzle01(Puzzle01.Part.ONE);
        assertEquals(142, puzzle01.sum(new File(resource.toURI())));
    }
    @Test
    void testSumPart2() throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("20231201_test_part2.txt");
        Puzzle01 puzzle01 = new Puzzle01(Puzzle01.Part.TWO);
        assertEquals(281, puzzle01.sum(new File(resource.toURI())));
    }
}
