package com.slicky.ulj.javafakesocial.rest.person;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class PersonService {
    public static final String URL = "https://randomuser.me/";
    private static PersonApi instance;

    public static synchronized PersonApi getInstance() {
        if (instance == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            instance = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(PersonApi.class);
        }
        return instance;
    }
}
