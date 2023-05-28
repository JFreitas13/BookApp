package com.svalero.bookApp.model;

import android.content.Context;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.BookstoreListContract;
import com.svalero.bookApp.domain.Bookstore;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookstoreListModel implements BookstoreListContract.Model {

    private Context context;

//    public BookstoreListModel(Context context) {
//        this.context = context;
//    }

    @Override
    public void loadAllBookstores(OnLoadBookstoreListener listener) {

        BookApiInterface bookApi = BookAPI.buildInstance();
        Call<List<Bookstore>> callBookstores = bookApi.getBookstores();
        Log.d("bookstore", "llamada desde el ListBookstoreModel");
        callBookstores.enqueue(new Callback<List<Bookstore>>() {
            @Override
            public void onResponse(Call<List<Bookstore>> call, Response<List<Bookstore>> response) {
                Log.d("bookstore", "llamada desde el model OK");
                List<Bookstore> bookstores = response.body();
                listener.onLoadBookstoreSuccess(bookstores);
            }

            @Override
            public void onFailure(Call<List<Bookstore>> call, Throwable t) {
                Log.d("booksstore", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadBookstoreError(message);

            }
        });

    }
}
