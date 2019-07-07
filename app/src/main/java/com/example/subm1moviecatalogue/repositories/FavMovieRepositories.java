package com.example.subm1moviecatalogue.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.subm1moviecatalogue.FavoritesActivity;
import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavMovieRepositories {
    Context context;
    private MutableLiveData<ArrayList<Movie>> favMovieData = new MutableLiveData<>();
    private static FavMovieRepositories favMovieRepositories;
    public static FavMovieRepositories getInstance(){
        if (favMovieRepositories == null){
            favMovieRepositories = new FavMovieRepositories();
        }
        return favMovieRepositories;
    }

    public void setFavMovieData(ArrayList<Movie> movieData) {
        this.favMovieData.setValue(movieData);
    }

    public void setContext(Context context){
        this.context=context;
    }

    public FavMovieRepositories(){
    }

    public void updateFavMovie(){
        MovieDatabase movieDatabase = new MovieDatabase(context);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.addAll(movieDatabase.getAllFavMovie());

        setFavMovieData(movies);
    }

    public MutableLiveData<ArrayList<Movie>> getFavMovie(){
//        ambil dari database
        MovieDatabase movieDatabase = new MovieDatabase(context);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.addAll(movieDatabase.getAllFavMovie());

        setFavMovieData(movies);

        return favMovieData;
    }
}
