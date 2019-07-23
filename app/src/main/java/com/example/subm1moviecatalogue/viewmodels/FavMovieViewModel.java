package com.example.subm1moviecatalogue.viewmodels;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.os.Build;

import com.example.subm1moviecatalogue.BuildConfig;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.repositories.FavMovieRepositories;

import java.util.ArrayList;

public class FavMovieViewModel extends ViewModel {
    private Context context;
    private static final String API_KEY= BuildConfig.API_KEY;
    private ArrayList<Movie> mMovieArray = new ArrayList<>();
    private MutableLiveData<ArrayList<Movie>> mMovie;
    private MutableLiveData<Boolean> isFetching = new MutableLiveData<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void init(Context context){
        this.context=context;
        setIsFetching(true);
        if (mMovie!=null){
            return;
        }
        FavMovieRepositories mFavMovieRepo = FavMovieRepositories.getInstance();
        mFavMovieRepo.setContext(this.context);
//        mMovie = mFavMovieRepo.getFavMovie();
        mMovie = mFavMovieRepo.CPgetFavMovie();

    }

    public void updateData(){
        FavMovieRepositories mFavMovieRepo = FavMovieRepositories.getInstance();
        mFavMovieRepo.setContext(context);
        mMovie = mFavMovieRepo.getFavMovie();
    }

    public LiveData<ArrayList<Movie>> getMovies(){
        return mMovie;
    }

    public ArrayList<Movie> getMovieArray(Context context){
        this.context=context;
        FavMovieRepositories mFavMovieRepo = FavMovieRepositories.getInstance();
        mFavMovieRepo.setContext(this.context);
        mMovieArray.addAll(mFavMovieRepo.getFavMovieArray());
        return mMovieArray;
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
