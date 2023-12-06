package de.mosesonline.adventofcode.puzzle04;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle04Test {

    @Test
    void should_collect_worth() throws URISyntaxException, IOException {
        int result = new Puzzle04()
                .calculateWorthPart1(getInstance()
                        .loadFromResource("20231204_test_part1.txt"));
        assertEquals(13, result);
    }

    @Test
    void should_collect_worth_part2() throws URISyntaxException, IOException {
        int result = new Puzzle04()
                .calculateScratchcards(getInstance()
                        .loadFromResource("20231204_test_part1.txt"));
        assertEquals(30, result);
    }
}
