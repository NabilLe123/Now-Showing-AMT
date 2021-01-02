package com.nabil.amt.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nabil.amt.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "amt_db";

    public abstract MovieDao movieDao();
}