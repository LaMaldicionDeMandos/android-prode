package org.pasut.prode.services.implementation;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by marcelo on 24/03/14.
 */
@Singleton
public class PreferencesService implements org.pasut.prode.services.PreferencesService {
    private final static String DB_NAME = "prode-preferences";

    private final SharedPreferences helper;
    private final Gson gson;

    @Inject
    public PreferencesService(Application app){
        this.helper = app.getSharedPreferences(DB_NAME, 4);
        gson = new Gson();
    }

    public <T> T get(String key, Class<T> clazz){
        String json = helper.getString(key, null);
        return gson.fromJson(json, clazz);
    }

    public <T> void put(String key, T value){
        String json = gson.toJson(value);
        SharedPreferences.Editor editor = helper.edit();
        editor.putString(key, json);
        editor.commit();
    }

    public boolean contain(String key){
        return helper.contains(key);
    }

    @Override
    public void delete(String key) {
        helper.edit().remove(key);
    }
}
