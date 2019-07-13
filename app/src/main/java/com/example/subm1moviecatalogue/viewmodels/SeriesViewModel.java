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
    private MutableLiveData<SeriesResult> mSeriesSearch;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    public LiveData<SeriesResult> getSeriess(){
        setIsFetching(true);
        if (mSeries!=null){
            return mSeries;
        }
        SeriesRepositories mSeriesRepo = SeriesRepositories.getInstance();
        mSeries = mSeriesRepo.getSeries(API_KEY,"en-US");
        return mSeries;
    }

    public LiveData<SeriesResult> getSearchSeriess(String query){
        setIsFetching(true);
        if (mSeries!=null){
            return mSeriesSearch;
        }
        SeriesRepositories mSeriesRepo = SeriesRepositories.getInstance();
        mSeriesSearch = mSeriesRepo.getSearchSeries(API_KEY,"en-US",query);
        return mSeriesSearch;
    }

    public void closing(){
        if (mSeries.getValue()!=null){
            setIsFetching(false);
        }
    }
    public void closingSearch(){
        if (mSeriesSearch.getValue()!=null){
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
