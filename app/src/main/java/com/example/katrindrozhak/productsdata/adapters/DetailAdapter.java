package com.example.katrindrozhak.productsdata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katrindrozhak.productsdata.R;
import com.example.katrindrozhak.productsdata.model.Transaction;
import com.example.katrindrozhak.productsdata.viewholders.DetailViewHolder;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {

    private Context context;
    private List<Transaction> transactions;

    public DetailAdapter(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, null);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        holder.show(context, transactions, position);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }
}
