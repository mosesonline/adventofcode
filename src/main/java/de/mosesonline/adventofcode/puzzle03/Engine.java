package de.mosesonline.adventofcode.puzzle03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.lang.Integer.parseInt;

class Engine {
    private final char[][] engineSchema;

    private final List<PartNumber> partNumbers;

    private Engine(char[][] engineSchema, List<PartNumber> partNumbers) {
        this.engineSchema = engineSchema;
        this.partNumbers = partNumbers;
    }

    static Builder builder() {
        return new Builder();
    }

    boolean numberAdjacentSymbol(PartNumber partNumber) {
        final int maxY = Math.min(engineSchema.length - 1, partNumber.y + 1);
        int minY = Math.max(0, partNumber.y - 1);
        int minX = Math.max(0, partNumber.x1 - 1);
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= Math.min(engineSchema[y].length - 1, partNumber.x2plus1); x++) {
                char currentField = engineSchema[y][x];
                if (!isDigit(currentField) && currentField != '.') {
                    return true;
                }
            }
        }
        return false;
    }

    List<Gear> findAllGears() {
        final var gears = new ArrayList<Gear>();
        for (int y = 0; y < engineSchema.length; y++) {
            for (int x = 0; x < engineSchema[y].length; x++) {
                char character = engineSchema[y][x];
                if (isJoint(character)) {
                    List<PartNumber> adjacentPartNumbers = findAdjacentPartNumbers(x, y);
                    if (adjacentPartNumbers.size() == 2) {
                        gears.add(new Gear(x, y, adjacentPartNumbers.getFirst(), adjacentPartNumbers.getLast()));
                    }
                }
            }
        }
        return gears;
    }

    private List<PartNumber> findAdjacentPartNumbers(int x, int y) {
        return partNumbers.stream().filter(pn -> pn.y <= (y + 1) && pn.y >= (y - 1))
                .filter(pn -> pn.isAdjacent(x, y)).toList();
    }

    private static boolean isJoint(char character) {
        return character == '*';
    }

    List<PartNumber> getPartNumbers() {
        return new ArrayList<>(partNumbers);
    }

    static class Builder {
        private final List<char[]> lines = new ArrayList<>();

        private final List<PartNumber> partNumbers = new ArrayList<>();

        private final static Pattern numberPattern = Pattern.compile("\\d+");

        Builder addLine(String line) {
            char[] charArray = line.toCharArray();
            Matcher matcher = numberPattern.matcher(line);
            while (matcher.find()) {
                int x1 = matcher.start();
                int x2 = matcher.end();
                partNumbers.add(new PartNumber(parseInt(line.substring(x1, x2)), x1, x2, lines.size()));
            }
            lines.add(charArray);
            return this;
        }

        Engine build() {
            char[][] field = new char[lines.size()][lines.getFirst().length];
            for (int i = 0; i < lines.size(); i++) {
                field[i] = lines.get(i);
            }
            return new Engine(field, partNumbers);
        }

    }

    record PartNumber(int value, int x1, int x2plus1, int y) {
        boolean isAdjacent(int x, int y) {
            return x >= (x1 - 1) && x <= x2plus1 &&
                    y >= (this.y - 1) && y <= (this.y + 1);
        }
    }

    record Gear(int x, int y, PartNumber one, PartNumber two) {
    }
}
