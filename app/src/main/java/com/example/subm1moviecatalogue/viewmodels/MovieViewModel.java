package com.example.subm1moviecatalogue.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.subm1moviecatalogue.BuildConfig;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.repositories.MovieRepositories;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY= BuildConfig.API_KEY;
    private MutableLiveData<MovieResult> mMovie;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    public void init(){
        setIsFetching(true);
        if (mMovie!=null){
            return;
        }
        MovieRepositories mMovieRepo = MovieRepositories.getInstance();
        mMovie = mMovieRepo.getMovie(API_KEY,"en-US");

    }

    public LiveData<MovieResult> getMovies(){
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
