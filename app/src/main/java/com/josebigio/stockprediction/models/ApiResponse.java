package com.josebigio.stockprediction.models;

import com.google.gson.annotations.SerializedName;

/**
 * <h1>ApiResponse</h1>
 */
public class ApiResponse<T> {

    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }
}
