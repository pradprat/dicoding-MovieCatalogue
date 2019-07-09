package com.example.subm1moviecatalogue.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.subm1moviecatalogue.models.MovieResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositories {
    private MutableLiveData<MovieResult> movieData = new MutableLiveData<>();
    private static MovieRepositories movieRepositories;
    public static MovieRepositories getInstance(){
        if (movieRepositories == null){
            movieRepositories = new MovieRepositories();
        }
        return movieRepositories;
    }

    private void setMovieData(MovieResult movieData) {
        this.movieData.setValue(movieData);
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
                    setMovieData(response.body());
                }
            }
            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {

            }
        });
        return movieData;
    }
}
