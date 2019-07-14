package com.example.subm1moviecatalogue.repositories;

import androidx.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.subm1moviecatalogue.databases.MovieDatabase;
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
}
