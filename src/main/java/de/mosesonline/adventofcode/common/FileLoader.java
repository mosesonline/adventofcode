package de.mosesonline.adventofcode.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.function.Consumer;

public class FileLoader {
    public File loadFromResource(String fileName) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return new File(resource.toURI());
    }

    public void parseLineByLine(File file, Consumer<String> consumer) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = br.readLine();
            while (currentLine != null && !currentLine.isEmpty()) {
                consumer.accept(currentLine);
                currentLine = br.readLine();
            }
        }
    }
}
