package com.example.subm1moviecatalogue.repositories;

import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.models.SeriesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("discover/movie")
    Call<MovieResult> getMovieList(@Query("api_key") String apiKey,
                                   @Query("language") String language);

    @GET("discover/tv")
    Call<SeriesResult> getSeriesList(@Query("api_key") String apiKey,
                                     @Query("language") String language);
    @GET("search/movie")
    Call<MovieResult> getSearchMovieList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("query") String query);
    @GET("search/tv")
    Call<SeriesResult> getSearchSeriesList(@Query("api_key") String apiKey,
                                         @Query("language") String language,
                                         @Query("query") String query);
}
