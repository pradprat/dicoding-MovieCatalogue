package com.example.subm1moviecatalogue;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.subm1moviecatalogue.adapters.ItemClickSupport;
import com.example.subm1moviecatalogue.adapters.SeriesAdapter;
import com.example.subm1moviecatalogue.models.Series;
import com.example.subm1moviecatalogue.models.SeriesResult;
import com.example.subm1moviecatalogue.viewmodels.SeriesViewModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {
    private ArrayList<Series> seriess=new ArrayList<>();
    private SeriesAdapter mSeriesAdapter;
    private RecyclerView rvSeries;
    private ProgressBar mProgressBar;
    private SeriesViewModel mSeriesViewModel;

    public SeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_series, container, false);

        mProgressBar = rootView.findViewById(R.id.pg_fragment_series);
        rvSeries = rootView.findViewById(R.id.rv_fragment_series);

        mSeriesViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(SeriesViewModel.class);


        mSeriesViewModel.getSeriess().observe(this, new Observer<SeriesResult>() {
            @Override
            public void onChanged(@Nullable SeriesResult seriesResult) {
                assert seriesResult != null;
                ArrayList<Series> resSeries = seriesResult.getResults();
                seriess.addAll(resSeries);
                mSeriesAdapter.notifyDataSetChanged();


//                check is data avaliable + hide progress bar
                mSeriesViewModel.closing();
            }
        });
        mSeriesViewModel.getIsFetching().observe(this, new Observer<Boolean>() {
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

    private void setRecyclerView(){
        if (mSeriesAdapter == null){
            mSeriesAdapter = new SeriesAdapter(getActivity(),seriess);
            rvSeries.setLayoutManager(new GridLayoutManager(getActivity(),2));
            rvSeries.setAdapter(mSeriesAdapter);
            ItemClickSupport.addTo(rvSeries).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                    intent.putExtra("type","Series");
                    intent.putExtra("PARCEL",seriess.get(position));
                    startActivity(intent);
                }
            });
        }else{
            mSeriesAdapter.notifyDataSetChanged();
        }
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);
    }

}
