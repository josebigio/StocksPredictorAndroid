package com.josebigio.stockprediction.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <h1>ApiProvider</h1>
 */
class ApiProvider {


    <T> T getService(Class<T> clazz, String domain) {
        return getService(clazz, domain, HttpLoggingInterceptor.Level.BODY);
    }

    private <T> T getService(Class<T> clazz, String domain, HttpLoggingInterceptor.Level loggingLevel) {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createClient(loggingLevel))
                .build();
        return adapter.create(clazz);
    }

    private OkHttpClient createClient(HttpLoggingInterceptor.Level loggingLevel) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(loggingLevel);
        return new OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }
}
