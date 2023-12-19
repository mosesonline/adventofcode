package de.mosesonline.adventofcode.puzzle12;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class ConditionRecord {

    private final List<SpringRow> rows;

    public ConditionRecord(List<SpringRow> rows) {
        this.rows = rows;
    }

    public BigInteger sumOfPossibleArrangements() {
        //System.out.println("result");
        //rows.stream().map(SpringRow::possibleArrangements).flatMap(List::stream).map(Arrays::toString).forEach(System.out::println);
        System.out.println(rows.size());
        return rows.parallelStream().map(SpringRow::possibleArrangements)
                .map(BigInteger::valueOf).reduce(BigInteger.ZERO, BigInteger::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConditionRecord that = (ConditionRecord) o;
        return Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }
}
