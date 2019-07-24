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

    //    Favorite Movie SQLlite ==============================================
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
//    Favorite Movie SQLlite END==============================================


    //    Favorite Movie Content Provider ==============================================
    @Query("SELECT * FROM " + Movie.TABLE_NAME)
    Cursor CPgetAllFavMovie();

    @Query("SELECT * FROM " + Movie.TABLE_NAME + " where id LIKE :movieId")
    Cursor CPgetFavMovieById(long movieId);

    @Query("SELECT COUNT(*) from " + Movie.TABLE_NAME)
    int CPcountFavMovie();

    @Insert
    long CPinsertFavMovies(Movie movie);

    @Delete
    void CPdeleteFavMovie(Movie movie);

    @Query("DELETE FROM " + Movie.TABLE_NAME + " WHERE id LIKE :movieId")
    int CPdeleteFavMovieById(long movieId);
//    Favorite Movie Content Provider END ==============================================


    //    Favorite Series SQLlite ==============================================
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
    //    Favorite Series SQLlite END==============================================


    //    Favorite Series Content Provider ==============================================
    @Query("SELECT * FROM series")
    Cursor CPgetAllFavSeries();

    @Query("SELECT * FROM series where id LIKE :seriesId")
    Cursor CPgetFavSeriesById(long seriesId);

    @Query("SELECT COUNT(*) from series")
    int CPcountFavSeries();

    @Insert
    long CPinsertFavSeries(Series series);

    @Delete
    void CPdeleteFavSeries(Series series);

    @Query("DELETE FROM series WHERE id LIKE :seriesId")
    int CPdeleteFavSeriesById(long seriesId);
    //    Favorite Series Content Provider END==============================================

}
