package de.mosesonline.adventofcode.puzzle12;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle12Test {
    @Test
    void shouldRunPart1TestData() throws URISyntaxException, IOException {
        BigInteger result = new Puzzle12()
                .sumOfPossibleArrangements(getInstance()
                        .loadFromResource("20231212_test_part1.txt"));
        assertEquals(BigInteger.valueOf(21), result);
    }
    @Test
    void shouldRunPart2TestData() throws URISyntaxException, IOException {
        BigInteger result = new Puzzle12()
                .sumOfPossibleFoldedArrangements(getInstance()
                        .loadFromResource("20231212_test_part1.txt"));
        assertEquals(BigInteger.valueOf(525152), result);
    }
}
