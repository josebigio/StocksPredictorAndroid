package com.josebigio.stockprediction.di;

import android.content.Context;

import com.josebigio.stockprediction.api.StocksProvider;
import com.josebigio.stockprediction.ui.presenters.implementations.MainPresenterImp;
import com.josebigio.stockprediction.ui.presenters.implementations.SearchPresenterImp;
import com.josebigio.stockprediction.ui.presenters.interfaces.MainPresenter;
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
    public MainPresenter provideMainPresenter() {
        return new MainPresenterImp();
    }

    @Provides
    @Singleton
    public SearchPresenter provideSearchPresenter(StocksProvider stocksProvider) {
        return new SearchPresenterImp(stocksProvider);
    }




}
