package de.mosesonline.adventofcode.puzzle05.almanac;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

record Seeds(List<Seed> seedNumbers) {


    Iterator<Seed> asRange() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator<Seed> {
        private int currentSeedIndex = 0;
        private long innerSeedNumber;
        private long innerSeedRange;
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        private MyIterator() {
            setInnerSeedData();
        }

        @Override
        public boolean hasNext() {
            Lock lock = this.lock.readLock();
            lock.lock();
            try {
                return currentSeedIndex < seedNumbers.size();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public Seed next() {
            Lock writeLock = lock.writeLock();
            writeLock.lock();
            try {
                final var seed = new Seed(innerSeedNumber);
                if (innerSeedNumber < innerSeedRange) {
                    innerSeedNumber++;
                } else {
                    currentSeedIndex += 2;
                    setInnerSeedData();
                }
                return seed;
            } finally {
                writeLock.unlock();
            }
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
