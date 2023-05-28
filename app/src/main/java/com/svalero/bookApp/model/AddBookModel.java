package com.svalero.bookApp.model;


import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.AddBookContract;
import com.svalero.bookApp.domain.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookModel implements AddBookContract.Model {

    @Override
    public void addBook(Book book, OnRegisterBookListener listener) {
        try {
            BookApiInterface bookApi = BookAPI.buildInstance();
            Call<Book> callTasks = bookApi.addBook(book);
            Log.d("books", "llamada desde el addBookmodel");
            callTasks.enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    Book book = response.body();
                    listener.onRegisterSuccess(book);
                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
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

