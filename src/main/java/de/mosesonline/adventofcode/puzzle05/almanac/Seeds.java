package de.mosesonline.adventofcode.puzzle05.almanac;

import java.util.*;

record Seeds(List<Seed> seedNumbers) {


    Iterator<Seed> asRange() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator<Seed> {
        private int currentSeedIndex = 0;
        private long innerSeedNumber;
        private long innerSeedRange;

        private MyIterator() {
            setInnerSeedData();
        }

        @Override
        public boolean hasNext() {
            return currentSeedIndex < seedNumbers.size();
        }

        @Override
        public Seed next() {
            final var seed = new Seed(innerSeedNumber);
            if (innerSeedNumber < innerSeedRange) {
                innerSeedNumber++;
            } else {
                currentSeedIndex += 2;
                setInnerSeedData();
            }
            return seed;
        }


        private void setInnerSeedData() {
            if (currentSeedIndex < seedNumbers.size()) {
                long seed = seedNumbers.get(currentSeedIndex).seed();
                innerSeedNumber = seed;
                innerSeedRange = seed + seedNumbers.get(currentSeedIndex + 1).seed() - 1;
            }
        }
    }
}
