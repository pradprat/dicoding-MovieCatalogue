package com.example.subm1moviecatalogue.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.Series;

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
