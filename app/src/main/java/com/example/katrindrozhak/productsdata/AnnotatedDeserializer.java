package com.example.katrindrozhak.productsdata;

import com.example.katrindrozhak.productsdata.interfaces.JsonRequired;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class AnnotatedDeserializer<T> implements JsonDeserializer<List<T>> {

    public List<T> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Type valueType = ((ParameterizedType) type).getActualTypeArguments()[0];

        List<T> list = new ArrayList<>();
        for (JsonElement item : je.getAsJsonArray()) {
            try {
                list.add(deserializeRequired(item, valueType));
            } catch (JsonParseException e) {
                throw e;
            }
        }
        return list;
    }

    private T deserializeRequired(JsonElement element, Type type) throws JsonParseException {
        T pojo = new Gson().fromJson(element, type);

        Field[] fields = pojo.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getAnnotation(JsonRequired.class) != null) {
                try {
                    f.setAccessible(true);
                    if (f.get(pojo) == null) {
                        throw new JsonParseException("Missing field in JSON: " + f.getName());
                    }
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return pojo;
    }
}
