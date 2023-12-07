package de.mosesonline.adventofcode.puzzle07;

public class Hand {
    public enum Card {
        CARD_2("2"),
        CARD_3("3"),
        CARD_4("4"),
        CARD_5("5"),
        CARD_6("6"),
        CARD_7("7"),
        CARD_8("8"),
        CARD_9("9"),
        CARD_T("T"),
        CARD_J("J"),
        CARD_Q("Q"),
        CARD_K("K"),
        CARD_A("A");

        Card(String value) {
            this.value = value;
        }

        private final String value;

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
