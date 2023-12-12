package de.mosesonline.adventofcode.puzzle11;

import java.math.BigInteger;
import java.util.Objects;

public class LocationPoint {
    public final BigInteger x;
    public final BigInteger y;

    public LocationPoint(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }
    public LocationPoint(int x, int y) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
    }
    public LocationPoint(long x, long y) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationPoint that = (LocationPoint) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
