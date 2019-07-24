package com.example.subm1moviecatalogue.viewmodels;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.os.Build;

import com.example.subm1moviecatalogue.BuildConfig;
import com.example.subm1moviecatalogue.models.Series;
import com.example.subm1moviecatalogue.repositories.FavSeriesRepositories;

import java.util.ArrayList;

public class FavSeriesViewModel extends ViewModel {
    Context context;
    private static final String API_KEY= BuildConfig.API_KEY;
    private MutableLiveData<ArrayList<Series>> mSeries;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init(Context context){
        this.context=context;
        setIsFetching(true);
        if (mSeries!=null){
            return;
        }
        FavSeriesRepositories mFavSeriesRepo = FavSeriesRepositories.getInstance();
        mFavSeriesRepo.setContext(this.context);
//        mSeries = mFavSeriesRepo.getFavSeries();
        mSeries = mFavSeriesRepo.CPgetFavSeries();
    }

    public void updateData(){
        FavSeriesRepositories mFavSeriesRepo = FavSeriesRepositories.getInstance();
        mFavSeriesRepo.setContext(context);
        mSeries = mFavSeriesRepo.getFavSeries();
    }

    public LiveData<ArrayList<Series>> getSeries(){
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
