package com.svalero.bookApp.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.ModifyBookstoreContract;
import com.svalero.bookApp.domain.Bookstore;
import com.svalero.bookApp.presenter.ModifyBookstorePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyBookstoreModel implements ModifyBookstoreContract.Model {

    private ModifyBookstorePresenter presenter;


    @Override
    public void modifyBookstore(long id, Bookstore bookstore, OnModifyBookstoreListener listener) {
        try {
            BookApiInterface bookApi = BookAPI.buildInstance();
            Call<Bookstore> callBookstoress = bookApi.modifyBookstore(id, bookstore);
            Log.d("bookstores", "llamada desde el ModifyBookstoreModel");
            callBookstoress.enqueue(new Callback<Bookstore>() {
                @Override
                public void onResponse(Call<Bookstore> call, Response<Bookstore> response) {
                    Log.d("bookstores", "llamada desde el ModifyBookstoreModel OK");
                    listener.onModifySuccess(bookstore);
                }

                @Override
                public void onFailure(Call<Bookstore> call, Throwable t) {
                    Log.d("booksstores", "llamada desde el DeleteBookstoreModel KO");
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
