package de.mosesonline.adventofcode.puzzle07;

import java.util.Comparator;
import java.util.Map;

public class Hand {
    private final Card[] cards = new Card[5];

    public Hand() {

    }

    public Hand(Card... givenCards) {
        System.arraycopy(givenCards, 0, cards, 0, 5);
    }

    public void setCard(int index, Card card) {
        assert index >= 0 && index < cards.length;
        cards[index] = card;
    }

    public Card[] getCards() {
        return cards;
    }

    public enum HandType {
        HIGH,
        PAIR,
        TWO_PAIR,
        THREE,
        FULL_HOUSE,
        FOUR,
        FIVE;

        static HandType fromHand(Card[] cards) {
            Map<Card, Long> distribution = Card.distribution(cards);
            return findHandType(distribution);
        }


        static HandType withJoker(Card[] cards) {
            final var distributions = Card.alternativeDistributions(cards);
            return distributions.stream()
                    .map(m -> (Map<Card, Long>) m)
                    .map(HandType::findHandType)
                    .max(Comparator.comparingInt(Enum::ordinal)).orElseThrow();
        }

        private static HandType findHandType(Map<Card, Long> distribution) {
            HandType current = HIGH;
            for (Long sameCardCount : distribution.values()) {
                if (sameCardCount == 5) {
                    current = FIVE;
                    break;
                } else if (sameCardCount == 4) {
                    current = FOUR;
                    break;
                } else if (sameCardCount == 3 && current == PAIR) {
                    current = FULL_HOUSE;
                    break;
                } else if (sameCardCount == 3) {
                    current = THREE;
                } else if (sameCardCount == 2 && current == PAIR) {
                    current = TWO_PAIR;
                    break;
                } else if (sameCardCount == 2 && current == THREE) {
                    current = FULL_HOUSE;
                    break;
                } else if (sameCardCount == 2) {
                    current = PAIR;
                }
            }
            return current;
        }
    }

    static class DefaultComparator implements Comparator<Hand> {

        @Override
        public int compare(Hand o1, Hand o2) {
            int compare = HandType.fromHand(o1.cards).compareTo(HandType.fromHand(o2.cards));
            if (compare != 0) {
                return compare;
            }
            for (int i = 0; i < 5 && compare == 0; i++) {
                compare = o1.cards[i].ordinal() - o2.cards[i].ordinal();
            }
            return (int) Math.signum(compare);
        }
    }

    static class Part2Comparator implements Comparator<Hand> {

        @Override
        public int compare(Hand o1, Hand o2) {
            int compare = HandType.withJoker(o1.cards).compareTo(HandType.withJoker(o2.cards));
            if (compare != 0) {
                return compare;
            }
            for (int i = 0; i < 5 && compare == 0; i++) {
                compare = o1.cards[i].alternativeOrder() - o2.cards[i].alternativeOrder();
            }
            return (int) Math.signum(compare);
        }
    }
}
