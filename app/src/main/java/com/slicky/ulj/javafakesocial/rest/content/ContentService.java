package com.slicky.ulj.javafakesocial.rest.content;

import retrofit2.Retrofit;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class ContentService {
    public static final String URL = "http://watchout4snakes.com/wo4snakes/";

    private static ContentApi instance;

    public static synchronized ContentApi getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(new StringConverter())
                    .build()
                    .create(ContentApi.class);
        }
        return instance;
    }
}
