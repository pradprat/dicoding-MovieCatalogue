package com.example.subm1moviecatalogue.databases;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.subm1moviecatalogue.models.Movie;

public class MovieContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.example.subm1moviecatalogue.provider";
    public static final Uri URI_MOVIE = Uri.parse(
            "content://" + AUTHORITY + "/" + Movie.TABLE_NAME);

    private static final int CODE_MOVIE_DIR = 1;

    private static final int CODE_MOVIE_ITEM = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Movie.TABLE_NAME, CODE_MOVIE_DIR);
        MATCHER.addURI(AUTHORITY, Movie.TABLE_NAME + "/*", CODE_MOVIE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if (code == CODE_MOVIE_DIR || code == CODE_MOVIE_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            MovieDao movie = MyDatabase.getMyDatabase(context).movieDao();
            final Cursor cursor;
            if (code == CODE_MOVIE_DIR) {
                cursor = movie.CPgetAllFavMovie();
            } else {
                cursor = movie.CPgetFavMovieById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_MOVIE_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Movie.TABLE_NAME;
            case CODE_MOVIE_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Movie.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        switch (MATCHER.match(uri)) {
//            case CODE_MOVIE_DIR:
//                final Context context = getContext();
//                if (context == null) {
//                    return null;
//                }
//                final long id = MyDatabase.getMyDatabase(context).movieDao()
//                        .insertFavMovies(Movie.fromContentValues(values));
//                context.getContentResolver().notifyChange(uri, null);
//                return ContentUris.withAppendedId(uri, id);
//
//            case CODE_MOVIE_ITEM:
//                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
//            default:
//                throw new IllegalArgumentException("Unknown URI: " + uri);
//        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
