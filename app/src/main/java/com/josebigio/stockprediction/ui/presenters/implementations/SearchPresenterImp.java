package com.josebigio.stockprediction.ui.presenters.implementations;

import android.content.Context;

import com.josebigio.stockprediction.api.StocksProvider;
import com.josebigio.stockprediction.models.PlotData;
import com.josebigio.stockprediction.models.Stock;
import com.josebigio.stockprediction.models.optionchains.Call;
import com.josebigio.stockprediction.ui.presenters.interfaces.SearchPresenter;
import com.josebigio.stockprediction.ui.views.interfaces.SearchView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * <h1>SearchPresenterImp</h1>
 */
public class SearchPresenterImp implements SearchPresenter {

    private SearchView searchView;
    private Context context;
    private StocksProvider stocksProvider;
    private List<Stock> lastStocks;

    public SearchPresenterImp(Context context, StocksProvider stocksProvider) {
        this.context = context;
        this.stocksProvider = stocksProvider;
    }

    @Override
    public void setView(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onStockSelected(int pos) {
        Stock selectedStock = lastStocks.get(pos);
        stocksProvider.getStockData(selectedStock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(optionsInfo -> {
                    Timber.d("onNext: %s",optionsInfo);
                    searchView.renderPlot(selectedStock.getCompanyName(),getPlotData(optionsInfo.getCalls()));
                }, error -> {
                    Timber.e("error getting stock info",error);
                });
    }

    @Override
    public void onTextChanged(String text) {
        stocksProvider.findStocks(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    Timber.d("stocks: %s",stocks);
                    searchView.clearResults();
                    searchView.setSearchDataSet(getResults(stocks));
                    lastStocks = stocks;

                }, error -> {
                    Timber.e("error getting stocks",error);
                });
    }


    private List<String> getResults(List<Stock> list) {
        List<String> result = new ArrayList<>();
        for(Stock stock: list) {
            result.add(stock.getCompanyName());
        }
        return result;
    }

    private PlotData getPlotData(List<Call> calls) {
        PlotData result = new PlotData();
        List<Number> xCoords = new ArrayList<>();
        List<Number> yCoords = new ArrayList<>();
        for(Call call: calls) {
            xCoords.add(Float.parseFloat(call.getP()));
            yCoords.add(Float.parseFloat(call.getStrike()));
        }
        result.setxCoords(xCoords);
        result.setyCoords(yCoords);
        return result;
    }
}
