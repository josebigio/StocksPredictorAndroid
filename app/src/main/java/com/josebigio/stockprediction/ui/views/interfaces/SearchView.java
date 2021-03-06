package com.josebigio.stockprediction.ui.views.interfaces;

import com.josebigio.stockprediction.models.PlotData;

import java.util.List;

/**
 * <h1>SearchViewImp</h1>
 */
public interface SearchView {
    void clearResults();
    void setSearchDataSet(List<String> results);
    void renderPlot(String title, PlotData plotData);
    void showLoading(boolean show);
    void showTextLoading(boolean show);
    void showError(String error);

}
