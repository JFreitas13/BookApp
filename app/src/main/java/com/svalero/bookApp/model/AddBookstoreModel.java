package com.svalero.bookApp.model;


import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.AddBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookstoreModel implements AddBookstoreContract.Model {

    @Override
    public void addBookstore(Bookstore bookstore, OnRegisterBookstoreListener listener) {
        try {
            BookApiInterface bookApi = BookAPI.buildInstance();
            Call<Bookstore> callBookstores = bookApi.addBookstore(bookstore);
            Log.d("bookstores", "llamada desde el addBookstoremodel");
            callBookstores.enqueue(new Callback<Bookstore>() {
                @Override
                public void onResponse(Call<Bookstore> call, Response<Bookstore> response) {
                    Bookstore bookstore = response.body();
                    listener.onRegisterSuccess(bookstore);
                }

                @Override
                public void onFailure(Call<Bookstore> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();

        }

    }
}

