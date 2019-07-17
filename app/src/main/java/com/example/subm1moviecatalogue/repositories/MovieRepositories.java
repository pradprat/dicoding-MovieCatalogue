package com.example.subm1moviecatalogue.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositories {
    private ArrayList<Movie> movieDataArray = new ArrayList<>();
    private MutableLiveData<MovieResult> movieData = new MutableLiveData<>();
    private MutableLiveData<MovieResult> movieSearchData = new MutableLiveData<>();
    private static MovieRepositories movieRepositories;
    public static MovieRepositories getInstance(){
        if (movieRepositories == null){
            movieRepositories = new MovieRepositories();
        }
        return movieRepositories;
    }

    public void setMovieRelease(ArrayList<Movie> movieRelease) {
        this.movieDataArray = movieRelease;
    }

    private void setMovieData(MovieResult movieData) {
        this.movieData.setValue(movieData);
    }
    private void setMovieSearchData(MovieResult movieData) {
        this.movieSearchData.setValue(movieData);
    }

    private MovieApi movieApi;

    private MovieRepositories(){
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<MovieResult> getMovie(String apiKey, String language){
        movieApi.getMovieList(apiKey, language).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()){
                    setMovieData(response.body());
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
        return movieData;
    }

    public MutableLiveData<MovieResult> getSearchMovie(String apiKey, String language,String query){
        movieApi.getSearchMovieList(apiKey, language,query).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()){
                    setMovieSearchData(response.body());
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
        return movieSearchData;
    }

    public ArrayList<Movie> getMovieDataArray(String apiKey, String language) {

        Response<MovieResult> movieResultResponse = null;
        try {
            movieResultResponse = movieApi.getMovieList(apiKey, language).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieResultResponse.body().getResults();
    }
}
