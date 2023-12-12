package de.mosesonline.adventofcode.puzzle11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static de.mosesonline.adventofcode.puzzle11.EmptySpace.EMPTY_SPACE;

public class Universe {


    private final UniverseSpace[][] spaces;

    public Universe(UniverseSpace[][] spaces) {
        this.spaces = spaces;
    }

    public Universe expandAndName() {
        return expandAndName(2);
    }

    public Universe expandAndName(int expansionFactor) {
        final var emptyRows = new Boolean[spaces.length];
        Arrays.fill(emptyRows, true);
        final var emptyCols = new Boolean[spaces[0].length];
        Arrays.fill(emptyCols, true);
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                if (!(spaces[i][j] instanceof EmptySpace)) {
                    emptyRows[i] = false;
                    emptyCols[j] = false;
                }
            }
        }
        UniverseSpace[][] expandedSpace = new UniverseSpace[spaces.length][spaces[0].length];
        for (UniverseSpace[] universeSpaces : expandedSpace) {
            Arrays.fill(universeSpaces, EMPTY_SPACE);
        }
        int galaxyNumber = 1;
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                if (!(spaces[i][j] instanceof EmptySpace)) {
                    expandedSpace[i][j] = new LocalizedGalaxy(new LocationPoint(BigInteger.valueOf(j), BigInteger.valueOf(i)), Integer.toString(galaxyNumber++));
                } else if (emptyCols[j] || emptyRows[i]) {
                    expandedSpace[i][j] = new FoldedSpace(emptyCols[j] ? expansionFactor : 1, emptyRows[i] ? expansionFactor : 1);
                }
            }
        }
        return new Universe(expandedSpace);
    }

    public BigInteger sumOfShortestPathsBetweenGalaxies() {
        BigInteger sum = BigInteger.ZERO;
        final var galaxies = new ArrayList<LocalizedGalaxy>();
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[i].length; j++) {
                if (spaces[i][j] instanceof LocalizedGalaxy galaxy) {
                    sum = sum.add(galaxies.stream().map(g -> calculateDistance(g, galaxy)).reduce(BigInteger.ZERO, BigInteger::add));
                    galaxies.add(galaxy);
                }
            }
        }
        return sum;
    }

    BigInteger calculateDistance(LocalizedGalaxy g1, LocalizedGalaxy g2) {
        final var p1 = g1.getLocation();
        final var p2 = g2.getLocation();
        BigInteger horizontalDistance = p1.x.subtract(p2.x).abs().add(foldedX(p1.x, p2.x));
        BigInteger verticalDistance = p1.y.subtract(p2.y).abs().add(foldedY(p1.y, p2.y));
        return horizontalDistance.add(verticalDistance);
    }

    private BigInteger foldedY(BigInteger y1, BigInteger y2) {
        BigInteger start = y1.min(y2);
        BigInteger end = y1.max(y2);
        BigInteger iterator = start;
        BigInteger result = BigInteger.ZERO;
        while(end.subtract(iterator).compareTo(BigInteger.ZERO) >= 0){
            if(spaces[iterator.intValue()][0] instanceof FoldedSpace foldedSpace){
                result = result.add(BigInteger.valueOf(foldedSpace.getFoldFactorY())).subtract(BigInteger.ONE);
            }
            iterator = iterator.add(BigInteger.ONE);
        }
        return result;
    }

    private BigInteger foldedX(BigInteger x1, BigInteger x2) {
        BigInteger start = x1.min(x2);
        BigInteger end = x1.max(x2);
        BigInteger iterator = start;
        BigInteger result = BigInteger.ZERO;
        while(end.subtract(iterator).compareTo(BigInteger.ZERO) >= 0){
            if(spaces[0][iterator.intValue()] instanceof FoldedSpace foldedSpace){
                result = result.add(BigInteger.valueOf(foldedSpace.getFoldFactorX())).subtract(BigInteger.ONE);
            }
            iterator = iterator.add(BigInteger.ONE);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universe universe = (Universe) o;
        return Arrays.deepEquals(spaces, universe.spaces);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(spaces);
    }
}
