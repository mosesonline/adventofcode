package de.mosesonline.adventofcode.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileLoader {
    private static FileLoader instance;

    private FileLoader() {

    }


    public static FileLoader getInstance() {
        if (instance == null) {
            instance = new FileLoader();
        }
        return instance;
    }

    public File loadFromResource(String fileName) throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return new File(resource.toURI());
    }

    public void parseLineByLine(File file, Consumer<String> consumer) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = br.readLine();
            while (currentLine != null) {
                consumer.accept(currentLine);
                currentLine = br.readLine();
            }
        }
    }

    public Stream<String> parseLineByLineToStream(File file) throws IOException {
        return parseLineByLine(file).stream();
    }

    public List<String> parseLineByLine(File file) throws IOException {
        final var lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine = br.readLine();
            while (currentLine != null) {
                lines.add(currentLine);
                currentLine = br.readLine();
            }
        }
        return lines;
    }
}
