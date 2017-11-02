package com.example.katrindrozhak.productsdata.model;

import com.example.katrindrozhak.productsdata.interfaces.JsonRequired;
import com.google.gson.annotations.SerializedName;

public class Rate {

    @JsonRequired
    @SerializedName("from")
    private String from;

    @JsonRequired
    @SerializedName("rate")
    private String rate;

    @JsonRequired
    @SerializedName("to")
    private String to;

    private double value;

    public Rate(double value) {
        this.value = value;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTo() {
        return to;
    }

    public double getValue() {
        return Double.parseDouble(rate);
    }
}
