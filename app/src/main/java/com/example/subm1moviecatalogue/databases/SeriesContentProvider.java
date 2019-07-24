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

import com.example.subm1moviecatalogue.models.Series;

public class SeriesContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.subm1seriescatalogue.provider";
    public static final Uri URI_SERIES = Uri.parse(
            "content://" + AUTHORITY + "/" + Series.TABLE_NAME);

    private static final int CODE_SERIES_DIR = 1;

    private static final int CODE_SERIES_ITEM = 2;

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, Series.TABLE_NAME, CODE_SERIES_DIR);
        MATCHER.addURI(AUTHORITY, Series.TABLE_NAME + "/*", CODE_SERIES_ITEM);
    }

    public static final int getIndexColumn(Cursor cursor, String key) {
        try {
            int i = cursor.getColumnIndex(key);
            return i;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if (code == CODE_SERIES_DIR || code == CODE_SERIES_ITEM) {
            final Context context = getContext();
            if (context == null) {
                return null;
            }
            MovieDao seriesDao = MyDatabase.getMyDatabase(context).movieDao();
            final Cursor cursor;
            if (code == CODE_SERIES_DIR) {
                cursor = seriesDao.CPgetAllFavSeries();
            } else {
                cursor = seriesDao.CPgetFavSeriesById(ContentUris.parseId(uri));
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
            case CODE_SERIES_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Series.TABLE_NAME;
            case CODE_SERIES_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Series.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)) {
            case CODE_SERIES_DIR:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }
                final long id = MyDatabase.getMyDatabase(context).movieDao()
                        .CPinsertFavSeries(Series.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_SERIES_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)) {
            case CODE_SERIES_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            case CODE_SERIES_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                final int count = MyDatabase.getMyDatabase(context).movieDao()
                        .CPdeleteFavSeriesById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}