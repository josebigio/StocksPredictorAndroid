package com.josebigio.stockprediction.ui.views.plot;

import android.graphics.Color;

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.josebigio.stockprediction.models.PlotData;

/**
 * <h1>PlotView</h1>
 */
public class PlotView {

    private XYPlot graph;
    private PlotState state = new PlotState();

    public PlotView(XYPlot graph) {
        this.graph = graph;
        state.title= "";
        state.plotData = new PlotData();
    }

    public PlotState getState() {
        return state;
    }

    public void setState(PlotState state) {
        this.state = state;
        render(state.plotData,state.title);
    }

    public void render(PlotData plotData, String title) {
        this.state.plotData = plotData;
        this.state.title = title;
        graph.clear();
        graph.setTitle(title);
        XYSeries series1 = new SimpleXYSeries(plotData.getxCoords(),plotData.getyCoords(), "Strike");
        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.RED, Color.TRANSPARENT, null);
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        graph.addSeries(series1, series1Format);
        PanZoom.attach(graph);
        graph.redraw();
    }


}
