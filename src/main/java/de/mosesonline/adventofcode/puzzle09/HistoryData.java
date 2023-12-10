package de.mosesonline.adventofcode.puzzle09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record HistoryData(List<Integer[]> data) {

    public int sumNextExtrapolatedValues() {
        return data.stream()
                .map(this::analyseData)
                .mapToInt(this::extrapolate)
                .sum();
    }

    private List<Integer[]> analyseData(Integer[] sensorData) {
        List<Integer[]> extrapolationData = new ArrayList<>();
        extrapolationData.add(sensorData);
        while (Arrays.stream(extrapolationData.getLast()).anyMatch(i -> i != 0)) {
            Integer[] currentDataset = extrapolationData.getLast();
            Integer[] nextDataSet = new Integer[currentDataset.length - 1];
            for (int i = 0; i < nextDataSet.length; i++) {
                nextDataSet[i] = currentDataset[i + 1] - currentDataset[i];
            }
            extrapolationData.add(nextDataSet);
        }
        for (Integer[] data : extrapolationData) {
            System.out.println(Arrays.toString(data));
        }
        System.out.println();
        return extrapolationData;
    }

    private int extrapolate(List<Integer[]> sensorData) {
        Integer[] extrapolatedValues = new Integer[sensorData.size()];
        extrapolatedValues[extrapolatedValues.length - 1] = 0;
        for (int i = sensorData.size() - 2; i >= 0; i--) {
            Integer[] currentSensorData = sensorData.get(i);
            extrapolatedValues[i] = extrapolatedValues[i + 1] + currentSensorData[currentSensorData.length - 1];
        }
        return extrapolatedValues[0];
    }

    public int sumPreviousExtrapolatedValues() {
        return data.stream()
                .map(this::analyseData)
                .mapToInt(this::extrapolateBackwards)
                .sum();
    }

    private int extrapolateBackwards(List<Integer[]> sensorData) {
        Integer[] extrapolatedValues = new Integer[sensorData.size()];
        extrapolatedValues[extrapolatedValues.length - 1] = 0;
        for (int i = sensorData.size() - 2; i >= 0; i--) {
            Integer[] currentSensorData = sensorData.get(i);
            extrapolatedValues[i] = currentSensorData[0] - extrapolatedValues[i + 1];
        }
        return extrapolatedValues[0];
    }
}
