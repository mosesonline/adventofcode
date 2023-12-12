package de.mosesonline.adventofcode.puzzle11;

import java.util.Objects;

public final class FoldedSpace extends UniverseSpace{
    private final int foldFactorX;
    private final int foldFactorY;

    public FoldedSpace(int foldFactorX, int foldFactorY) {
        this.foldFactorX = foldFactorX;
        this.foldFactorY = foldFactorY;
    }

    public int getFoldFactorX() {
        return foldFactorX;
    }

    public int getFoldFactorY() {
        return foldFactorY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoldedSpace that = (FoldedSpace) o;
        return foldFactorX == that.foldFactorX && foldFactorY == that.foldFactorY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(foldFactorX, foldFactorY);
    }
}
