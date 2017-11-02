package com.example.katrindrozhak.productsdata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katrindrozhak.productsdata.R;
import com.example.katrindrozhak.productsdata.interfaces.OnItemClickedListener;
import com.example.katrindrozhak.productsdata.model.Product;
import com.example.katrindrozhak.productsdata.viewholders.TransactionViewHolder;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    private List<Product> mProducts;
    private Context context;
    private OnItemClickedListener onClick;

    public TransactionsAdapter(List<Product> products, Context context) {
        this.context = context;
        this.mProducts = products;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, null);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.show(context, mProducts, position);
        holder.container.setOnClickListener(view -> onClick.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return this.mProducts.size();
    }


    public void setOnClick(OnItemClickedListener onClick) {
        this.onClick = onClick;
    }
}

