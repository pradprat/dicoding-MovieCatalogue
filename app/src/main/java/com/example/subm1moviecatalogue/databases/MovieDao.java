package com.example.subm1moviecatalogue.databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.subm1moviecatalogue.models.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getAllFavMovie();

    @Query("SELECT * FROM movie where id LIKE :movieId")
    Movie getFavMovieById(long movieId);

    @Query("SELECT COUNT(*) from movie")
    int countFavMovie();

    @Insert
    void insertFavMovies(Movie... movies);

    @Delete
    void deleteFavMovie(Movie movie);

    @Query("DELETE FROM movie WHERE id LIKE :movieId")
    void deleteFavMovieById(long movieId);

}
