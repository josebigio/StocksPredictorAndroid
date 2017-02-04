package com.josebigio.stockprediction.di;

import com.josebigio.stockprediction.MainActivity;
import com.josebigio.stockprediction.ui.views.implementations.SearchViewImp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <h1>MainComponent</h1>
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);
    void inject(SearchViewImp searchViewImp);

}
