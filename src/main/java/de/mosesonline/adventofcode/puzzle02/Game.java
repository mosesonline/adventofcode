package de.mosesonline.adventofcode.puzzle02;

import java.util.Arrays;

public record Game(int number, Set[] sets) {
    public boolean possible() {
        int maxBlue = Arrays.stream(sets).map(Set::blue).max(Integer::compareTo).orElse(0);
        int maxGreen = Arrays.stream(sets).map(Set::green).max(Integer::compareTo).orElse(0);
        int maxRed = Arrays.stream(sets).map(Set::red).max(Integer::compareTo).orElse(0);
        return maxBlue <= 14 && maxGreen <= 13 && maxRed <= 12;
    }

    public Set minSet() {
        int maxBlue = Arrays.stream(sets).map(Set::blue).max(Integer::compareTo).orElse(0);
        int maxGreen = Arrays.stream(sets).map(Set::green).max(Integer::compareTo).orElse(0);
        int maxRed = Arrays.stream(sets).map(Set::red).max(Integer::compareTo).orElse(0);
        return new Set(maxBlue, maxGreen, maxRed);
    }
}
