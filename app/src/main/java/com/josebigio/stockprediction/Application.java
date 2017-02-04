package com.josebigio.stockprediction;

import com.josebigio.stockprediction.di.DaggerMainComponent;
import com.josebigio.stockprediction.di.MainComponent;
import com.josebigio.stockprediction.di.MainModule;

import timber.log.Timber;

/**
 * <h1>Application</h1>
 */
public class Application extends android.app.Application {

    MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
        Timber.plant(new Timber.DebugTree());

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }


}
