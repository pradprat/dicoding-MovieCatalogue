package com.example.subm1moviecatalogue.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.subm1moviecatalogue.models.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase
            INSTANCE;
    public abstract MovieDao movieDao();
    public static MyDatabase getMyDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "movie-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
