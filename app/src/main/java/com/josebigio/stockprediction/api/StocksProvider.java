package com.josebigio.stockprediction.api;

import com.josebigio.stockprediction.models.Stock;
import com.josebigio.stockprediction.models.optionchains.OptionsInfo;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * <h1>StocksProvider</h1>
 */
public class StocksProvider extends ApiProvider {

    private static final String HOST_NAME = "https://options-chain.herokuapp.com";

    public Observable<List<Stock>> findStocks(String query) {
        HashMap<String, String> params = new HashMap<>();
        params.put("name",query);
        return getService(ApiService.class, HOST_NAME).findStocks(params)
                .switchMap(listApiResponse -> Observable.just(listApiResponse.getData()));
    }

    public Observable<OptionsInfo> getStockData(Stock stock) {
        HashMap<String, String> params = new HashMap<>();
        params.put("stock_name",stock.getStockName());
        return getService(ApiService.class, HOST_NAME).getStockData(params)
                .switchMap(listApiResponce -> Observable.just(listApiResponce.getData()));
    }

}
