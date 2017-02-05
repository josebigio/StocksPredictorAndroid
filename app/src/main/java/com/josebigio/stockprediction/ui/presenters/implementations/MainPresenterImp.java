package com.josebigio.stockprediction.ui.presenters.implementations;

import com.josebigio.stockprediction.ui.presenters.interfaces.MainPresenter;
import com.josebigio.stockprediction.ui.views.interfaces.MainView;

/**
 * <h1>MainPresenterImp</h1>
 */
public class MainPresenterImp implements MainPresenter {

    MainView mainView;

    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
        mainView.startSearchView();
    }
}
