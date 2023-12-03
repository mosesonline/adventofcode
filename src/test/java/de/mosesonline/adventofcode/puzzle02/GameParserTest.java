package de.mosesonline.adventofcode.puzzle02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameParserTest {
    @Test
    void shouldReadTestFile() throws URISyntaxException, IOException {
    final var parser = new GameParser();
        List<Game> games = parser.parseGameFile("20231202_test_part1.txt");
        assertEquals(5, games.size());
    }
}
