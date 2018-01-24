package com.venti.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    /**
     * 转换为Json对象
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(o);
    }
}
