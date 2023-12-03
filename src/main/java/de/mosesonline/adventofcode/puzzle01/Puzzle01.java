package de.mosesonline.adventofcode.puzzle01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Puzzle01 {

    private final Part part;

    Puzzle01(Part part) {
        this.part = part;
    }

    public static void runPart1() {
        try {
            Puzzle01 puzzel = new Puzzle01(Part.ONE);
            URL resource = puzzel.getClass().getClassLoader().getResource("20231201_realdata.txt");
            int result = puzzel.sum(new File(resource.toURI()));
            System.out.println("20231201_1: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runPart2() {
        try {
            Puzzle01 puzzel = new Puzzle01(Part.TWO);
            URL resource = puzzel.getClass().getClassLoader().getResource("20231201_realdata.txt");
            int result = puzzel.sum(new File(resource.toURI()));
            System.out.println("20231201_2: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int sum(File input) throws IOException {
        int sum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String currentLine = reader.readLine();
            while (currentLine != null && !currentLine.isEmpty()) {
                sum += findFirst(currentLine) * 10 + findLast(currentLine);
                currentLine = reader.readLine();
            }
        }
        return sum;
    }

    private int findFirst(String currentLine) {
        for (int i = 0; i < currentLine.length(); i++) {
            if (currentLine.charAt(i) >= 0x30 && currentLine.charAt(i) <= 0x39) {
                return currentLine.charAt(i) - 0x30;
            }
            if (part == Part.TWO) {
                int number = matchesNumber(currentLine.substring(i));
                if (number > 0) {
                    return number;
                }
            }
        }
        return 0;
    }

    private int findLast(String currentLine) {
        for (int i = currentLine.length() - 1; i >= 0; i--) {
            if (currentLine.charAt(i) >= 0x30 && currentLine.charAt(i) <= 0x39) {
                return currentLine.charAt(i) - 0x30;
            }
            if (part == Part.TWO) {
                int number = matchesNumberBackwards(currentLine.substring(0, i + 1));
                if (number > 0) {
                    return number;
                }
            }

        }
        return 0;
    }

    private int matchesNumber(String currentLine) {
        int length = currentLine.length();
        if (length < 3) {
            return 0;
        }
        if (currentLine.startsWith("one")) {
            return 1;
        } else if (currentLine.startsWith("two")) {
            return 2;
        } else if (length >= 5 && currentLine.startsWith("three")) {
            return 3;
        } else if (length >= 4 && currentLine.startsWith("four")) {
            return 4;
        } else if (length >= 4 && currentLine.startsWith("five")) {
            return 5;
        } else if (currentLine.startsWith("six")) {
            return 6;
        } else if (length >= 5 && currentLine.startsWith("seven")) {
            return 7;
        } else if (length >= 5 && currentLine.startsWith("eight")) {
            return 8;
        } else if (length >= 4 && currentLine.startsWith("nine")) {
            return 9;
        }
        return 0;
    }

    private int matchesNumberBackwards(String currentLine) {
        int length = currentLine.length();
        if (length < 3) {
            return 0;
        }
        if (currentLine.endsWith("one")) {
            return 1;
        } else if (currentLine.endsWith("two")) {
            return 2;
        } else if (length >= 5 && currentLine.endsWith("three")) {
            return 3;
        } else if (length >= 4 && currentLine.endsWith("four")) {
            return 4;
        } else if (length >= 4 && currentLine.endsWith("five")) {
            return 5;
        } else if (currentLine.endsWith("six")) {
            return 6;
        } else if (length >= 5 && currentLine.endsWith("seven")) {
            return 7;
        } else if (length >= 5 && currentLine.endsWith("eight")) {
            return 8;
        } else if (length >= 4 && currentLine.endsWith("nine")) {
            return 9;
        }
        return 0;
    }

    enum Part {
        ONE,
        TWO
    }
}
