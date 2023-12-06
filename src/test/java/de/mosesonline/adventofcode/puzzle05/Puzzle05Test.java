package de.mosesonline.adventofcode.puzzle05;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle05Test {
    @Test
    void should_run_part1_with_testdata() throws URISyntaxException, IOException {
        Long result = new Puzzle05()
                .lowestLocationNumber(getInstance()
                        .loadFromResource("20231205_test_part1.txt"));
        assertEquals(35L, result);
    }

    @Test
    void should_run_part2_with_testdata() throws URISyntaxException, IOException {
        Long result = new Puzzle05()
                .lowestLocationNumberInRange(getInstance()
                        .loadFromResource("20231205_test_part1.txt"));
        assertEquals(46L, result);
    }
}
