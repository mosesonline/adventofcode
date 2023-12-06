package de.mosesonline.adventofcode.puzzle04;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

record Card(int number, List<Integer> winningNumbers, List<Integer> playNumbers) {
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^Card +(?<cardNumber>\\d+)$");

    static Card from(String currentLine) {
        String[] cardMetaAndDataSplit = currentLine.split(":");
        int cardNumber = parseCardNumber(cardMetaAndDataSplit[0]);
        String[] numbersSplit = cardMetaAndDataSplit[1].split("\\|");
        final var winningNumbers = parseNumberList(numbersSplit[0]);
        final var playNumbers = parseNumberList(numbersSplit[1]);
        return new Card(cardNumber, winningNumbers, playNumbers);
    }

    private static List<Integer> parseNumberList(String numbersString) {
        return Arrays.stream(numbersString.trim().split(" +"))
                .map(Integer::parseInt).sorted().toList();
    }

    private static int parseCardNumber(String cardDataString) {
        Matcher numberMatcher = CARD_NUMBER_PATTERN.matcher(cardDataString);
        if (!numberMatcher.find()) {
            throw new IllegalStateException("Must have card number");
        }

        return parseInt(numberMatcher.group("cardNumber"));
    }

    public int countMatches() {
        return playNumbers.stream().filter(winningNumbers::contains)
                .reduce(0, (a, b) -> ++a);
    }
}
