package de.mosesonline.adventofcode.puzzle10;

import java.io.File;
import java.io.IOException;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class PlanParser {
    public PipePlan parse(File file) throws IOException {
        PipePlan.PlanBuidler builder = new PipePlan.PlanBuidler();
        getInstance().parseLineByLine(file, (currentLine) -> {
            if (!currentLine.isBlank()) {
                currentLine.chars().forEach(i -> builder.add((char) i));
                builder.nextRow();
            }
        });
        return builder.build();
    }
}
