package com.example.subm1moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.subm1moviecatalogue.BuildConfig;
import com.example.subm1moviecatalogue.models.SeriesResult;
import com.example.subm1moviecatalogue.repositories.SeriesRepositories;

public class SeriesViewModel extends ViewModel {

    private static final String API_KEY= BuildConfig.API_KEY;
    private MutableLiveData<SeriesResult> mSeries;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    public void init(){
        setIsFetching(true);
        if (mSeries!=null){
            return;
        }
        SeriesRepositories mSeriesRepo = SeriesRepositories.getInstance();
        mSeries = mSeriesRepo.getSeries(API_KEY,"en-US");

    }

    public LiveData<SeriesResult> getSeriess(){
        return mSeries;
    }

    public void closing(){
        if (mSeries.getValue()!=null){
            setIsFetching(false);
        }
    }

    public LiveData<Boolean> getIsFetching(){
        return isFetching;
    }

    private void setIsFetching(boolean isFetching){
        this.isFetching.postValue(isFetching);
    }
}
