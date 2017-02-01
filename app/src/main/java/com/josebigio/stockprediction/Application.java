package com.josebigio.stockprediction;

import com.josebigio.stockprediction.di.ApiComponent;
import com.josebigio.stockprediction.di.ApiModule;
import com.josebigio.stockprediction.di.DaggerApiComponent;

import timber.log.Timber;

/**
 * <h1>Application</h1>
 */
public class Application extends android.app.Application {

    ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        apiComponent = DaggerApiComponent.builder().apiModule(new ApiModule(this)).build();
        Timber.plant(new Timber.DebugTree());

    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
