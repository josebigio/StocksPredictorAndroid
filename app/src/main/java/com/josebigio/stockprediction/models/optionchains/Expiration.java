package com.josebigio.stockprediction.models.optionchains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <h1>Expiration</h1>
 */
public class Expiration {

    @SerializedName("y")
    @Expose
    private Integer y;
    @SerializedName("m")
    @Expose
    private Integer m;
    @SerializedName("d")
    @Expose
    private Integer d;

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

}