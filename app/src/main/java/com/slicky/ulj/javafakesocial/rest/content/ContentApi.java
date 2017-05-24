package com.slicky.ulj.javafakesocial.rest.content;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by SlickyPC on 17.5.2017
 */
public interface ContentApi {

    @FormUrlEncoded
    @POST("/Random/RandomParagraph")
    Call<String> getContent(@Field("Subject1") String subject1,
                            @Field("Subject2") String subject2);
}
