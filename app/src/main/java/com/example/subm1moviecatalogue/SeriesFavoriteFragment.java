package com.example.subm1moviecatalogue;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.subm1moviecatalogue.adapters.FavMovieAdapter;
import com.example.subm1moviecatalogue.adapters.FavSeriesAdapter;
import com.example.subm1moviecatalogue.adapters.ItemClickSupport;
import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.Series;
import com.example.subm1moviecatalogue.viewmodels.FavMovieViewModel;
import com.example.subm1moviecatalogue.viewmodels.FavSeriesViewModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFavoriteFragment extends Fragment {
    private ArrayList<Series> Seriess=new ArrayList<>();
    private FavSeriesAdapter mFavSeriesAdapter;
    private RecyclerView rvSeries;
    private ProgressBar mProgressBar;
    private FavSeriesViewModel mFavSeriesViewModel;
    private Series SeriesSelected;
    private MovieDatabase SeriesDatabase;

    public SeriesFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_series_favorite, container, false);


        mProgressBar = rootView.findViewById(R.id.pg_fav_fragment_series);
        rvSeries = rootView.findViewById(R.id.rv_favorite_series);
        SeriesDatabase = new MovieDatabase(getActivity());


        mFavSeriesViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(FavSeriesViewModel.class);

//        initializing data + show progress bar
        mFavSeriesViewModel.init(getActivity());

        mFavSeriesViewModel.getSeries().observe(this, new Observer<ArrayList<Series>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Series> resSeries) {
                assert resSeries != null;
                Seriess.addAll(resSeries);
                mFavSeriesAdapter.notifyDataSetChanged();

//                check is data avaliable + hide progress bar
                mFavSeriesViewModel.closing();
            }
        });
        mFavSeriesViewModel.getIsFetching().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }else {
                    hideProgressBar();
                }
            }
        });
        setRecyclerView();


        return rootView;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (!SeriesDatabase.isFavSeriesExist(SeriesSelected)){
                mFavSeriesAdapter.removeItem(SeriesSelected);
            }
        }
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void setRecyclerView(){
        if (mFavSeriesAdapter == null){
            mFavSeriesAdapter = new FavSeriesAdapter(getActivity(),Seriess);
            rvSeries.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvSeries.setAdapter(mFavSeriesAdapter);

            ItemClickSupport.addTo(rvSeries).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                    intent.putExtra("type","series");
                    intent.putExtra("PARCEL",Seriess.get(position));
                    SeriesSelected=Seriess.get(position);


//                    startActivity(intent);
                    startActivityForResult(intent,100);
                }
            });
        }else{
            mFavSeriesAdapter.notifyDataSetChanged();
        }
    }
}
