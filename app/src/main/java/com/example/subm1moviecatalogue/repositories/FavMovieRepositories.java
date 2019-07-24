package com.example.subm1moviecatalogue.repositories;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
        try {
            for (int i = 0; i < cursor.getCount(); i++) {
                Movie movie;
                cursor.moveToPosition(i);
                Log.d("__awk", "doInBackground: " + cursor.getString(cursor.getColumnIndexOrThrow(Movie.COLUMN_title)));
                movie = Movie.getMovieFromCursor(cursor);
                movies.add(movie);
            }
        } catch (Exception e) {
            Log.d("__movieRepos", "CPgetFavMovie: " + e.getMessage());
        }

        setFavMovieData(movies);

        return favMovieData;
    }

    public void CPinsertFavMovie(Movie movie) {
        try {
            ContentValues values = Movie.getContentValuesFromMovie(movie);
            context.getContentResolver().insert(MovieContentProvider.URI_MOVIE, values);
            Log.d("__CP", "CPinsertFavMovie: " + values.get(Movie.COLUMN_title));
        } catch (Exception e) {
        }
    }

    public void CPdeleteFavMovie(Movie movie) {
        try {
            context.getContentResolver().delete(Uri.parse(MovieContentProvider.URI_MOVIE.toString() + "/" + movie.getId()), null, null);
            Log.d("__CP", "CPdeleteFavMovie: " + movie.getTitle());
        } catch (Exception e) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean CPisFavMovieExist(Movie movie) {
        try {
            Uri uri = Uri.parse(MovieContentProvider.URI_MOVIE.toString() + "/" + movie.getId());
            Log.d("__uri", "CPisFavMovieExist: " + uri);
            Cursor cursor = context.getContentResolver().query(uri, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();
            Movie movie2 = Movie.getMovieFromCursor(cursor);
            Log.d("__cursor", "CPisFavMovieExist: " + movie2.getId());
            return true;
        } catch (Exception e) {
            Log.d("__error", "CPisFavMovieExist: " + e.getMessage());
            return false;
        }
    }


}
