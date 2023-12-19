package de.mosesonline.adventofcode.puzzle12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

import static de.mosesonline.adventofcode.puzzle12.SpringStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpringRowTest {

    @ParameterizedTest
    @MethodSource("groups")
    void shouldValidateGroups(SpringRow row, boolean valid) {
        SpringStatus[] rowStates = row.rowStates().rowStates();
        int unknwonCount = (int) Arrays.stream(rowStates).filter(s -> s == UNKNOWN).count();
        assertEquals(valid, SpringRow.validRow(rowStates, SpringRow.getBinaryString(BigInteger.valueOf(row.rowStates().permutation()), unknwonCount), row.damagedLengthList()));
    }

    @Test
    void shouldFindAllPossibleValidGroups() {
        final var target = new SpringRow(new StringStates(new SpringStatus[]{UNKNOWN, DAMAGED, DAMAGED, DAMAGED, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN}, -1), new int[]{3, 2, 1});
        int result = target.possibleArrangements();

        assertEquals(10, result);
    }

    @Test
    void shouldFindAllPossibleValidGroups1() {
        final var target = new SpringRow(new StringStates(new SpringStatus[]{UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, DAMAGED, DAMAGED, DAMAGED}, -1), new int[]{1, 1, 3});
        int result = target.possibleArrangements();

        assertEquals(1, result);
    }

    @Test
    void shouldFindAllPossibleValidGroups2() {
        final var target = new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, UNKNOWN, UNKNOWN, OPERATIONAL, OPERATIONAL, UNKNOWN, UNKNOWN, OPERATIONAL, OPERATIONAL, OPERATIONAL, UNKNOWN, DAMAGED, DAMAGED}, -1), new int[]{1, 1, 3});
        int result = target.possibleArrangements();

        assertEquals(4, result);
    }

    @Test
    void shouldFindAllPossibleValidGroups3() {
        final var target = new SpringRow(new StringStates(new SpringStatus[]{UNKNOWN, DAMAGED, UNKNOWN, DAMAGED, UNKNOWN, DAMAGED, UNKNOWN, DAMAGED, UNKNOWN, DAMAGED, UNKNOWN, DAMAGED, UNKNOWN, DAMAGED, UNKNOWN}, -1), new int[]{1, 3, 1, 6});
        int result = target.possibleArrangements();

        assertEquals(1, result);
    }

    @Test
    void shouldFindAllPossibleValidGroups4() {
        final var target = new SpringRow(new StringStates(new SpringStatus[]{UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL}, -1), new int[]{4, 1, 1});
        int result = target.possibleArrangements();

        assertEquals(1, result);
    }

    @Test
    void shouldFindAllPossibleValidGroups5() {
        final var target = new SpringRow(new StringStates(new SpringStatus[]{UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL}, -1), new int[]{1, 6, 5});
        final var result = target.possibleArrangements();

        assertEquals(4, result);
    }


    @Test
    void shouldFindAllPossibleValidGroups6() {
        final var target = ConditionRecordParser.unfoldSpringRow(new String[]{"?###????????", "3,2,1"});
        final var result = target.possibleArrangements();

        assertEquals(506250, result);
    }

    static Stream<Arguments> groups() {
        return Stream.of(
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED, OPERATIONAL}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, DAMAGED}, -1), new int[]{3, 2, 1}), true),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, DAMAGED}, -1), new int[]{3, 2, 1}), false),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL}, -1), new int[]{3, 2, 1}), false),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL}, -1), new int[]{3, 2, 1}), false),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, DAMAGED, DAMAGED, OPERATIONAL, OPERATIONAL, OPERATIONAL}, -1), new int[]{1, 1, 1, 3, 5}), false),
                Arguments.of(new SpringRow(new StringStates(new SpringStatus[]{OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, OPERATIONAL, DAMAGED, OPERATIONAL}, -1), new int[]{1, 2, 1, 1}), false)

        );
    }
}
