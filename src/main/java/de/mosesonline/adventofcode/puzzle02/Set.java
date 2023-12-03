package de.mosesonline.adventofcode.puzzle02;

public record Set(int blue, int green, int red) {
    public int power() {
        return blue * green * red;
    }
}
