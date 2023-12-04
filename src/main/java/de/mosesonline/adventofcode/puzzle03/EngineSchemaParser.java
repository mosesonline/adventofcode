package de.mosesonline.adventofcode.puzzle03;

import de.mosesonline.adventofcode.common.FileLoader;

import java.io.File;
import java.io.IOException;

class EngineSchemaParser {
    private static final FileLoader fileLoader = new FileLoader();

     Engine parse(File file) throws IOException {
        final var engineBuilder = Engine.builder();
        fileLoader.parseLineByLine(file, engineBuilder::addLine);
        return engineBuilder.build();
    }
}
