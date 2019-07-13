package com.example.subm1moviecatalogue.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.subm1moviecatalogue.R;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.repositories.FavMovieRepositories;
import com.example.subm1moviecatalogue.viewmodels.FavMovieViewModel;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dicoding on 1/9/2017.
 */


class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    private List<Movie> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    private RemoteViews rv;

    private FavMovieViewModel mMovieViewModel = new FavMovieViewModel();

    StackRemoteViewsFactory(Context context) {
        mContext = context;
    }


    @Override
    public void onCreate() {
        mWidgetItems.addAll(mMovieViewModel.getMovieArray(mContext));
    }

    @Override
    public void onDataSetChanged() {
        mWidgetItems.clear();
        if (!mWidgetItems.containsAll(mMovieViewModel.getMovieArray(mContext))){
            mWidgetItems.addAll(mMovieViewModel.getMovieArray(mContext));
        }
   }

    @Override
    public void onDestroy() {
        mWidgetItems.clear();

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        try {
            URL url = new URL("https://image.tmdb.org/t/p/w185"+mWidgetItems.get(position).getPosterPath());
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            rv.setImageViewBitmap(R.id.imageView, image);
        } catch(Exception e) {
            System.out.println(e);
        }
        rv.setTextViewText(R.id.tv_widget_item,mWidgetItems.get(position).getTitle());

        Bundle extras = new Bundle();
        extras.putInt(FavoriteWidget.EXTRA_ITEM,position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

}