package de.mosesonline.adventofcode.puzzle06;

import java.util.ArrayList;
import java.util.List;

public record Race(long time, long distance) {
    public int countPossibilities() {
        return possibilities().size();
    }

    public List<Long> possibilities() {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < time(); i++) {
            final var tryDistance = (time() - i) * i;
            if (tryDistance > distance) {
                result.add(tryDistance);
            }
        }
        return result;
    }
}
