package de.mosesonline.adventofcode.puzzle02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle02Test {
    @Test
    void should_count_test_data_part1() throws URISyntaxException, IOException {
        int result = new Puzzle02().playPartOne("20231202_test_part1.txt");
        assertEquals(8, result);
    }

    @Test
    void should_count_test_data_part2() throws URISyntaxException, IOException {
        int result = new Puzzle02().playPartTwo("20231202_test_part1.txt");
        assertEquals(2286, result);
    }
}
