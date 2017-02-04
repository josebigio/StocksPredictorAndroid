package com.josebigio.stockprediction.models.optionchains;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * <h1>Call</h1>
 */
public class Call {

    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("s")
    @Expose
    private String s;
    @SerializedName("e")
    @Expose
    private String e;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("cs")
    @Expose
    private String cs;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("cp")
    @Expose
    private String cp;
    @SerializedName("b")
    @Expose
    private String b;
    @SerializedName("a")
    @Expose
    private String a;
    @SerializedName("oi")
    @Expose
    private String oi;
    @SerializedName("vol")
    @Expose
    private String vol;
    @SerializedName("strike")
    @Expose
    private String strike;
    @SerializedName("expiry")
    @Expose
    private String expiry;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getOi() {
        return oi;
    }

    public void setOi(String oi) {
        this.oi = oi;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getStrike() {
        return strike;
    }

    public void setStrike(String strike) {
        this.strike = strike;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

}