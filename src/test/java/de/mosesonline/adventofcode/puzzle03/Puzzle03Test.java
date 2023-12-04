package de.mosesonline.adventofcode.puzzle03;

import de.mosesonline.adventofcode.common.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle03Test {

    public static final FileLoader FILE_LOADER = new FileLoader();

    @Test
    void should_calculate_sum_part1() throws URISyntaxException, IOException {
        int result = new Puzzle03()
                .sumPart1(FILE_LOADER
                        .loadFromResource("20231203_test_part1.txt"));
        assertEquals(4361, result);
    }
    @Test
    void should_calculate_sum_part2() throws URISyntaxException, IOException {
        int result = new Puzzle03()
                .sumPart2(FILE_LOADER
                        .loadFromResource("20231203_test_part1.txt"));
        assertEquals(467835, result);
    }
}
