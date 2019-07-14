package com.example.subm1moviecatalogue.databases;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import android.content.Context;

import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.Series;

@Database(entities = {Movie.class , Series.class}, version = 2)
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

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    +"ADD COLUMN address TEXT");

        }
    };
}
