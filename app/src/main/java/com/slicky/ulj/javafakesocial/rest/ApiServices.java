package com.slicky.ulj.javafakesocial.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by SlickyPC on 6.6.2017
 */
public class ApiServices {

    public static final String PERSON_URL = "https://randomuser.me/";
    public static final String CONTENT_URL = "http://watchout4snakes.com/wo4snakes/";

    private static PersonApi personApi;
    private static ContentApi contentApi;

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build();

    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public static synchronized PersonApi personApi() {
        if (personApi == null) {
            personApi = new Retrofit.Builder()
                    .baseUrl(PERSON_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build()
                    .create(PersonApi.class);
        }
        return personApi;
    }

    public static synchronized ContentApi contentApi() {
        if (contentApi == null) {
            contentApi = new Retrofit.Builder()
                    .baseUrl(CONTENT_URL)
                    .addConverterFactory(new StringConverter())
                    .client(okHttpClient)
                    .build()
                    .create(ContentApi.class);
        }
        return contentApi;
    }
}
