package com.ulj.slicky.javafakesocial.rest;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by SlickyPC on 17.5.2017
 */
public class StringConverter extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (String.class == type)
            return new Converter<ResponseBody, String>() {
                @Override
                public String convert(@NonNull ResponseBody value) throws IOException {
                    return value.string();
                }
            };
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        if (String.class == type)
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(@NonNull String value) {
                    return RequestBody.create(MediaType.parse("text/plain"), value);
                }
            };
        return null;
    }
}
