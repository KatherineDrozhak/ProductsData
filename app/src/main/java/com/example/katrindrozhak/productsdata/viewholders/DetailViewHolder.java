package com.example.katrindrozhak.productsdata.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.katrindrozhak.productsdata.CurrencySymbol;
import com.example.katrindrozhak.productsdata.R;
import com.example.katrindrozhak.productsdata.model.Transaction;
import com.example.katrindrozhak.productsdata.views.MainView;

import java.util.List;

public class DetailViewHolder extends RecyclerView.ViewHolder implements MainView<List<Transaction>> {
    private TextView currentCurrency;
    private TextView converterCurrency;

    public DetailViewHolder(View itemView) {
        super(itemView);
        currentCurrency = itemView.findViewById(R.id.sum);
        converterCurrency = itemView.findViewById(R.id.sum_gbp);
    }

    @Override
    public void show(Context context, List<Transaction> productNames, int position) {
        String currencyIcon = new CurrencySymbol().symbolFor(productNames.get(position).getCurrency());
        currentCurrency.setText(String.format("%s %s", currencyIcon, productNames.get(position).getAmount()));
        converterCurrency.setText(String.format("£ %s", productNames.get(position).getConvertedAmount()));
    }
}
