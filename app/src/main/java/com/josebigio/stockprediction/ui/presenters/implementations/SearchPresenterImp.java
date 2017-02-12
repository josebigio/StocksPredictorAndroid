package com.josebigio.stockprediction.ui.presenters.implementations;

import com.josebigio.stockprediction.api.StocksProvider;
import com.josebigio.stockprediction.models.PlotData;
import com.josebigio.stockprediction.models.Stock;
import com.josebigio.stockprediction.models.optionchains.Call;
import com.josebigio.stockprediction.ui.presenters.interfaces.SearchPresenter;
import com.josebigio.stockprediction.ui.views.interfaces.SearchView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * <h1>SearchPresenterImp</h1>
 */
public class SearchPresenterImp implements SearchPresenter {

    private SearchView searchView;
    private StocksProvider stocksProvider;
    private List<Stock> lastStocks;

    @Inject
    public SearchPresenterImp(StocksProvider stocksProvider) {
        this.stocksProvider = stocksProvider;
    }

    @Override
    public void setView(SearchView searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onStockSelected(int pos) {
        Stock selectedStock = lastStocks.get(pos);
        searchView.showLoading(true);
        stocksProvider.getStockData(selectedStock)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(optionsInfo -> {
                    searchView.showLoading(false);
                    Timber.d("onNext: %s",optionsInfo);
                    searchView.renderPlot(selectedStock.getCompanyName(),getPlotData(optionsInfo.getCalls()));
                }, error -> {
                    searchView.showLoading(false);
                    searchView.showError("Could not get data for " + selectedStock.getCompanyName());
                    Timber.e("error getting stock info: %s",error);
                });
    }

    @Override
    public void onTextChanged(String text) {
        searchView.showTextLoading(true);
        stocksProvider.findStocks(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stocks -> {
                    searchView.showTextLoading(false);
                    Timber.d("stocks: %s",stocks);
                    searchView.clearResults();
                    searchView.setSearchDataSet(getResults(stocks));
                    lastStocks = stocks;
                }, error -> {
                    searchView.showTextLoading(false);
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
            float y;
            float x;
            try {
                y = Float.parseFloat(call.getP());
                x = Float.parseFloat(call.getStrike());
                yCoords.add(y);
                xCoords.add(x);
            }
            catch (NumberFormatException e) {
                Timber.e("error parsing data: %s",e);
            }

        }
        result.setxCoords(xCoords);
        result.setyCoords(yCoords);
        Timber.d("plotData: %s",result);
        return result;
    }
}
