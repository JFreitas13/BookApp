package com.svalero.bookApp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.bookApp.domain.FavBook;

@Database(entities = {FavBook.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract FavBookDao favBookDao();
}
