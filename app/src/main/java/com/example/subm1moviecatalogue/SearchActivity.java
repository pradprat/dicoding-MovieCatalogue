package com.example.subm1moviecatalogue;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.subm1moviecatalogue.adapters.ItemClickSupport;
import com.example.subm1moviecatalogue.adapters.SearchAdapter;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.models.Series;
import com.example.subm1moviecatalogue.models.SeriesResult;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;
import com.example.subm1moviecatalogue.viewmodels.SeriesViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView rvSearch;
    private MovieViewModel mMovieViewModel;
    private SeriesViewModel mSeriesViewModel;
    ArrayList<Series> seriess = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    SearchAdapter mSearchAdapter;
    String query,kind;

    @Override
    protected void onStop() {
        super.onStop();
        mSearchAdapter.clearList(kind);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_search);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        query = getIntent().getStringExtra("QUERY");
        kind = getIntent().getStringExtra("KIND");

        rvSearch = findViewById(R.id.rv_search_activity);

        if (kind.equals("Movie")){
//            if user select movie tab=======================================================================
            searchMovie(query);
        }else{
//            if user select Series tab======================================================================
            searchSeries(query);
        }
        super.onCreate(savedInstanceState);
    }

    private void searchMovie(String query){
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        mMovieViewModel.getSearchMovies(query).observe(this, new Observer<MovieResult>() {
            @Override
            public void onChanged(@Nullable MovieResult MovieResult) {
                assert MovieResult != null;
                ArrayList<Movie> resMovie = MovieResult.getResults();
                movies.clear();
                movies.addAll(resMovie);
                mSearchAdapter.notifyDataSetChanged();
                mMovieViewModel.closingSearch();
            }
        });

        if (mSearchAdapter == null){
            mSearchAdapter = new SearchAdapter(this);
            mSearchAdapter.setListMovie(movies);
            rvSearch.setLayoutManager(new GridLayoutManager(this,2));
            rvSearch.setAdapter(mSearchAdapter);
            ItemClickSupport.addTo(rvSearch).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getApplicationContext(),MovieDetailActivity.class);
                    intent.putExtra("type","Movie");
                    intent.putExtra("PARCEL",movies.get(position));
                    startActivity(intent);
                }
            });
        }else{
            mSearchAdapter.notifyDataSetChanged();
        }
    }

    private void searchSeries(String query){
        mSeriesViewModel = ViewModelProviders.of(this).get(SeriesViewModel.class);

        mSeriesViewModel.getSearchSeriess(query).observe(this, new Observer<SeriesResult>() {
            @Override
            public void onChanged(@Nullable SeriesResult SeriesResult) {
                assert SeriesResult != null;
                ArrayList<Series> resSeries = SeriesResult.getResults();
                seriess.clear();
                seriess.addAll(resSeries);
                mSearchAdapter.notifyDataSetChanged();
                mSeriesViewModel.closingSearch();
            }
        });

        if (mSearchAdapter == null){
            mSearchAdapter = new SearchAdapter(this);
            mSearchAdapter.setListSeries(seriess);
            rvSearch.setLayoutManager(new GridLayoutManager(this,2));
            rvSearch.setAdapter(mSearchAdapter);
            ItemClickSupport.addTo(rvSearch).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getApplicationContext(),MovieDetailActivity.class);
                    intent.putExtra("type","Series");
                    intent.putExtra("PARCEL",seriess.get(position));
                    startActivity(intent);
                }
            });
        }else{
            mSearchAdapter.notifyDataSetChanged();
        }
    }
}
