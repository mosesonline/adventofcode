package de.mosesonline.adventofcode.puzzle10;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle10Test {
    @Test
    void should_find_farthest_1() throws URISyntaxException, IOException {

        int result = new Puzzle10()
                .maxStepsFromStart(getInstance()
                        .loadFromResource("20231210_test_part1_1.txt"));
        assertEquals(4, result);
    }

    @Test
    void should_find_farthest_2() throws URISyntaxException, IOException {

        int result = new Puzzle10()
                .maxStepsFromStart(getInstance()
                        .loadFromResource("20231210_test_part1_2.txt"));
        assertEquals(8, result);
    }

    @Test
    void should_count_enclosed_1() throws URISyntaxException, IOException {

        int result = new Puzzle10()
                .countEnclosed(getInstance()
                        .loadFromResource("20231210_test_part2_1.txt"));
        assertEquals(8, result);
    }

    @Test
    void should_count_enclosed_2() throws URISyntaxException, IOException {

        int result = new Puzzle10()
                .countEnclosed(getInstance()
                        .loadFromResource("20231210_test_part2_2.txt"));
        assertEquals(8, result);
    }

    @Test
    void should_count_enclosed_3() throws URISyntaxException, IOException {

        int result = new Puzzle10()
                .countEnclosed(getInstance()
                        .loadFromResource("20231210_test_part2_3.txt"));
        assertEquals(8, result);
    }
}
