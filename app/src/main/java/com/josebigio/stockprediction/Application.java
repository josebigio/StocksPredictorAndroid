package com.josebigio.stockprediction;

import android.app.Activity;
import android.os.Bundle;

import com.josebigio.stockprediction.di.DaggerMainComponent;
import com.josebigio.stockprediction.di.MainComponent;
import com.josebigio.stockprediction.di.MainModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * <h1>Application</h1>
 */
public class Application extends android.app.Application implements android.app.Application.ActivityLifecycleCallbacks{

    MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
        Timber.plant(new Timber.DebugTree());
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
       registerActivityLifecycleCallbacks(this);

    }


    public MainComponent getMainComponent() {
        return mainComponent;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
