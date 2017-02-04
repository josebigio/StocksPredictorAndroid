package com.josebigio.stockprediction.ui.presenters.interfaces;

import com.josebigio.stockprediction.ui.views.interfaces.SearchView;

/**
 * <h1>SearchPresenter</h1>
 */
public interface SearchPresenter {

    void setView(SearchView searchView);
    void onStockSelected(int position);
    void onTextChanged(String text);
}
