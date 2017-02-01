package com.josebigio.stockprediction.models;

import com.google.gson.annotations.SerializedName;

/**
 * <h1>Stock</h1>
 */
public class Stock {

    @SerializedName("companyName")
    private String companyName;
    @SerializedName("stockName")
    private String stockName;

    public String getCompanyName() {
        return companyName;
    }

    public String getStockName() {
        return stockName;
    }

    @Override
    public String toString() {
        return String.format("companyName: %s, stockName: %s", companyName,stockName);
    }
}
