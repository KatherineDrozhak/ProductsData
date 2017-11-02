package com.example.katrindrozhak.productsdata.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.katrindrozhak.productsdata.R;
import com.example.katrindrozhak.productsdata.model.Product;
import com.example.katrindrozhak.productsdata.views.MainView;

import java.util.List;

public class TransactionViewHolder extends RecyclerView.ViewHolder implements MainView<List<Product>> {

    private TextView productName;
    private TextView transitionValue;
    public RelativeLayout container;

    public TransactionViewHolder(View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.component_layout);
        productName = container.findViewById(R.id.product);
        transitionValue = container.findViewById(R.id.transition);
    }

    @Override
    public void show(Context context, List<Product> products, int position) {
        productName.setText(products.get(position).getSku());
        transitionValue.setText(products.get(position).getCount() + context.getString(R.string.transactions_value));
    }
}
