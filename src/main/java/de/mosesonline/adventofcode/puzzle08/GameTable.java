package de.mosesonline.adventofcode.puzzle08;

import java.math.BigInteger;
import java.util.Map;

public record GameTable(String stepInstructions, Map<String, Pair> field) {
    public int stepsToReach(String start, String end) {
        Path path = new Path(new String[]{start}, field);
        int step = 0;
        boolean notAllZ;
        int currentInstructionIndex = 0;
        do {
            notAllZ = checkStep(currentInstructionIndex, path);
            step++;
            currentInstructionIndex = ++currentInstructionIndex % stepInstructions.length();
        } while (notAllZ);
        return step;
    }

    public BigInteger stepsToReachFromMatcher(String start) {
        String[] initial = field.keySet()
                .stream()
                .filter(s -> s.matches(start))
                .toArray(String[]::new);
        Path path = new Path(initial, field);
        BigInteger step = BigInteger.ZERO;
        boolean notAllZ;
        int currentInstructionIndex = 0;
        do {
            notAllZ = checkStep(currentInstructionIndex, path);
            step = step.add(BigInteger.ONE);
            currentInstructionIndex = ++currentInstructionIndex % stepInstructions.length();
        } while (notAllZ);
        return step;
    }

    private boolean checkStep(int currentInstructionIndex, Path path) {
        char instruction = stepInstructions.charAt(currentInstructionIndex);
        return path.nextStep(instruction);
    }

}
