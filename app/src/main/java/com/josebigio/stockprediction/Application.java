package com.josebigio.stockprediction;

import com.josebigio.stockprediction.di.DaggerMainComponent;
import com.josebigio.stockprediction.di.MainComponent;
import com.josebigio.stockprediction.di.MainModule;
import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }



    public MainComponent getMainComponent() {
        return mainComponent;
    }


}
