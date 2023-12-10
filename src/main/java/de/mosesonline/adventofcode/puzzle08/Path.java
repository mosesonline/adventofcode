package de.mosesonline.adventofcode.puzzle08;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Path {
    private final Node[] currentNodes;
    private final Map<String, Node> field;

    public Path(String[] initNodes, Map<String, Pair> initField) {
        this.field = initField.entrySet().stream()
                .map(entry -> new AbstractMap
                        .SimpleEntry<>(entry.getKey(), new Node(entry.getKey(), entry.getValue().left(), entry.getValue().right())))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
        currentNodes = new Node[initNodes.length];
        System.out.println(currentNodes.length);
        for (int i = 0; i < initNodes.length; i++) {
            currentNodes[i] = field.get(initNodes[i]);
            currentNodes[i].initChildren();
        }
    }

    public boolean nextStep(char instruction) {
        boolean notAllZ = false;
        for (int i = 0; i < currentNodes.length; i++) {
            currentNodes[i] = instruction == 'L' ? currentNodes[i].getLeft() : currentNodes[i].getRight();
            notAllZ = notAllZ || currentNodes[i].name.charAt(2) != 'Z';
        }
        if(!notAllZ){
            System.out.println(Arrays.toString(currentNodes));
        }
        return notAllZ;
    }

    public class Node {
        private final String name;
        private final String nameLeft;
        private final String nameRight;

        private Node left;

        private Node right;

        public Node(String name, String nameLeft, String nameRight) {
            this.name = name;
            this.nameLeft = nameLeft;
            this.nameRight = nameRight;
        }

        public Node getLeft() {
            if (left == null) {
                initChildren();
            }
            return left;
        }

        public Node getRight() {
            if (right == null) {
                initChildren();
            }
            return right;
        }

        private void initChildren() {
            left = field.get(nameLeft);
            right = field.get(nameRight);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", nameLeft='" + nameLeft + '\'' +
                    ", nameRight='" + nameRight + '\'' +
                    '}';
        }
    }
}
