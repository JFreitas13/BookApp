package com.svalero.bookApp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.bookApp.domain.FavBook;

import java.util.List;

@Dao
public interface FavBookDao {

    @Query(value = "SELECT * FROM FavBook")
    List<FavBook> getAll();

    @Insert
    void insert(FavBook favBook);

    @Delete
    void delete(FavBook favBook);

    @Update
    void update(FavBook favBook);

}
