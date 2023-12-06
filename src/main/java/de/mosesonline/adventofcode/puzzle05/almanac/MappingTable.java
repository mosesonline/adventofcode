package de.mosesonline.adventofcode.puzzle05.almanac;

public interface MappingTable {
    String to();
    String from();
    Seed map(Seed input);
}
