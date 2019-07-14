package com.example.subm1moviecatalogue.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.subm1moviecatalogue.BuildConfig;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.repositories.MovieRepositories;

public class MovieViewModel extends ViewModel {

    private static final String API_KEY= BuildConfig.API_KEY;
    private MutableLiveData<MovieResult> mMovie;
    private MutableLiveData<MovieResult> mMovieSearch;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    public void init(){

    }

    public LiveData<MovieResult> getMovies(){
        setIsFetching(true);
        if (mMovie!=null){
            return mMovie;
        }
        MovieRepositories mMovieRepo = MovieRepositories.getInstance();
        mMovie = mMovieRepo.getMovie(API_KEY,"en-US");
        return mMovie;
    }

    public LiveData<MovieResult> getSearchMovies(String query){
        setIsFetching(true);
        if (mMovie!=null){
            return mMovieSearch;
        }
        MovieRepositories mMovieRepo = MovieRepositories.getInstance();
        mMovieSearch = mMovieRepo.getSearchMovie(API_KEY,"en-US",query);
        return mMovieSearch;
    }

    public void closing(){
        if (mMovie.getValue()!=null){
            setIsFetching(false);
        }
    }

    public void closingSearch(){
        if (mMovieSearch.getValue()!=null){
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
