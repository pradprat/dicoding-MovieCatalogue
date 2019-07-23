package com.example.subm1moviecatalogue.repositories;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.subm1moviecatalogue.databases.MovieContentProvider;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MutableLiveData<ArrayList<Movie>> CPgetFavMovie() {

        ArrayList<Movie> movies = new ArrayList<>();
//        ambil dari database
        Cursor cursor = context.getContentResolver().query(MovieContentProvider.URI_MOVIE, null, null, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            Movie movie = new Movie();
            cursor.moveToPosition(i);
            Log.d("__awk", "doInBackground: " + cursor.getString(cursor.getColumnIndexOrThrow(Movie.COLUMN_title)));
            movie = Movie.getMovieFromCursor(cursor);
            movies.add(movie);
        }

        setFavMovieData(movies);

        return favMovieData;
    }


}
