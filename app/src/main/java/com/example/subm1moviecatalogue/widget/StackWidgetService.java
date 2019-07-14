package com.example.subm1moviecatalogue.widget;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;

/**
 * Created by dicoding on 1/9/2017.
 */

public class StackWidgetService extends RemoteViewsService {
    MovieViewModel mMovieViewModel;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}