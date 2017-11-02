package com.example.katrindrozhak.productsdata.model;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Product {

    private String sku;
    private List<Transaction> transactions = new ArrayList<>();

    public String getSku() {
        return sku;
    }

    public int getCount() {
        return transactions.size();
    }

    public Product(String sku) {
        this.sku = sku;
    }

    public Product() {
    }

    public void add(Transaction transaction) {
        if (!transactions.contains(transaction)) {
            transactions.add(transaction);
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }
}
