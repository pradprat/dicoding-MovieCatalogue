package com.example.subm1moviecatalogue.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.subm1moviecatalogue.models.SeriesResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesRepositories {
    MutableLiveData<SeriesResult> seriesData = new MutableLiveData<>();
    private static SeriesRepositories seriesRepositories;
    public static SeriesRepositories getInstance(){
        if (seriesRepositories == null){
            seriesRepositories = new SeriesRepositories();
        }
        return seriesRepositories;
    }

    public void setSeriesData(SeriesResult seriesData) {
        this.seriesData.setValue(seriesData);
    }

    private MovieApi seriesApi;

    public SeriesRepositories(){
        seriesApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<SeriesResult> getSeries(String apiKey, String language){
        seriesApi.getSeriesList(apiKey, language).enqueue(new Callback<SeriesResult>() {
            @Override
            public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                if (response.isSuccessful()){
                    setSeriesData(response.body());
                }else{
                    Log.d("__respon", response.message());
                }
            }
            @Override
            public void onFailure(Call<SeriesResult> call, Throwable t) {
                Log.d("__error",t.getMessage());
            }
        });
        return seriesData;
    }
}
