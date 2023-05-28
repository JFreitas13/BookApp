package com.svalero.bookApp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.DeleteBookContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteBookModel implements DeleteBookContract.Model {


    @Override
    public void deleteBook(long bookId, OnDeleteBookListener listener) {
        try {
            BookApiInterface bookApi = BookAPI.buildInstance();
            Call<Void> callBooks = bookApi.deleteBooks(bookId);
            Log.d("books", "llamada desde el DeleBookModel");
            callBooks.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("books", "llamada desde el DeleteBookModel OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("books", "llamada desde el DeleteBookModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeleteError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }

    }
}
