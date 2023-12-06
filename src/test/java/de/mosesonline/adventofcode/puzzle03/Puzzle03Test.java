package de.mosesonline.adventofcode.puzzle03;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle03Test {

    @Test
    void should_calculate_sum_part1() throws URISyntaxException, IOException {
        int result = new Puzzle03()
                .sumPart1(getInstance()
                        .loadFromResource("20231203_test_part1.txt"));
        assertEquals(4361, result);
    }
    @Test
    void should_calculate_sum_part2() throws URISyntaxException, IOException {
        int result = new Puzzle03()
                .sumPart2(getInstance()
                        .loadFromResource("20231203_test_part1.txt"));
        assertEquals(467835, result);
    }
}
