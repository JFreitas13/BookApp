package com.svalero.bookApp.model;

import static com.svalero.bookApp.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.svalero.bookApp.api.BookAPI;
import com.svalero.bookApp.api.BookApiInterface;
import com.svalero.bookApp.contract.BookListContract;
import com.svalero.bookApp.contract.FavBookListContract;
import com.svalero.bookApp.db.AppDatabase;
import com.svalero.bookApp.domain.Book;
import com.svalero.bookApp.domain.FavBook;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
