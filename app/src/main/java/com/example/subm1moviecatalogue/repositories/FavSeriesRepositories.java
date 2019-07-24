package com.example.subm1moviecatalogue.repositories;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.databases.SeriesContentProvider;
import com.example.subm1moviecatalogue.models.Series;

import java.util.ArrayList;

public class FavSeriesRepositories {
    Context context;
    private MutableLiveData<ArrayList<Series>> favSeriesData = new MutableLiveData<>();
    private static FavSeriesRepositories favSeriesRepositories;
    public static FavSeriesRepositories getInstance(){
        if (favSeriesRepositories == null){
            favSeriesRepositories = new FavSeriesRepositories();
        }
        return favSeriesRepositories;
    }

    public void setFavSeriesData(ArrayList<Series> seriesData) {
        this.favSeriesData.setValue(seriesData);
    }

    public void setContext(Context context){
        this.context=context;
    }

    public FavSeriesRepositories(){
    }

    public void updateFavSeries(){
        MovieDatabase seriesDatabase = new MovieDatabase(context);
        ArrayList<Series> seriess = new ArrayList<>();
        seriess.addAll(seriesDatabase.getAllFavSeries());

        setFavSeriesData(seriess);
    }

    public MutableLiveData<ArrayList<Series>> getFavSeries(){
//        ambil dari database
        MovieDatabase seriesDatabase = new MovieDatabase(context);
        ArrayList<Series> seriess = new ArrayList<>();
        seriess.addAll(seriesDatabase.getAllFavSeries());

        setFavSeriesData(seriess);

        return favSeriesData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MutableLiveData<ArrayList<Series>> CPgetFavSeries() {

        ArrayList<Series> seriess = new ArrayList<>();
//        ambil dari database
        Cursor cursor = context.getContentResolver().query(SeriesContentProvider.URI_SERIES, null, null, null);
        try {
            for (int i = 0; i < cursor.getCount(); i++) {
                Series series;
                cursor.moveToPosition(i);
                Log.d("__awk", "doInBackground: " + cursor.getString(cursor.getColumnIndexOrThrow(Series.COLUMN_name)));
                series = Series.getSeriesFromCursor(cursor);
                seriess.add(series);
            }
        } catch (Exception e) {
            Log.d("__seriesRepos", "CPgetFavSeries: " + e.getMessage());
        }

        setFavSeriesData(seriess);

        return favSeriesData;
    }

    public void CPinsertFavSeries(Series series) {
        try {
            ContentValues values = Series.getContentValuesFromSeries(series);
            context.getContentResolver().insert(SeriesContentProvider.URI_SERIES, values);
            Log.d("__CP", "CPinsertFavSeries: " + values.get(Series.COLUMN_name));
        } catch (Exception e) {
        }
    }

    public void CPdeleteFavSeries(Series series) {
        try {
            context.getContentResolver().delete(Uri.parse(SeriesContentProvider.URI_SERIES.toString() + "/" + series.getId()), null, null);
            Log.d("__CP", "CPdeleteFavSeries: " + series.getName());
        } catch (Exception e) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean CPisFavSeriesExist(Series series) {
        try {
            Uri uri = Uri.parse(SeriesContentProvider.URI_SERIES.toString() + "/" + series.getId());
            Log.d("__uri", "CPisFavSeriesExist: " + uri);
            Cursor cursor = context.getContentResolver().query(uri, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            Series series2 = Series.getSeriesFromCursor(cursor);
            Log.d("__cursor", "CPisFavSeriesExist: " + series2.getId());
            return true;
        } catch (Exception e) {
            Log.d("__error", "CPisFavSeriesExist: " + e.getMessage());
            return false;
        }
    }
}
