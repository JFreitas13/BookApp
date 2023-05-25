package com.svalero.bookApp.api;

import static com.svalero.bookApp.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookAPI {

    //instanciar libreria Retrofit y consumir API
    public static BookApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(BookApiInterface.class);
    }

}
