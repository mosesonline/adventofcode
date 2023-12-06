package de.mosesonline.adventofcode.puzzle04;

import java.util.List;

import static java.util.stream.Collectors.toMap;

class Scratchcards {
    int play(List<Card> listOfCards) {
        final var cardsIntancesHolder = listOfCards.stream().collect(toMap(Card::number, c -> new InstanceCardHolder(c, 1)));
        cardsIntancesHolder.forEach((number, instanceCardHolder) -> {
            int matchCount = instanceCardHolder.card().countMatches();
            for (int i = number + 1; i < number + matchCount + 1; i++) {
                if (cardsIntancesHolder.containsKey(i)) {
                    cardsIntancesHolder.put(i, cardsIntancesHolder.get(i).addInstances(instanceCardHolder.count()));
                }
            }
        });
        return cardsIntancesHolder.values().stream().mapToInt(InstanceCardHolder::count).sum();
    }
}
