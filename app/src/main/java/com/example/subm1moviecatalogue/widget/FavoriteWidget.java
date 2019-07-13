package com.example.subm1moviecatalogue.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.subm1moviecatalogue.MovieDetailActivity;
import com.example.subm1moviecatalogue.R;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.viewmodels.FavMovieViewModel;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;

import java.util.ArrayList;

import static java.lang.System.in;

/**
 * Implementation of App Widget functionality.
 */
public class FavoriteWidget extends AppWidgetProvider {
    ArrayList<Movie> movies = new ArrayList<>();

    private static final String TOAST_ACTION = "com.example.subm1moviecatalogue.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.subm1moviecatalogue.EXTRA_ITEM";

    /*
    Update widget berdasarkan id widget-nya di home screen
     */
    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        Intent intent = new Intent(context, StackWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.favorite_widget);
        views.setRemoteAdapter(R.id.stack_view, intent);
        views.setEmptyView(R.id.stack_view, R.id.empty_view);

        Intent toastIntent = new Intent(context, FavoriteWidget.class);
        toastIntent.setAction(FavoriteWidget.TOAST_ACTION);
        toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    /*
    Update widget
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    /*
    Gunakan onReceive untuk menerima broadcast
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction() != null) {
            if (intent.getAction().equals(TOAST_ACTION)) {
                int position = intent.getIntExtra(EXTRA_ITEM, 0);
                FavMovieViewModel favMovieViewModel = new FavMovieViewModel();
                Movie movie = favMovieViewModel.getMovieArray(context).get(position);

                Intent intent1 = new Intent(context, MovieDetailActivity.class);
                intent1.putExtra("type","Movie");
                intent1.putExtra("PARCEL",movie);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (movie!=null){
                    Toast.makeText(context, "movie "+movie.getTitle(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "int " + in, Toast.LENGTH_SHORT).show();
                }

                context.startActivity(intent1);

            }
        }
    }
}

