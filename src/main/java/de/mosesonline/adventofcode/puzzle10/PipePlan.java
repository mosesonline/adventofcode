package de.mosesonline.adventofcode.puzzle10;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.mosesonline.adventofcode.puzzle10.PipePlan.Direction.*;

public class PipePlan {

    private final Tile[][] plan;
    private final Point startPoint;

    private PipePlan(int startRow, int startColumn, Tile[][] plan) {
        this.startPoint = new Point(startColumn, startRow);
        this.plan = plan;
    }

    public int stepsToFarthestPoint() {
        final var tile = plan[startPoint.x][startPoint.y];
        System.out.println(tile);
        List<Tile> connectedTiles = connectsTo(tile, null);
        System.out.println(connectedTiles);
        List<Tile> leftWay = new ArrayList<>();
        leftWay.add(tile);
        leftWay.add(connectedTiles.getFirst());
        List<Tile> rightWay = new ArrayList<>();
        rightWay.add(tile);
        rightWay.add(connectedTiles.getLast());
        while (!leftWay.getLast().equals(rightWay.getLast())) {
            connectsTo(leftWay.getLast(), leftWay.get(leftWay.size() - 2))
                    .stream()
                    .findFirst().ifPresent(leftWay::add);
            connectsTo(rightWay.getLast(), rightWay.get(rightWay.size() - 2))
                    .stream()
                    .findFirst().ifPresent(rightWay::add);
        }
        return rightWay.size()-1;
    }

    public List<Tile> connectsTo(Tile tile, Tile source) {
        List<Tile> tiles = new ArrayList<>();
        if (tile.pipe.connectsTo(W) && tile.point.x - 1 >= 0) {
            Tile target = plan[tile.point.y][tile.point.x - 1];
            if (source != target && target.pipe.connectsTo(E)) {
                tiles.add(target);
            }
        }
        if (tile.pipe.connectsTo(E) && tile.point.x + 1 < plan[tile.point.y].length) {
            Tile target = plan[tile.point.y][tile.point.x + 1];
            if (source != target && target.pipe.connectsTo(W)) {
                tiles.add(target);
            }
        }
        if (tile.pipe.connectsTo(S) && tile.point.y + 1 < plan.length) {
            Tile target = plan[tile.point.y + 1][tile.point.x];
            if (source != target && target.pipe.connectsTo(N)) {
                tiles.add(target);
            }
        }
        if (tile.pipe.connectsTo(N) && tile.point.y - 1 >= 0) {
            Tile target = plan[tile.point.y - 1][tile.point.x];
            if (source != target && target.pipe.connectsTo(S)) {
                tiles.add(target);
            }
        }
        return tiles;
    }

    public int countEnclosed() {

        return 0;
    }


    public static class PlanBuidler {
        private final List<Tile[]> plan = new ArrayList<>();
        private final List<Tile> currentRow = new ArrayList<>();
        private int startRow;
        private int startColumn;
        private int currentX = 0;
        private int currentY = 0;

        public PlanBuidler() {

        }

        public PlanBuidler add(char c) {
            Pipe e = Pipe.fromChar(c);
            if (e == Pipe.START) {
                startColumn = currentRow.size();
                startRow = plan.size();
            }
            currentRow.add(new Tile(new Point(currentX, currentY), e));
            currentX++;
            return this;
        }

        public PlanBuidler nextRow() {
            plan.add(currentRow.toArray(Tile[]::new));
            currentRow.clear();
            currentY++;
            currentX = 0;
            return this;
        }

        public PipePlan build() {
            return new PipePlan(startRow, startColumn, plan.toArray(Tile[][]::new));
        }
    }

    public record Tile(Point point, Pipe pipe) {

    }

    public enum Direction {
        N,
        E,
        S,
        W
    }

    public enum Pipe {
        VERTICAL('|', N, S),
        HORIZONTAL('-', E, W),
        NORTH_EAST('L', N, E),
        NORTH_WEST('J', N, W),
        SOUTH_WEST('7', W, S),
        SOUTH_EAST('F', E, S),
        EMPTY('.'),
        START('S', N, E, S, W);

        private final char value;
        private final Direction[] connectedDriections;

        Pipe(char value, Direction... connectedDriections) {
            this.value = value;
            this.connectedDriections = connectedDriections;
        }

        public char getValue() {
            return value;
        }

        public boolean connectsTo(Direction d) {
            for (int i = 0; i < connectedDriections.length; i++) {
                if (connectedDriections[i] == d) {
                    return true;
                }
            }
            return false;
        }

        static Pipe fromChar(char c) {
            return Arrays.stream(values()).filter(v -> v.getValue() == c).findFirst().orElseThrow();
        }
    }
}
