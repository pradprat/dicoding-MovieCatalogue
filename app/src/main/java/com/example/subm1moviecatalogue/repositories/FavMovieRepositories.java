package com.example.subm1moviecatalogue.repositories;

import androidx.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.models.Movie;

import java.util.ArrayList;

public class FavMovieRepositories {
    Context context;
    private ArrayList<Movie> favMovieDataArray = new ArrayList<>(); // widget purposes
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

    public ArrayList<Movie> getFavMovieArray(){ //widget purposes
        MovieDatabase movieDatabase = new MovieDatabase(context);
        favMovieDataArray.addAll(movieDatabase.getAllFavMovie());

        return favMovieDataArray;
    }
}
