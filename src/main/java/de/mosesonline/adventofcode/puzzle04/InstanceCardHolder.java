package de.mosesonline.adventofcode.puzzle04;

record InstanceCardHolder(Card card, Integer count) {
    InstanceCardHolder addInstances(int count) {
        return new InstanceCardHolder(card(), count() + count);
    }
}
