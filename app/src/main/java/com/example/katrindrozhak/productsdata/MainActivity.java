package com.example.katrindrozhak.productsdata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.katrindrozhak.productsdata.adapters.TransactionsAdapter;
import com.example.katrindrozhak.productsdata.interfaces.OnItemClickedListener;
import com.example.katrindrozhak.productsdata.model.Product;
import com.example.katrindrozhak.productsdata.model.Transaction;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.parceler.Parcels;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickedListener {

    public static final String KEY_PRODUCT_NAME = "name";

    private List<Transaction> transactions = new ArrayList<>();
    private TextView productName;
    private TextView transactionValue;
    private RecyclerView recyclerView;
    private RelativeLayout error;
    private ProductCollection productCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        productName.setText(R.string.product_name);
        transactionValue.setText(R.string.transactions);

        setupTransactions();

        validTransaction();
        setupAdapter();
    }

    private void setupAdapter() {
        productCollection = new ProductCollection(transactions);

        TransactionsAdapter adapter = new TransactionsAdapter(productCollection.getProducts(), MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.setOnClick(this);

    }

    private void setupTransactions() {
        try {
            Type typeToken = new TypeToken<List<Transaction>>() {
            }.getType();
            transactions = new RequestJsonFile<Transaction>()
                    .requestJsonObject(this, typeToken, "transactions.json");
            Log.d("tag", "tag");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonParseException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        List<Product> products = productCollection.getProducts();
        sendIntent(products, position);

    }

    private void setupUI() {
        productName = findViewById(R.id.product);
        transactionValue = findViewById(R.id.transition);
        recyclerView = findViewById(R.id.container);
        error = findViewById(R.id.not_available_layout);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void validTransaction() {

        if (transactions == null || transactions.isEmpty()) {
            error.setVisibility(View.VISIBLE);
        } else {
            error.setVisibility(View.INVISIBLE);
        }
    }

    private void sendIntent(List<Product> products, int position) {
        final Product product = products.get(position);
        Parcelable selected = Parcels.wrap(product);
        Intent intent = new Intent(MainActivity.this, ProductDetail.class);
        intent.putExtra(KEY_PRODUCT_NAME, selected);
        startActivity(intent);
    }
}
