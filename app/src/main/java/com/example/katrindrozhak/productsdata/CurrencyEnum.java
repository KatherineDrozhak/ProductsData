package com.example.katrindrozhak.productsdata;

public enum CurrencyEnum {
    GBP("GBP"),
    USD("USD"),
    AUD("AUD"),
    CAD("CAD");

    private String currency;

    CurrencyEnum(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
