package de.mosesonline.adventofcode.puzzle12;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.mosesonline.adventofcode.common.FileLoader.getInstance;

public class ConditionRecordParser {

    public ConditionRecord parse(File file) throws IOException {
        List<SpringRow> rows = new ArrayList<>();

        getInstance().parseLineByLine(file, (currentLine) -> {
            if (currentLine.isBlank()) {
                return;
            }
            String[] data = currentLine.split(" +");
            SpringStatus[] conditions = data[0].chars().mapToObj(i -> SpringStatus.fromChar((char) i)).toArray(SpringStatus[]::new);
            int[] contiguousDamageGroups = Arrays.stream(data[1].split(",")).mapToInt(Integer::parseInt).toArray();
            rows.add(new SpringRow(new StringStates(conditions, -1), contiguousDamageGroups));
        });

        return new ConditionRecord(rows);
    }

    public ConditionRecord parseFolded(File file) throws IOException {
        List<SpringRow> rows = new ArrayList<>();

        getInstance().parseLineByLine(file, (currentLine) -> {
            if (currentLine.isBlank()) {
                return;
            }
            String[] data = currentLine.split(" +");
            SpringRow row = unfoldSpringRow(data);
            rows.add(row);
        });

        return new ConditionRecord(rows);
    }

    static SpringRow unfoldSpringRow(String[] data) {
        SpringStatus[] conditionsTmp = data[0].chars().mapToObj(i -> SpringStatus.fromChar((char) i)).toArray(SpringStatus[]::new);
        SpringStatus[] conditions = new SpringStatus[conditionsTmp.length * 5 + 4];
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                conditions[i * conditionsTmp.length + i - 1] = SpringStatus.UNKNOWN;
            }
            System.arraycopy(conditionsTmp, 0, conditions, i * conditionsTmp.length + i, conditionsTmp.length);
        }
        int[] contiguousDamageGroupsTmp = Arrays.stream(data[1].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] contiguousDamageGroups = new int[contiguousDamageGroupsTmp.length * 5];
        for (int i = 0; i < 5; i++) {
            System.arraycopy(contiguousDamageGroupsTmp, 0, contiguousDamageGroups, i * contiguousDamageGroupsTmp.length, contiguousDamageGroupsTmp.length);
        }
        SpringRow row = new SpringRow(new StringStates(conditions, -1), contiguousDamageGroups);
        return row;
    }
}
