package de.mosesonline.adventofcode.puzzle07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static de.mosesonline.adventofcode.puzzle07.Card.*;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void should_calculateAlternatives() {
        List<Card[]> alternatives = Card.alternatives(new Card[]{CARD_2, CARD_3, Card.CARD_4, Card.CARD_J, Card.CARD_J});
        assertEquals(6, alternatives.size());
        assertTrue(alternatives.stream().anyMatch(p -> Arrays.equals(p, new Card[]{CARD_2, CARD_3, Card.CARD_4, CARD_2, CARD_2})));
        assertTrue(alternatives.stream().anyMatch(p -> Arrays.equals(p, new Card[]{CARD_2, CARD_3, Card.CARD_4, CARD_3, CARD_3})));
        assertTrue(alternatives.stream().anyMatch(p -> Arrays.equals(p, new Card[]{CARD_2, CARD_3, Card.CARD_4, Card.CARD_4, Card.CARD_4})));
        assertTrue(alternatives.stream().anyMatch(p -> Arrays.equals(p, new Card[]{CARD_2, CARD_3, Card.CARD_4, CARD_3, CARD_2})));
        assertTrue(alternatives.stream().anyMatch(p -> Arrays.equals(p, new Card[]{CARD_2, CARD_3, Card.CARD_4, CARD_4, Card.CARD_2})));
        assertTrue(alternatives.stream().anyMatch(p -> Arrays.equals(p, new Card[]{CARD_2, CARD_3, Card.CARD_4, CARD_4, Card.CARD_3})));
    }

    @Test
    void should_perm_1() {
        final var permutation = Card.permutations(new Card[]{CARD_A}, 5);
        assertEquals(1, permutation.size());
        assertArrayEquals(new Card[]{CARD_A, CARD_A, CARD_A, CARD_A, CARD_A}, permutation.get(0));
    }

    @Test
    void should_perm_2() {
        final var permutation = Card.permutations(new Card[]{CARD_A, CARD_3}, 3);
        assertEquals(4, permutation.size());
        assertArrayEquals(new Card[]{CARD_A, CARD_A, CARD_A}, permutation.get(0));
        assertArrayEquals(new Card[]{CARD_A, CARD_3, CARD_A}, permutation.get(1));
        assertArrayEquals(new Card[]{CARD_A, CARD_3, CARD_3}, permutation.get(2));
        assertArrayEquals(new Card[]{CARD_3, CARD_3, CARD_3}, permutation.get(3));
    }

    @Test
    void should_perm_3() {
        final var permutation = Card.permutations(new Card[]{CARD_A, CARD_3, CARD_2}, 2);
        assertEquals(6, permutation.size());
        assertArrayEquals(new Card[]{CARD_A, CARD_A}, permutation.get(0));
        assertArrayEquals(new Card[]{CARD_A, CARD_3}, permutation.get(1));
        assertArrayEquals(new Card[]{CARD_A, CARD_2}, permutation.get(2));
        assertArrayEquals(new Card[]{CARD_3, CARD_3}, permutation.get(3));
        assertArrayEquals(new Card[]{CARD_3, CARD_2}, permutation.get(4));
        assertArrayEquals(new Card[]{CARD_2, CARD_2}, permutation.get(5));
    }
}
