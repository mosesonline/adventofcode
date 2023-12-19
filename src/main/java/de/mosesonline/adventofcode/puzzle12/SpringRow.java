package de.mosesonline.adventofcode.puzzle12;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static de.mosesonline.adventofcode.puzzle12.SpringStatus.DAMAGED;
import static de.mosesonline.adventofcode.puzzle12.SpringStatus.UNKNOWN;

public record SpringRow(StringStates rowStates, int[] damagedLengthList) {

    public static final BigInteger BIG_TWO = BigInteger.valueOf(2);

    int possibleArrangements() {
        int expectedDamaged = Arrays.stream(damagedLengthList).sum();
        List<StringStates> result = Collections.synchronizedList(new ArrayList<>());
        SpringStatus[] rowStates = this.rowStates.rowStates();
        int unknowns = (int) Arrays.stream(rowStates).filter(s -> s == UNKNOWN).count();
        int knownDamaged = (int) Arrays.stream(rowStates).filter(s -> s == DAMAGED).count();
        BigInteger permutations = BIG_TWO.pow(unknowns);
        System.out.println("Permutations: " + permutations);
        int cpus = Runtime.getRuntime().availableProcessors();
        BigInteger steps =  BigInteger.valueOf(cpus).multiply(BigInteger.TEN).min(permutations);
        BigInteger parts = permutations.divide(steps);
        Stream.iterate(BigInteger.ZERO, i -> i.compareTo(parts) < 0, i -> i.add(BigInteger.ONE))
                .parallel()
                .forEach(j -> {
                    for (BigInteger i = j.multiply(steps); i.compareTo(j.add(BigInteger.ONE).multiply(steps)) < 0 && i.compareTo(permutations) < 0; i = i.add(BigInteger.ONE)) {
                        String binaryString = getBinaryString(i, unknowns);
                        if (isValidPermutation(i, knownDamaged, expectedDamaged)
                                && validRow(rowStates, binaryString, damagedLengthList)) {
                            System.out.println(i);
                            result.add(new StringStates(rowStates, i.longValue()));
                        }
                    }
                });
        System.out.println("Finished " + permutations);
        return result.size();
    }

    static boolean isValidPermutation(BigInteger i, int knownDamaged, int expectedDamaged) {
        return i.bitCount() + knownDamaged == expectedDamaged;
    }

    static boolean hasOneAtIndex(String permutationBinaryString, int idx) {
        return permutationBinaryString.charAt(idx) == '1';
    }

    static String getBinaryString(BigInteger i, int unknownCount) {
        char[] binaryString = new char[unknownCount];
        if (unknownCount == 0) {
            return "";
        }
        int iteration = unknownCount - 1;
        BigInteger[] division;
        while (!i.equals(BigInteger.ZERO)) {
            division = i.divideAndRemainder(BIG_TWO);
            binaryString[iteration--] = division[1].getLowestSetBit() != 0 ? '0' : '1';
            i = division[0];
        }
        return new String(binaryString);
    }

    static boolean validRow(SpringStatus[] rowStates, String permutationBinaryString, int[] damagedLengthList) {
        int i = 0;
        int unknownIdx = 0;
        for (int j = 0; j < rowStates.length && i < damagedLengthList.length; j++) {
            if (rowStates[j] == DAMAGED || rowStates[j] == UNKNOWN && hasOneAtIndex(permutationBinaryString, unknownIdx++)) {
                int damagedGroupLength = damagedLengthList[i];
                int indexAfterGroup = j + damagedGroupLength;
                if (indexAfterGroup > rowStates.length) {
                    //System.out.println("test2");
                    return false;
                }
                for (int k = j + 1; k < indexAfterGroup; k++) {
                    if (rowStates[k] != DAMAGED && !(rowStates[k] == UNKNOWN && hasOneAtIndex(permutationBinaryString, unknownIdx++))) {
                        //System.out.println("test3");
                        return false;
                    }
                }
                if (indexAfterGroup < rowStates.length && isNextNotOperational(permutationBinaryString, unknownIdx, rowStates[indexAfterGroup])) {
                    //System.out.println("test4");
                    return false;
                }
                j = indexAfterGroup - 1;
                i++;
            }
        }
        //System.out.println("test");
        return i == damagedLengthList.length;
    }

    private static boolean isNextNotOperational(String permutationBinaryString, int unknownIdx, SpringStatus rowStates) {
        return rowStates == DAMAGED || (rowStates == UNKNOWN && hasOneAtIndex(permutationBinaryString, unknownIdx));
    }


    @Override
    public String toString() {
        return "SpringRow{" +
                "rowStates=" + rowStates +
                ", damagedLengthList=" + Arrays.toString(damagedLengthList) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringRow springRow = (SpringRow) o;
        return rowStates.equals(springRow.rowStates) && Arrays.equals(damagedLengthList, springRow.damagedLengthList);
    }

    @Override
    public int hashCode() {
        int result = rowStates.hashCode();
        result = 31 * result + Arrays.hashCode(damagedLengthList);
        return result;
    }
}
