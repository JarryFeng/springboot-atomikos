package com.jarry.handler;

/**
 * Created by jarry on 2018/7/4.
 */
public class DataSourceHandler {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setDataSource(String key){
        context.set(key);
    }

    public static String getDataSource(){
        return context.get();
    }

    public static void removeDataSource(){
        context.remove();
    }
}
