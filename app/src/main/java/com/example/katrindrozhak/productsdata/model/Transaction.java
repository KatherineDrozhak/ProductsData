package com.example.katrindrozhak.productsdata.model;

import com.example.katrindrozhak.productsdata.interfaces.JsonRequired;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Transaction {
    @JsonRequired
    @SerializedName("amount")
    private String amount;

    @JsonRequired
    @SerializedName("currency")
    private String currency;

    @JsonRequired
    @SerializedName("sku")
    String sku;

    private double convertedAmount;

    public Transaction() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
