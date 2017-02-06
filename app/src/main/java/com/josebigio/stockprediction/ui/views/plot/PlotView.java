package com.josebigio.stockprediction.ui.views.plot;

import android.graphics.Color;

import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.josebigio.stockprediction.models.PlotData;

/**
 * <h1>PlotView</h1>
 */
public class PlotView {

    XYPlot graph;
    private PlotData currentPlotData = new PlotData();
    private String currentTitle = "";
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
        Number[] domainLabels = plotData.getxCoords().toArray(new Number[plotData.getxCoords().size()]);
        XYSeries series1 = new SimpleXYSeries(
                plotData.getyCoords(), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Strike");
        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.TRANSPARENT, null);
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));
        graph.addSeries(series1, series1Format);
//        graph.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
//            @Override
//            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
//                int i = Math.round(((Number) obj).floatValue());
//                return toAppendTo.append(domainLabels[i]); //x choords
//            }
//            @Override
//            public Object parseObject(String source, ParsePosition pos) {
//                return null;
//            }
//        });
        //PanZoom.attach(graph);
        graph.redraw();
    }


}
