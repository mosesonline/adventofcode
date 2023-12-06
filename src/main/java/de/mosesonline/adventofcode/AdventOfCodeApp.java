package de.mosesonline.adventofcode;

import de.mosesonline.adventofcode.puzzle01.Puzzle01;
import de.mosesonline.adventofcode.puzzle02.Puzzle02;
import de.mosesonline.adventofcode.puzzle03.Puzzle03;
import de.mosesonline.adventofcode.puzzle04.Puzzle04;
import de.mosesonline.adventofcode.puzzle05.Puzzle05;

import java.util.ArrayList;
import java.util.List;

public class AdventOfCodeApp {

    public static void main(String[] args) {
        try {
            List<Thread> threadList = new ArrayList<>();
            threadList.add(Thread.startVirtualThread(Puzzle01::runPart1));
            threadList.add(Thread.startVirtualThread(Puzzle01::runPart2));
            threadList.add(Thread.startVirtualThread(Puzzle02::runPart1));
            threadList.add(Thread.startVirtualThread(Puzzle02::runPart2));
            threadList.add(Thread.startVirtualThread(Puzzle03::runPart1));
            threadList.add(Thread.startVirtualThread(Puzzle03::runPart2));
            threadList.add(Thread.startVirtualThread(Puzzle04::runPart1));
            threadList.add(Thread.startVirtualThread(Puzzle04::runPart2));
            threadList.add(Thread.startVirtualThread(Puzzle05::runPart1));
            threadList.add(Thread.startVirtualThread(Puzzle05::runPart2));
            threadList.forEach(t -> {
                try {
                    t.join();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
