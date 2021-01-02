package com.nabil.amt.database;

import android.content.Context;

import androidx.room.Room;

public class RoomDbInstance {
    private static AppDatabase appDatabase = null;

    public static AppDatabase getAppDb(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
}
