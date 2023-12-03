package de.mosesonline.adventofcode.puzzle02;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class GameParser {

    public List<Game> parseGameFile(String path) throws URISyntaxException, IOException {
        List<Game> games = new ArrayList<>();
        URL resource = getClass().getClassLoader().getResource(path);
        try (BufferedReader br = new BufferedReader(new FileReader(new File(resource.toURI())))) {
            String currentLine = br.readLine();
            while (currentLine != null && !currentLine.isEmpty()) {
                games.add(parseGame(currentLine));
                currentLine = br.readLine();
            }
        }
        return games;
    }

    private Game parseGame(String currentLine) {
        String[] game = currentLine.split(": ");
        String[] setData = game[1].split("; ");
        Set[] set = new Set[setData.length];
        String[] colorData;
        for (int i = 0; i < setData.length; i++) {
            colorData = setData[i].split(", ");
            set[i] = parseSet(colorData);
        }
        return new Game(parseInt(game[0].substring(5)), set);
    }

    private static Set parseSet(String[] colorData) {
        int blue = 0;
        int green = 0;
        int red = 0;
        for (String colorDatum : colorData) {
            if (colorDatum.endsWith("blue")) {
                blue = parseInt(colorDatum.substring(0, colorDatum.length() - 5));
            }
            if (colorDatum.endsWith("green")) {
                green = parseInt(colorDatum.substring(0, colorDatum.length() - 6));
            }
            if (colorDatum.endsWith("red")) {
                red = parseInt(colorDatum.substring(0, colorDatum.length() - 4));
            }
        }
        return new Set(blue, green, red);
    }
}
