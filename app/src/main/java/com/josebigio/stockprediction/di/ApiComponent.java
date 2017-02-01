package com.josebigio.stockprediction.di;

import com.josebigio.stockprediction.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <h1>ApiComponent</h1>
 */
@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {

    void inject(MainActivity mainActivity);

}
