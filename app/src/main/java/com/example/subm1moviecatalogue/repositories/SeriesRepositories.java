package com.example.subm1moviecatalogue.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.subm1moviecatalogue.models.SeriesResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesRepositories {
    private MutableLiveData<SeriesResult> seriesData = new MutableLiveData<>();
    private MutableLiveData<SeriesResult> seriesSearchData = new MutableLiveData<>();
    private static SeriesRepositories seriesRepositories;
    public static SeriesRepositories getInstance(){
        if (seriesRepositories == null){
            seriesRepositories = new SeriesRepositories();
        }
        return seriesRepositories;
    }

    private void setSeriesData(SeriesResult seriesData) {
        this.seriesData.setValue(seriesData);
    }
    private void setSeriesSearchData(SeriesResult seriesData) {
        this.seriesSearchData.setValue(seriesData);
    }

    private MovieApi seriesApi;

    private SeriesRepositories(){
        seriesApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<SeriesResult> getSeries(String apiKey, String language){
        seriesApi.getSeriesList(apiKey, language).enqueue(new Callback<SeriesResult>() {
            @Override
            public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                if (response.isSuccessful()){
                    setSeriesData(response.body());
                }
            }
            @Override
            public void onFailure(Call<SeriesResult> call, Throwable t) {

            }
        });
        return seriesData;
    }
    public MutableLiveData<SeriesResult> getSearchSeries(String apiKey, String language,String query){
        seriesApi.getSearchSeriesList(apiKey, language,query).enqueue(new Callback<SeriesResult>() {
            @Override
            public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                if (response.isSuccessful()){
                    setSeriesSearchData(response.body());
                }
            }
            @Override
            public void onFailure(Call<SeriesResult> call, Throwable t) {

            }
        });
        return seriesSearchData;
    }
}
