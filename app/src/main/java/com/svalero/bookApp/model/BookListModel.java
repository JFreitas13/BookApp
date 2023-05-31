package com.svalero.bookApp.model;

import android.content.Context;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.BookListContract;
import com.svalero.bookApp.domain.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListModel implements BookListContract.Model {

    private Context context;

    public BookListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllBooks(OnLoadBookListener listener) {

        BookApiInterface bookApi = BookAPI.buildInstance();
        Call<List<Book>> callBooks = bookApi.getBooks();
        Log.d("books", "llamada desde el model");
        callBooks.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                Log.d("books", "llamada desde el model OK");
                List<Book> books = response.body();
                listener.onLoadBooksSuccess(books);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("books", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadBooksError(message);

            }
        });

    }
}
