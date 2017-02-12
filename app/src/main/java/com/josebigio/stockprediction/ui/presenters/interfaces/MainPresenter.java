package com.josebigio.stockprediction.ui.presenters.interfaces;

import com.josebigio.stockprediction.ui.views.interfaces.MainView;

/**
 * <h1>MainPresenter</h1>
 */
public interface MainPresenter {

    void setView(MainView mainView);
    void onActionClicked();
}
