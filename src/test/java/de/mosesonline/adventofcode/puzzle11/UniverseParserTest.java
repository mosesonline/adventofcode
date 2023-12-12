package de.mosesonline.adventofcode.puzzle11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;
import static de.mosesonline.adventofcode.puzzle11.EmptySpace.EMPTY_SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UniverseParserTest {


    public static final Universe TEST_BASE_UNIVERSE = new Universe(new UniverseSpace[][]{
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, new Galaxy(), EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, new Galaxy(), EMPTY_SPACE, EMPTY_SPACE},
            {new Galaxy(), EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, new Galaxy(), EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, new Galaxy(), EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, new Galaxy()},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, new Galaxy(), EMPTY_SPACE, EMPTY_SPACE},
            {new Galaxy(), EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, new Galaxy(), EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE, EMPTY_SPACE}
    });

    @Test
    void shouldParseUniverse() throws URISyntaxException, IOException {
        final var universe = new UniverseParser().parse(getInstance()
                .loadFromResource("20231211_test_part1.txt"));
        assertNotNull(universe);
        assertEquals(TEST_BASE_UNIVERSE, universe);
    }
}
