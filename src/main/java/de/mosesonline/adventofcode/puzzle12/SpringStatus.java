package de.mosesonline.adventofcode.puzzle12;

import java.util.Arrays;

public enum SpringStatus {
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    private final char status;

    SpringStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "" + status;
    }

    public static SpringStatus fromChar(char i) {
        return Arrays.stream(values()).filter(s -> s.status == i).findFirst().orElseThrow();
    }
}
