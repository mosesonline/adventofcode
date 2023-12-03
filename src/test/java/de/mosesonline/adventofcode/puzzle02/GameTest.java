package de.mosesonline.adventofcode.puzzle02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void should_calculate_min_set(){
        final var target = new Game(1, new Set[]{
                new Set(3,0,4),
                new Set(6,2,1),
                new Set(0,2,0)
        });
        Set result = target.minSet();
        assertEquals(4, result.red());
        assertEquals(2, result.green());
        assertEquals(6, result.blue());
    }
}
