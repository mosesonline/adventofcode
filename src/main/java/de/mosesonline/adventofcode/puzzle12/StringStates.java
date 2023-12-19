package de.mosesonline.adventofcode.puzzle12;

import java.util.Arrays;
import java.util.Objects;

public record StringStates(SpringStatus[] rowStates, long permutation) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringStates that = (StringStates) o;
        return permutation == that.permutation && Arrays.equals(rowStates, that.rowStates);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(permutation);
        result = 31 * result + Arrays.hashCode(rowStates);
        return result;
    }
}
