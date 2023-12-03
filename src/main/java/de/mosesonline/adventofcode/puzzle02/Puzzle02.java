package de.mosesonline.adventofcode.puzzle02;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Puzzle02 {
    private final GameParser gameParser = new GameParser();

    Puzzle02() {

    }

    public static void runPart1() {
        try {
            int result = new Puzzle02().playPartOne("20231202_realdata.txt");
            System.out.println("20231202_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runPart2() {
        try {
            int result = new Puzzle02().playPartTwo("20231202_realdata.txt");
            System.out.println("20231202_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    int playPartTwo(String file) throws URISyntaxException, IOException {
        List<Game> games = gameParser.parseGameFile(file);
        return games.stream().map(Game::minSet).map(Set::power)
                .mapToInt(Integer::intValue).sum();
    }

    int playPartOne(String file) throws URISyntaxException, IOException {
        List<Game> games = gameParser.parseGameFile(file);
        return games.stream().filter(Game::possible)
                .map(Game::number)
                .mapToInt(Integer::intValue).sum();
    }
}

