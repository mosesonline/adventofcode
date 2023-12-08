package de.mosesonline.adventofcode.puzzle07;

import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public enum Card {
    CARD_2('2'),
    CARD_3('3'),
    CARD_4('4'),
    CARD_5('5'),
    CARD_6('6'),
    CARD_7('7'),
    CARD_8('8'),
    CARD_9('9'),
    CARD_T('T'),
    CARD_J('J'),
    CARD_Q('Q'),
    CARD_K('K'),
    CARD_A('A');

    Card(char value) {
        this.value = value;
    }

    private final char value;

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public int alternativeOrder() {
        return this == CARD_J ? -1 : this.ordinal();
    }

    public static Card fromValue(char c) {
        return Arrays.stream(Card.values()).filter(card -> card.getValue() == c).findFirst().orElseThrow();
    }

    public static Map<Card, Long> distribution(Card[] cards) {
        return Arrays.stream(cards).collect(groupingBy(c -> c, HashMap::new, counting()));
    }

    public static List<? extends Map<Card, Long>> alternativeDistributions(Card[] cards) {
        return alternatives(cards).stream()
                .map(altCards -> Arrays.stream(altCards).collect(groupingBy(c -> c, HashMap::new, counting())))
                .toList();
    }

    public static List<Card[]> alternatives(Card[] cardDeck) {
        Map<Card, Long> initialDistributions = distribution(cardDeck);
        int jokerCount = initialDistributions.getOrDefault(Card.CARD_J, -1L).intValue();
        if (jokerCount <= 0) {
            return Collections.singletonList(cardDeck);
        }
        int[] jokerIndexes = getJokerIndexes(cardDeck, jokerCount);

        Card[] nonJokerCards = initialDistributions
                .keySet()
                .stream()
                .filter(c -> c != Card.CARD_J)
                .toArray(Card[]::new);
        if (nonJokerCards.length == 0) {
            Card[] maxResult = new Card[cardDeck.length];
            Arrays.fill(maxResult, CARD_A);
            return Collections.singletonList(maxResult);
        }
        final var permutations = permutations(nonJokerCards, jokerCount);
        return permutations.stream().map(p -> {
            Card[] copy = new Card[cardDeck.length];
            System.arraycopy(cardDeck, 0, copy, 0, copy.length);
            for (int i = 0; i < jokerIndexes.length; i++) {
                int jokerIndex = jokerIndexes[i];
                copy[jokerIndex] = p[i];
            }
            return copy;
        }).toList();
    }

    private static int[] getJokerIndexes(Card[] cardDeck, int jokerCount) {
        int[] jokerIndexes = new int[jokerCount];
        int j = 0;
        for (int i = 0; i < cardDeck.length; i++) {
            final var card = cardDeck[i];
            if (card == CARD_J) {
                jokerIndexes[j++] = i;
            }
        }
        return jokerIndexes;
    }

    static List<Card[]> permutations(Card[] source, int length) {
        List<Card[]> permutations = new ArrayList<>();
        Card[] target = new Card[length];
        for (int a = 0; a < source.length; a++) {
            Arrays.fill(target, source[a]);
            permutations.add(getCopy(target, length));
            for (int j = a; j < source.length; j++) {
                if (j != a) {
                    for (int i = 1; i < target.length; i++) {
                        target[i] = source[j];
                        permutations.add(getCopy(target, length));
                    }
                }
            }
        }
        return permutations;
    }

    private static Card[] getCopy(Card[] target, int length) {
        Card[] toSave = new Card[length];
        System.arraycopy(target, 0, toSave, 0, length);
        return toSave;
    }
}
