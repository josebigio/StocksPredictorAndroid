package com.josebigio.stockprediction.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>PlotData</h1>
 */
public class PlotData implements Serializable {

    private List<Number> xCoords = new ArrayList<>();
    private List<Number> yCoords = new ArrayList<>();

    public List<Number> getxCoords() {
        return xCoords;
    }

    public void setxCoords(List<Number> xCoords) {
        this.xCoords = xCoords;
    }

    public List<Number> getyCoords() {
        return yCoords;
    }

    public void setyCoords(List<Number> yCoords) {
        this.yCoords = yCoords;
    }

    public Number getMaxX() {
        return findMaxValue(xCoords);
    }

    public Number getMaxY() {
        return findMaxValue(yCoords);
    }

    public Number getMinX() {
        return findMinValue(xCoords);
    }

    public Number getMinY() {
        return findMinValue(yCoords);
    }

    private Number findMaxValue(List<Number> list) {
        float max = Float.MIN_VALUE;
        for(Number number: list) {
            max = Math.max(number.floatValue(),max);
        }
        return max;
    }

    private Number findMinValue(List<Number> list) {
        float min = Float.MAX_VALUE;
        for(Number number: list) {
            min = Math.max(number.floatValue(),min);
        }
        return min;
    }

    @Override
    public String toString() {
        return String.format("xchords: %s, yChords: %s",xCoords,yCoords);
    }
}
