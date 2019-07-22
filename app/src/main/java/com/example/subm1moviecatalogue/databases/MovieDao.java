package com.example.subm1moviecatalogue.databases;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.Series;

import java.util.List;

@Dao
public interface MovieDao {

    //    Favotite Movie SQLlite ==============================================
    @Query("SELECT * FROM " + Movie.TABLE_NAME)
    List<Movie> getAllFavMovie();

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " where id LIKE :movieId")
    Movie getFavMovieById(long movieId);

    @Query("SELECT COUNT(*) from " + Movie.TABLE_NAME)
    int countFavMovie();

    @Insert
    void insertFavMovies(Movie... movies);

    @Delete
    void deleteFavMovie(Movie movie);

    @Query("DELETE FROM " + Movie.TABLE_NAME + " WHERE id LIKE :movieId")
    void deleteFavMovieById(long movieId);
//    Favotite Movie SQLlite END==============================================


    //    Favotite Movie Content Provider ==============================================
    @Query("SELECT * FROM " + Movie.TABLE_NAME)
    Cursor CPgetAllFavMovie();

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " where id LIKE :movieId")
    Cursor CPgetFavMovieById(long movieId);

    @Query("SELECT COUNT(*) from " + Movie.TABLE_NAME)
    int CPcountFavMovie();

    @Insert
    long CPinsertFavMovies(Movie... movies);

    @Delete
    void CPdeleteFavMovie(Movie movie);

    @Query("DELETE FROM " + Movie.TABLE_NAME + " WHERE id LIKE :movieId")
    int CPdeleteFavMovieById(long movieId);
//    Favotite Movie Content Provider END ==============================================




    @Query("SELECT * FROM series")
    List<Series> getAllFavSeries();

    @Query("SELECT * FROM series where id LIKE :seriesId")
    Series getFavSeriesById(long seriesId);

    @Query("SELECT COUNT(*) from series")
    int countFavSeries();

    @Insert
    void insertFavSeries(Series... series);

    @Delete
    void deleteFavSeries(Series series);

    @Query("DELETE FROM series WHERE id LIKE :seriesId")
    void deleteFavSeriesById(long seriesId);

}
