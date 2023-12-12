package de.mosesonline.adventofcode.puzzle11;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static de.mosesonline.adventofcode.puzzle11.EmptySpace.EMPTY_SPACE;
import static de.mosesonline.adventofcode.puzzle11.UniverseParserTest.TEST_BASE_UNIVERSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UniverseTest {

    private static final Universe EXPECTED_EXPANDED_UNIVERSE = new Universe(new UniverseSpace[][]{
            {EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), new LocalizedGalaxy(new LocationPoint(3,0),"1"), EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, new LocalizedGalaxy(new LocationPoint(7,1),"2"), new FoldedSpace(2,1), EMPTY_SPACE},
            {new LocalizedGalaxy(new LocationPoint(0,2),"3"), EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE},
            {new FoldedSpace(1,2), new FoldedSpace(1,2), new FoldedSpace(2,2), new FoldedSpace(1,2), new FoldedSpace(1,2), new FoldedSpace(2,2), new FoldedSpace(1,2), new FoldedSpace(1,2), new FoldedSpace(2,2), new FoldedSpace(1,2)},
            {EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), new LocalizedGalaxy(new LocationPoint(6,4),"4"), EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE},
            {EMPTY_SPACE,new LocalizedGalaxy(new LocationPoint(1,5),"5"), new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE},
            {EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), new LocalizedGalaxy(new LocationPoint(9,6),"6")},
            {new FoldedSpace(1,2), new FoldedSpace(1,2), new FoldedSpace(2,2), new FoldedSpace(1,2), new FoldedSpace(1,2), new FoldedSpace(2,2), new FoldedSpace(1,2), new FoldedSpace(1,2), new FoldedSpace(2,2), new FoldedSpace(1,2)},
            {EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, new LocalizedGalaxy(new LocationPoint(7,8),"7"), new FoldedSpace(2,1), EMPTY_SPACE},
            {new LocalizedGalaxy(new LocationPoint(0,9),"8"), EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE, new LocalizedGalaxy(new LocationPoint(4,9),"9"), new FoldedSpace(2,1), EMPTY_SPACE, EMPTY_SPACE, new FoldedSpace(2,1), EMPTY_SPACE}
    });

    @Test
    void shouldExpand() {
        Universe universe = new Universe(new UniverseSpace[][]{{EMPTY_SPACE, EMPTY_SPACE}});
        Universe expandedUniverse = universe.expandAndName();
        assertEquals(
                new Universe(new UniverseSpace[][]
                        {
                                {
                                        new FoldedSpace(2,2), new FoldedSpace(2,2)
                                }
                        }),
                expandedUniverse);
    }

    @Test
    void shouldExpandBase() {
        Universe expandedUniverse = TEST_BASE_UNIVERSE.expandAndName();

        assertEquals(
                EXPECTED_EXPANDED_UNIVERSE,
                expandedUniverse);
    }

    @Test
    void shouldNotExpand() {
        Universe universe = new Universe(new UniverseSpace[][]{{new Galaxy(), new Galaxy()}});
        Universe expandedUniverse = universe.expandAndName();
        assertEquals(new Universe(new UniverseSpace[][]{{new LocalizedGalaxy(new LocationPoint(0, 0), "1"), new LocalizedGalaxy(new LocationPoint(1, 0), "2")}}), expandedUniverse);
    }

    @Test
    void shouldSumDistances() {
        assertEquals(BigInteger.valueOf(374), EXPECTED_EXPANDED_UNIVERSE.sumOfShortestPathsBetweenGalaxies());
    }

    @Nested
    public class Distance {
        @Test
        void shouldCalculateDistance1() {
            final var g1 = new LocalizedGalaxy(new LocationPoint(3, 0), "1");
            final var g2 = new LocalizedGalaxy(new LocationPoint(7, 8), "7");
            assertEquals(BigInteger.valueOf(15), EXPECTED_EXPANDED_UNIVERSE.calculateDistance(g1, g2));
        }

        @Test
        void shouldCalculateDistance2() {
            final var g1 = new LocalizedGalaxy(new LocationPoint(0, 2), "3");
            final var g2 = new LocalizedGalaxy(new LocationPoint(12, 7), "6");
            assertEquals(BigInteger.valueOf(17), EXPECTED_EXPANDED_UNIVERSE.calculateDistance(g1, g2));
        }

        @Test
        void shouldCalculateDistance3() {
            final var g1 = new LocalizedGalaxy(new LocationPoint(0, 11), "8");
            final var g2 = new LocalizedGalaxy(new LocationPoint(5, 11), "9");

            assertEquals(BigInteger.valueOf(5), EXPECTED_EXPANDED_UNIVERSE.calculateDistance(g1, g2));
        }

        @Test
        void shouldCalculateDistance4() {
            final var g1 = new LocalizedGalaxy(new LocationPoint(1, 6), "5");
            final var g2 = new LocalizedGalaxy(new LocationPoint(5, 11), "9");

            assertEquals(BigInteger.valueOf(9), EXPECTED_EXPANDED_UNIVERSE.calculateDistance(g1, g2));
        }

        @Test
        void shouldCalculateDistance5() {
            final var g1 = new LocalizedGalaxy(new LocationPoint(3, 0), "1");
            final var g2 = new LocalizedGalaxy(new LocationPoint(7, 1), "2");

            assertEquals(BigInteger.valueOf(6), EXPECTED_EXPANDED_UNIVERSE.calculateDistance(g1, g2));
        }
    }
}
