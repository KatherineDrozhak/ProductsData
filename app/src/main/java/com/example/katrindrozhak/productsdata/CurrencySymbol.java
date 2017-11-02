package com.example.katrindrozhak.productsdata;

public class CurrencySymbol {

    public String symbolFor(String currency) {
        if (CurrencyEnum.GBP.getCurrency().equals(currency)) {
            return "£";
        } else if (CurrencyEnum.USD.getCurrency().equals(currency)) {
            return "$";
        }
        return currency;
    }
}
