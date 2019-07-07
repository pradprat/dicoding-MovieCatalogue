package com.example.subm1moviecatalogue.databases;

import android.content.Context;
import android.util.Log;

import com.example.subm1moviecatalogue.models.Movie;

import java.util.List;

public class MovieDatabase {
    private MyDatabase db;

    public MovieDatabase(Context context){
        this.db = MyDatabase.getMyDatabase(context);
    }

    public void insertFavMovies(Movie movie) {
        db.movieDao().insertFavMovies(movie);
        Log.d("__database", movie.getTitle()+" ditambahkan");
    }

    public List<Movie> getAllFavMovie() {
        List<Movie> movies = db.movieDao().getAllFavMovie();
        for (Movie movie : movies){
            Log.d("__database", "Sudah ada di database = "+movie.getTitle());
        }
        return movies;
    }

    public void deleteFavMovie(Movie movie){
        db.movieDao().deleteFavMovie(movie);
        Log.d("__database", movie.getTitle()+" dihapus");
    }

    public void deleteFavMovieById(long movieId){
        Movie movie = db.movieDao().getFavMovieById(movieId);
        db.movieDao().deleteFavMovieById(movieId);
        Log.d("__database", movie.getTitle()+" dihapus");
    }

    public boolean isFavMovieExist(Movie movie){
        return db.movieDao().getFavMovieById(movie.getId()) != null;
    }

}
