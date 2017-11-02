package com.example.katrindrozhak.productsdata.views;

import android.content.Context;

public interface MainView<T> {

    void show(Context context, T productNames, int position);
}
