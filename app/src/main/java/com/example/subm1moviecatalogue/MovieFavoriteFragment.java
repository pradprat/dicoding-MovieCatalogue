package com.example.subm1moviecatalogue;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.subm1moviecatalogue.adapters.FavMovieAdapter;
import com.example.subm1moviecatalogue.adapters.ItemClickSupport;
import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.viewmodels.FavMovieViewModel;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    private ArrayList<Movie> movies=new ArrayList<>();
    private FavMovieAdapter mFavMovieAdapter;
    private RecyclerView rvMovie;
    private ProgressBar mProgressBar;
    private FavMovieViewModel mFavMovieViewModel;
    private Movie movieSelected;
    private MovieDatabase movieDatabase;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_movie_favorite, container, false);


        mProgressBar = rootView.findViewById(R.id.pg_fav_fragment_movie);
        rvMovie = rootView.findViewById(R.id.rv_favorite_movie);
        movieDatabase = new MovieDatabase(getActivity());


        mFavMovieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(FavMovieViewModel.class);

//        initializing data + show progress bar
        mFavMovieViewModel.init(getActivity());

        mFavMovieViewModel.getMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> resMovie) {
                assert resMovie != null;
                movies.addAll(resMovie);
                mFavMovieAdapter.notifyDataSetChanged();

//                check is data avaliable + hide progress bar
                mFavMovieViewModel.closing();
            }
        });
        mFavMovieViewModel.getIsFetching().observe(this, new Observer<Boolean>() {
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
            if (!movieDatabase.isFavMovieExist(movieSelected)){
                mFavMovieAdapter.removeItem(movieSelected);
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
        if (mFavMovieAdapter == null){
            mFavMovieAdapter = new FavMovieAdapter(getActivity(),movies);
            rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvMovie.setAdapter(mFavMovieAdapter);

            ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                    intent.putExtra("type","Movie");
                    intent.putExtra("PARCEL",movies.get(position));
                    movieSelected=movies.get(position);


//                    startActivity(intent);
                    startActivityForResult(intent,100);
                }
            });
        }else{
            mFavMovieAdapter.notifyDataSetChanged();
        }
    }
}
