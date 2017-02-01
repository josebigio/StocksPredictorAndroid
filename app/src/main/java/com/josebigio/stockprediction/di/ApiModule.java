package com.josebigio.stockprediction.di;

import android.content.Context;

import com.josebigio.stockprediction.api.StocksProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <h1>ApiModule</h1>
 */
@Module
public class ApiModule {

    private Context context;

    public ApiModule(Context context) {
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


}
