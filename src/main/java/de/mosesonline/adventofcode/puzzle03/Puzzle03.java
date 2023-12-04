package de.mosesonline.adventofcode.puzzle03;

import de.mosesonline.adventofcode.common.FileLoader;

import java.io.File;
import java.io.IOException;

public class Puzzle03 {
    private static final FileLoader FILE_LOADER = new FileLoader();
    private static final EngineSchemaParser ENGINE_SCHEMA_PARSER = new EngineSchemaParser();

    Puzzle03() {
    }

    public static void runPart1() {
        try {
            Puzzle03 puzzle = new Puzzle03();
            int result = puzzle.sumPart1(FILE_LOADER.loadFromResource("20231203_realdata.txt"));
            System.out.println("20231203_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runPart2() {
        try {
            Puzzle03 puzzle = new Puzzle03();
            int result = puzzle.sumPart2(FILE_LOADER.loadFromResource("20231203_realdata.txt"));
            System.out.println("20231203_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int sumPart1(File input) throws IOException {
        Engine engine = ENGINE_SCHEMA_PARSER.parse(input);
        return engine.getPartNumbers().stream()
                .filter(engine::numberAdjacentSymbol)
                .map(Engine.PartNumber::value)
                .reduce(0, Integer::sum);
    }

    public int sumPart2(File input) throws IOException {
        Engine engine = ENGINE_SCHEMA_PARSER.parse(input);
        return engine.findAllGears().stream()
                .map(g -> g.one().value() * g.two().value())
                .reduce(0, Integer::sum);
    }

}
