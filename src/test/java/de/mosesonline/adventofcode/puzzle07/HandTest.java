package de.mosesonline.adventofcode.puzzle07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    @ParameterizedTest
    @MethodSource("handDataWithDistribution")
    void should_calculateRank(Hand hand, Map<Card, Long> expectedRank) {
        assertEquals(expectedRank, Card.distribution(hand.getCards()));
    }

    @ParameterizedTest
    @MethodSource("handsToCompare")
    void should_compare(Hand hand1, Hand hand2, int result) {
        Hand.DefaultComparator defaultComparator = new Hand.DefaultComparator();
        assertEquals(result, defaultComparator.compare(hand1, hand2));
        assertEquals(-1 * result, defaultComparator.compare(hand2, hand1));
    }
    @Test
    void should_select_correct_max(){
        final var hand1 = new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_J);
        final var hand2 = new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_4, Card.CARD_J);
        Hand.Part2Comparator defaultComparator = new Hand.Part2Comparator();
        assertEquals(1, defaultComparator.compare(hand1, hand2));
        assertEquals(-1, defaultComparator.compare(hand2, hand1));
    }
    @Test
    void should_select_correct_max_2(){
        final var hand1 = new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_J);
        final var hand2 = new Hand(Card.CARD_3, Card.CARD_3, Card.CARD_3, Card.CARD_3, Card.CARD_J);
        Hand.Part2Comparator defaultComparator = new Hand.Part2Comparator();
        assertEquals(-1, defaultComparator.compare(hand1, hand2));
        assertEquals(1, defaultComparator.compare(hand2, hand1));
    }

    @Test
    void should_select_correct_max_J(){
        final var hand1 = new Hand(Card.CARD_J, Card.CARD_J, Card.CARD_J, Card.CARD_J, Card.CARD_J);
        final var hand2 = new Hand(Card.CARD_3, Card.CARD_3, Card.CARD_3, Card.CARD_3, Card.CARD_J);
        Hand.Part2Comparator defaultComparator = new Hand.Part2Comparator();
        assertEquals(-1, defaultComparator.compare(hand1, hand2));
        assertEquals(1, defaultComparator.compare(hand2, hand1));
    }

    @Test
    void should_select_correct_max_A(){
        final var hand1 = new Hand(Card.CARD_J, Card.CARD_J, Card.CARD_J, Card.CARD_J, Card.CARD_J);
        final var hand2 = new Hand(Card.CARD_A, Card.CARD_A, Card.CARD_A, Card.CARD_A, Card.CARD_A);
        Hand.Part2Comparator defaultComparator = new Hand.Part2Comparator();
        assertEquals(-1, defaultComparator.compare(hand1, hand2));
        assertEquals(1, defaultComparator.compare(hand2, hand1));
    }

    static Stream<Arguments> handsToCompare() {
        return Stream.of(
                Arguments.of(new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2),
                        new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_A),
                        1),
                Arguments.of(new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_A),
                        new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_K, Card.CARD_A),
                        1));
    }

    static Stream<Arguments> handDataWithDistribution() {
        return Stream.of(
                Arguments.of(new Hand(Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2, Card.CARD_2), Map.of(Card.CARD_2, 5L)),
                Arguments.of(new Hand(Card.CARD_A, Card.CARD_T, Card.CARD_3, Card.CARD_2, Card.CARD_2),
                        Map.of(
                                Card.CARD_2, 2L,
                                Card.CARD_A, 1L,
                                Card.CARD_T, 1L,
                                Card.CARD_3, 1L
                        )
                ),
                Arguments.of(new Hand(Card.CARD_A, Card.CARD_K, Card.CARD_3, Card.CARD_5, Card.CARD_2),
                        Map.of(
                                Card.CARD_2, 1L,
                                Card.CARD_5, 1L,
                                Card.CARD_A, 1L,
                                Card.CARD_K, 1L,
                                Card.CARD_3, 1L
                        )
                )
        );
    }
}
