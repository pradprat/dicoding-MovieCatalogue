package com.example.subm1moviecatalogue.repositories;

import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.models.SeriesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie")
    Call<MovieResult> getMovieList(@Query("api_key") String apiKey,
                                   @Query("language") String language);

    @GET("tv")
    Call<SeriesResult> getSeriesList(@Query("api_key") String apiKey,
                                     @Query("language") String language);
}
