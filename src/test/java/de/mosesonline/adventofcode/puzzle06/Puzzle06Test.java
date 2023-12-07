package de.mosesonline.adventofcode.puzzle06;

import de.mosesonline.adventofcode.puzzle05.Puzzle05;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.*;

class Puzzle06Test {
    @Test
    void should_count_possibilities_part1() throws URISyntaxException, IOException {
        Integer result = new Puzzle06()
                .numberOfWaysToBeatRecord(getInstance()
                        .loadFromResource("20231206_test_part1.txt"));
        assertEquals(288, result);
    }
    @Test
    void should_count_possibilities_part2() throws URISyntaxException, IOException {
        Integer result = new Puzzle06()
                .numberOfWaysToBeatRecordOneRace(getInstance()
                        .loadFromResource("20231206_test_part1.txt"));
        assertEquals(71503, result);
    }
}
