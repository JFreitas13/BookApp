package com.svalero.bookApp.model;

import static com.svalero.bookApp.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.room.Room;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.api.Constants;
import com.svalero.bookApp.contract.DeleteBookContract;
import com.svalero.bookApp.contract.DeleteFavBookContract;
import com.svalero.bookApp.db.AppDatabase;
import com.svalero.bookApp.domain.FavBook;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteFavBookModel implements DeleteFavBookContract.Model {

    public Context context;

    public DeleteFavBookModel(Context context) {
        this.context = context;
    }

    @Override
    public boolean deleteFavBook(FavBook favBook) {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.favBookDao().delete(favBook);
        return true;
    }
}
