package de.mosesonline.adventofcode.puzzle08;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle08Test {

    @Test
    void should_calculate_steps_part1_1() throws URISyntaxException, IOException {
        Integer result = new Puzzle08()
                .stepsToReachZZZ(getInstance()
                        .loadFromResource("20231208_test_part1_1.txt"));
        assertEquals(2, result);
    }

    @Test
    void should_calculate_steps_part1_2() throws URISyntaxException, IOException {
        Integer result = new Puzzle08()
                .stepsToReachZZZ(getInstance()
                        .loadFromResource("20231208_test_part1_2.txt"));
        assertEquals(6, result);
    }

    @Test
    void should_calculate_steps_simultaneous_part2() throws URISyntaxException, IOException {
        long result = new Puzzle08()
                .stepsToReachZ(getInstance()
                        .loadFromResource("20231208_test_part2.txt")).longValue();
        assertEquals(6L, result);
    }

}
