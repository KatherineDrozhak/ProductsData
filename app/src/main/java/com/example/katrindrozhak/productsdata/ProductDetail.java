package com.example.katrindrozhak.productsdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.katrindrozhak.productsdata.adapters.DetailAdapter;
import com.example.katrindrozhak.productsdata.model.Product;
import com.example.katrindrozhak.productsdata.model.Rate;
import com.example.katrindrozhak.productsdata.model.Transaction;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.parceler.Parcels;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductDetail extends AppCompatActivity {

    public static final String GBP = CurrencyEnum.GBP.getCurrency();

    private List<Rate> rates = new ArrayList<>();
    private TextView productTransaction;
    private TextView transactionSum;
    private RecyclerView recyclerView;
    private RelativeLayout error;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_product);

        product = Parcels.unwrap(getIntent().getParcelableExtra(MainActivity.KEY_PRODUCT_NAME));

        setupUI();

        setupRates();

        presentTransactions();
    }

    private String sum(List<Transaction> transactions) {
        double sum = 0;
        for (Transaction t : transactions) {
            sum += t.getConvertedAmount();
        }

        NumberFormat formatter = NumberFormat.getNumberInstance();
        return formatter.format(sum);
    }

    private void setupUI() {
        productTransaction = findViewById(R.id.product_transaction);
        transactionSum = findViewById(R.id.all_sum);
        recyclerView = findViewById(R.id.detail_container);
        error = findViewById(R.id.not_available_layout);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupRates() {
        try {
            Type typeToken = new TypeToken<List<Rate>>() {
            }.getType();
            rates = new RequestJsonFile<Rate>().requestJsonObject(this, typeToken, "rates.json");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonParseException ex) {
            ex.printStackTrace();
        }

        productTransaction.setText(String.format("%s%s", getString(R.string.transaction_for_product), product.getSku()));
    }

    private void presentTransactions() {
        List<Transaction> transactions = product.getTransactions();
        convertedCurrency(transactions);
        validTransaction(transactions);

        transactionSum.setText(String.format("%s%s", getString(R.string.all_sum), sum(transactions)));

        DetailAdapter adapter = new DetailAdapter(ProductDetail.this, transactions);
        recyclerView.setAdapter(adapter);
    }

    private void validTransaction(List<Transaction> transactions) {

        if (transactions == null || transactions.isEmpty() || rates == null || rates.isEmpty()) {
            error.setVisibility(View.VISIBLE);
        } else {
            error.setVisibility(View.INVISIBLE);
        }
    }

    private void convertedCurrency(List<Transaction> transactions) {

        CurrencyConverter currencyConverter = new CurrencyConverter(rates);

        for (int i = 0; i < transactions.size(); i++) {
            String curr = transactions.get(i).getCurrency();
            double amount = Double.parseDouble(transactions.get(i).getAmount());
            if (!curr.equals(GBP)) {
                Double value = currencyConverter.convert(amount, curr, GBP);
                transactions.get(i).setConvertedAmount(value);
            } else {
                transactions.get(i).setConvertedAmount(amount);
            }
        }

    }

}
