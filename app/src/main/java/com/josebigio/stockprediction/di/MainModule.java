package com.josebigio.stockprediction.di;

import android.content.Context;

import com.josebigio.stockprediction.api.StocksProvider;
import com.josebigio.stockprediction.ui.presenters.implementations.SearchPresenterImp;
import com.josebigio.stockprediction.ui.presenters.interfaces.SearchPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <h1>ApiModule</h1>
 */
@Module
public class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public StocksProvider provideStocksProvider() {
        return new StocksProvider();
    }


    @Provides
    @Singleton
    public SearchPresenter provideSearchPresenter(Context context, StocksProvider stocksProvider) {
        return new SearchPresenterImp(context,stocksProvider);
    }


}
