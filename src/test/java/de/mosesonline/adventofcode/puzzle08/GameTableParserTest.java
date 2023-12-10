package de.mosesonline.adventofcode.puzzle08;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTableParserTest {
    @Test
    void should_parse_testdata() throws URISyntaxException, IOException {
        final var gt = new GameTableParser().parse(getInstance()
                .loadFromResource("20231208_test_part1_2.txt"));
        assertEquals(new GameTable(
                "LLR",
                Map.of(
                        "AAA", new Pair("BBB", "BBB"),
                        "BBB", new Pair("AAA", "ZZZ"),
                        "ZZZ", new Pair("ZZZ", "ZZZ")
                )
        ), gt);
    }
}
