package com.ulj.slicky.javafakesocial.rest;

import android.support.annotation.Nullable;

import com.ulj.slicky.javafakesocial.model.person.PersonQuery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SlickyPC on 17.5.2017
 */
public interface PersonApi {

    // results=[int]
    // gender=[male|female]
    // page=[int]
    // seed=[string]
    // nat=[AU|BR|CA|CH|DE|DK|ES|FI|FR|GB|IE|IR|NL|NZ|TR|US|...]
    @GET("/api")
    Call<PersonQuery> getPerson(@Query("results") int results,
                                @Nullable
                                @Query("gender") String gender,
                                @Nullable
                                @Query("seed") String seed,
                                @Nullable
                                @Query("nat") String nationality,
                                @Nullable
                                @Query("page") String page);
}
