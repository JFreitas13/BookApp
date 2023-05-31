package com.svalero.bookApp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.DeleteBookContract;
import com.svalero.bookApp.contract.DeleteBookstoreContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteBookstoreModel implements DeleteBookstoreContract.Model {


    @Override
    public void deleteBookstore(long id, OnDeleteBookstoreListener listener) {
        try {
            BookApiInterface bookApi = BookAPI.buildInstance();
            Call<Void> callBookstores = bookApi.deleteBookstores(id);
            Log.d("booksstore", "llamada desde el DeleteBookstoreModel");
            callBookstores.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("booksstores", "llamada desde el DeleteBookstoreModel OK");
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
