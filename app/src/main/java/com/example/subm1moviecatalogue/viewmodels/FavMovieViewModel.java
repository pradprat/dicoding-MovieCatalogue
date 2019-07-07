package com.example.subm1moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.subm1moviecatalogue.BuildConfig;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.repositories.FavMovieRepositories;
import com.example.subm1moviecatalogue.repositories.MovieRepositories;

import java.util.ArrayList;

public class FavMovieViewModel extends ViewModel {
    Context context;
    private static final String API_KEY= BuildConfig.API_KEY;
    private MutableLiveData<ArrayList<Movie>> mMovie;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    public void init(Context context){
        this.context=context;
        setIsFetching(true);
        if (mMovie!=null){
            return;
        }
        FavMovieRepositories mFavMovieRepo = FavMovieRepositories.getInstance();
        mFavMovieRepo.setContext(this.context);
        mMovie = mFavMovieRepo.getFavMovie();

    }

    public void updateData(){
        FavMovieRepositories mFavMovieRepo = FavMovieRepositories.getInstance();
        mFavMovieRepo.setContext(context);
        mMovie = mFavMovieRepo.getFavMovie();
    }

    public LiveData<ArrayList<Movie>> getMovies(){
        return mMovie;
    }

    public void closing(){
        if (mMovie.getValue()!=null){
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
