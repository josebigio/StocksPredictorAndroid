package com.josebigio.stockprediction.api;

import com.josebigio.stockprediction.models.ApiResponse;
import com.josebigio.stockprediction.models.Stock;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * <h1>ApiService</h1>
 */
public interface ApiService {

    @GET("/api/look_up/")
    Observable<ApiResponse<List<Stock>>>  findStocks(@QueryMap HashMap<String,String> params);
}
