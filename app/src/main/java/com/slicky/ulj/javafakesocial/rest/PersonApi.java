package com.slicky.ulj.javafakesocial.rest;

import android.support.annotation.Nullable;
import com.slicky.ulj.javafakesocial.model.person.PersonQuery;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SlickyPC on 17.5.2017
 */
public interface PersonApi {

    // page=3&results=10&seed=abc
    // nat=us,dk,fr,gb
    // AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IR, NL, NZ, TR, US
    @GET("/api")
    Call<PersonQuery> getPerson(
            @Query("results")int results,
            @Nullable
            @Query("gender") String gender,
            @Nullable
            @Query("seed") String seed,
            @Nullable
            @Query("nat") String nationality,
            @Nullable
            @Query("page") String page
    );
}
