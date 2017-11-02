package com.example.katrindrozhak.productsdata;

import com.example.katrindrozhak.productsdata.model.Product;
import com.example.katrindrozhak.productsdata.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by katrindrozhak on 02.11.2017.
 */

public class ProductCollection {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    ProductCollection(List<Transaction> transactions) {
        products = collect(transactions);
    }

    private List<Product> collect(List<Transaction> transactions) {

        HashMap<String, List<Transaction>> sorted = new HashMap<>();

        if (transactions == null) {
            return new ArrayList<>();
        }

        for (Transaction t : transactions) {
            List<Transaction> list = sorted.get(t.getSku());

            if (list == null) {
                List<Transaction> tmp = new ArrayList<>();
                tmp.add(t);
                sorted.put(t.getSku(), tmp);

            } else {
                list.add(t);
                sorted.put(t.getSku(), list);
            }
        }

        List<Product> sortedNames = new ArrayList<>();
        sortedList(sorted, sortedNames);
        return sortedNames;
    }

    private void sortedList(HashMap<String, List<Transaction>> sorted, List<Product> products) {
        for (String key : sorted.keySet()) {
            Product n = new Product(key);
            for (Transaction p : sorted.get(key)) {
                n.add(p);
            }
            products.add(n);
        }
    }
}
