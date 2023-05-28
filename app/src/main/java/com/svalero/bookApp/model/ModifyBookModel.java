package com.svalero.bookApp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.ModifyBookContract;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.presenter.ModifyBookPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyBookModel implements ModifyBookContract.Model {

    private ModifyBookPresenter presenter;

    @Override
    public void modifyBook(long id, Book book, OnModifyBookListener listener) {

        try {
            BookApiInterface bookApi = BookAPI.buildInstance();
            Call<Book> callBooks = bookApi.modifyBook(id, book);
            Log.d("books", "llamada desde el ModifyBookModel");
            callBooks.enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    Log.d("books", "llamada desde el ModifyBookModel OK");
                    listener.onModifySuccess(book);
                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
                    Log.d("books", "llamada desde el DeleteBookModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }

    }
}
