package com.svalero.bookApp.model;

import static com.svalero.bookApp.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Room;

import com.svalero.bookApp.contract.AddFavBookContract;
import com.svalero.bookApp.db.AppDatabase;
import com.svalero.bookApp.domain.FavBook;

public class AddFavBookModel implements AddFavBookContract.Model {

    private Context context;

    public AddFavBookModel(Context context) {
        this.context = context;
    }

    @Override
    public boolean addFavBook(FavBook favBook) {
        try {
            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.favBookDao().insert(favBook); //insertamos en la BBDD
            return true;
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }
}
