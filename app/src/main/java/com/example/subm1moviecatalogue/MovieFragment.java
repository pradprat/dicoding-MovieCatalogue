    package com.example.subm1moviecatalogue;


    import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.subm1moviecatalogue.adapters.ItemClickSupport;
import com.example.subm1moviecatalogue.adapters.MovieAdapter;
import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;


    /**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private ArrayList<Movie> movies=new ArrayList<>();
    private MovieAdapter mMovieAdapter;
    private RecyclerView rvMovie;
    private ProgressBar mProgressBar;
    private MovieViewModel mMovieViewModel;

    public MovieFragment() {
    // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_movie, container, false);


        mProgressBar = rootView.findViewById(R.id.pg_fragment_movie);
        rvMovie = rootView.findViewById(R.id.rv_fragment_movie);


        mMovieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);

//        initializing data + show progress bar
        mMovieViewModel.init();

        mMovieViewModel.getMovies().observe(this, new Observer<MovieResult>() {
            @Override
            public void onChanged(@Nullable MovieResult movieResult) {
                assert movieResult != null;
                ArrayList<Movie> resMovie = movieResult.getResults();
                movies.addAll(resMovie);
                mMovieAdapter.notifyDataSetChanged();


//                check is data avaliable + hide progress bar
                mMovieViewModel.closing();
            }
        });
        mMovieViewModel.getIsFetching().observe(this, new Observer<Boolean>() {
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
        if (mMovieAdapter == null){
            mMovieAdapter = new MovieAdapter(getActivity(),movies);
            rvMovie.setLayoutManager(new GridLayoutManager(getActivity(),2));
            rvMovie.setAdapter(mMovieAdapter);

            ItemClickSupport.addTo(rvMovie).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                    intent.putExtra("type","movie");
                    intent.putExtra("PARCEL",movies.get(position));

                    startActivity(intent);
                }
            });
        }else{
            mMovieAdapter.notifyDataSetChanged();
        }
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);
    }


}

