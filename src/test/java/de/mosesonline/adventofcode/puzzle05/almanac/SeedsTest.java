package de.mosesonline.adventofcode.puzzle05.almanac;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeedsTest {
    @Test
    void should_calculate_range() {
        Seeds seeds = new Seeds(List.of(
                new Seed(79),
                new Seed(14),
                new Seed(55),
                new Seed(13)
        ));
        int count = 0;
        Iterator<Seed> flattenedRanges = seeds.asRange();
        while (flattenedRanges.hasNext()) {
            flattenedRanges.next();
            count++;
        }
        assertEquals(27, count);
    }

}
