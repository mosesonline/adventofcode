package de.mosesonline.adventofcode.puzzle09;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle09Test {
    @Test
    void should_sum() throws URISyntaxException, IOException {
        int result = new Puzzle09()
                .sum(getInstance()
                        .loadFromResource("20231209_test_part1.txt"));
        assertEquals(114, result);
    }

    @Test
    void should_sum_real_data() throws URISyntaxException, IOException {
        int result = new Puzzle09()
                .sum(getInstance()
                        .loadFromResource("20231209_realdata.txt"));
        assertEquals(1702218515, result);
    }

    @Test
    void should_sum_backwards() throws URISyntaxException, IOException {
        int result = new Puzzle09()
                .sumPrevious(getInstance()
                        .loadFromResource("20231209_test_part1.txt"));
        assertEquals(2, result);
    }

    @Test
    void should_sum_backwards_real_data() throws URISyntaxException, IOException {
        int result = new Puzzle09()
                .sumPrevious(getInstance()
                        .loadFromResource("20231209_realdata.txt"));
        assertEquals(925, result);
    }
}
