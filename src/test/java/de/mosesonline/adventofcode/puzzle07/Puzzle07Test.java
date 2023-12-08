package de.mosesonline.adventofcode.puzzle07;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Puzzle07Test {
    @Test
    void should_calculate_total_winnings_part1() throws URISyntaxException, IOException {
        Integer result = new Puzzle07()
                .totalWinnings(getInstance()
                        .loadFromResource("20231207_test_part1.txt"));
        assertEquals(6440, result);
    }

    @Test
    void should_calculate_total_winnings_part2() throws URISyntaxException, IOException {
        Integer result = new Puzzle07()
                .totalWinningsWithJoker(getInstance()
                        .loadFromResource("20231207_test_part1.txt"));
        assertEquals(5905, result);
    }
}
