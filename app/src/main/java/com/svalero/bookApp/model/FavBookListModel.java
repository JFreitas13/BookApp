package com.svalero.bookApp.model;

import static com.svalero.bookApp.db.Constants.DATABASE_NAME;

import android.content.Context;
import androidx.room.Room;

import com.svalero.bookApp.contract.FavBookListContract;
import com.svalero.bookApp.db.AppDatabase;
import com.svalero.bookApp.domain.FavBook;

import java.util.List;


public class FavBookListModel implements FavBookListContract.Model {

    private Context context;

    public FavBookListModel(Context context) {
        this.context = context;
    }

    @Override
    public List<FavBook> loadAllFavBook() {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        return db.favBookDao().getAll();
    }
}
