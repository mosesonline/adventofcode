package de.mosesonline.adventofcode.puzzle07;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class Puzzle07 {
    Puzzle07() {

    }

    public static void runPart1() {
        try {
            Puzzle07 puzzle = new Puzzle07();
            int result = puzzle.totalWinnings(getInstance().loadFromResource("20231207_realdata.txt"));
            System.out.println("20231206_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void runPart2() {
        try {
            Puzzle07 puzzle = new Puzzle07();
            int result = puzzle.totalWinningsWithJoker(getInstance().loadFromResource("20231207_realdata.txt"));
            System.out.println("20231206_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public Integer totalWinnings(File file) throws IOException {
        List<Bet> bets = new BetParser().readBets(file);
        Hand.DefaultComparator comparator = new Hand.DefaultComparator();
        List<Integer> rankedList = bets.stream()
                .sorted((b1, b2) -> comparator.compare(b1.hand(), b2.hand())).map(Bet::bet).toList();
        int points = 0;
        for (int i = 0; i < rankedList.size(); i++) {
            points += rankedList.get(i) * (i + 1);
        }
        return points;
    }

    public Integer totalWinningsWithJoker(File file) throws IOException {
        List<Bet> bets = new BetParser().readBets(file);
        Hand.Part2Comparator comparator = new Hand.Part2Comparator();
        List<Integer> rankedList = bets.stream()
                .sorted((b1, b2) -> comparator.compare(b1.hand(), b2.hand())).map(Bet::bet).toList();
        int points = 0;
        for (int i = 0; i < rankedList.size(); i++) {
            points += rankedList.get(i) * (i + 1);
        }
        return points;
    }
}
