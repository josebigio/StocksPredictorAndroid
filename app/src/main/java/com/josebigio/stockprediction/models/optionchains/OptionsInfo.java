package com.josebigio.stockprediction.models.optionchains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <h1>OptionsInfo</h1>
 */
public class OptionsInfo {
    @SerializedName("expiry")
    @Expose
    private Expiry expiry;
    @SerializedName("expirations")
    @Expose
    private List<Expiration> expirations = null;
    @SerializedName("puts")
    @Expose
    private List<Put> puts = null;
    @SerializedName("calls")
    @Expose
    private List<Call> calls = null;
    @SerializedName("underlying_id")
    @Expose
    private String underlyingId;
    @SerializedName("underlying_price")
    @Expose
    private Double underlyingPrice;

    public Expiry getExpiry() {
        return expiry;
    }

    public void setExpiry(Expiry expiry) {
        this.expiry = expiry;
    }

    public List<Expiration> getExpirations() {
        return expirations;
    }

    public void setExpirations(List<Expiration> expirations) {
        this.expirations = expirations;
    }

    public List<Put> getPuts() {
        return puts;
    }

    public void setPuts(List<Put> puts) {
        this.puts = puts;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

    public String getUnderlyingId() {
        return underlyingId;
    }

    public void setUnderlyingId(String underlyingId) {
        this.underlyingId = underlyingId;
    }

    public Double getUnderlyingPrice() {
        return underlyingPrice;
    }

    public void setUnderlyingPrice(Double underlyingPrice) {
        this.underlyingPrice = underlyingPrice;
    }

}
