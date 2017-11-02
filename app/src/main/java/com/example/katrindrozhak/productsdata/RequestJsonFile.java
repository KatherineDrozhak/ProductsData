package com.example.katrindrozhak.productsdata;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RequestJsonFile<T> {

    public List<T> requestJsonObject(Activity activity, Type type, String fileName) throws IOException {
        List<T> objects = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(List.class, new AnnotatedDeserializer<T>())
                .create();
        String json = null;

        try {
            InputStream is = activity.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            objects = gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return objects;
    }

}
