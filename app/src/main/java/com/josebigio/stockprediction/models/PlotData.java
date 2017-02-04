package com.josebigio.stockprediction.models;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>PlotData</h1>
 */
public class PlotData {

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
}
