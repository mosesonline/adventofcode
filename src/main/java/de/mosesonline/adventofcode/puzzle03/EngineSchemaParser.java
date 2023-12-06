package de.mosesonline.adventofcode.puzzle03;

import java.io.File;
import java.io.IOException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

class EngineSchemaParser {

    Engine parse(File file) throws IOException {
        final var engineBuilder = Engine.builder();
        getInstance().parseLineByLine(file, engineBuilder::addLine);
        return engineBuilder.build();
    }
}
