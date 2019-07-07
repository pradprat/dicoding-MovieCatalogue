package com.example.subm1moviecatalogue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subm1moviecatalogue.databases.MovieDatabase;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.Series;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    MovieDatabase movieDatabase;
    Movie movie;
    Series series;
    int ADD_FAVORITE=100;
    int REMOVE_FAVORITE=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        TextView tvDesc = findViewById(R.id.tv_detail_movie_desc);
        TextView tvTitle = findViewById(R.id.tv_detail_movie_title);
        TextView tvRelease = findViewById(R.id.tv_detail_movie_release);
        ImageView ivPoster = findViewById(R.id.iv_detail_movie_poster);
        ImageView ivBackground = findViewById(R.id.iv_detail_movie_background);
        TextView tvRating = findViewById(R.id.tv_detail_movie_rating);
        TextView tvVotes = findViewById(R.id.tv_detail_movie_voters);
        TextView tvPopular = findViewById(R.id.tv_detail_movie_popularity);
        final CheckBox cbFavorite = findViewById(R.id.cb_detail_favorite);

        movieDatabase = new MovieDatabase(this);

        String type = getIntent().getStringExtra("type");
        switch (type){
            case "movie":
                movie = getIntent().getParcelableExtra("PARCEL");
//
                tvDesc.setText(movie.getOverview());
                tvTitle.setText(movie.getTitle());
                tvRelease.setText(movie.getReleaseDate());

                tvRating.setText(movie.getVoteAverage().toString());
                if (movie.getVoteAverage()>7){
                    tvRating.setTextColor(getResources().getColor(R.color.highRating));
                }else if(movie.getVoteAverage()>5){
                    tvRating.setTextColor(getResources().getColor(R.color.midRating));
                }else{
                    tvRating.setTextColor(getResources().getColor(R.color.lowRating));
                }
                tvVotes.setText(movie.getVoteCount()+" votes");

                Picasso.get().load("https://image.tmdb.org/t/p/w185"+movie.getPosterPath())
                        .into(ivPoster);
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.getBackdropPath())
                        .into(ivBackground);

                tvPopular.setText(movie.getPopularity().toString());

//               add Favorite button
                if (movieDatabase.isFavMovieExist(movie)){
                    cbFavorite.setChecked(true);
                }else{
                    cbFavorite.setChecked(false);
                }
                
                cbFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!isChecked){ // exist in database
                            movieDatabase.deleteFavMovie(movie);
                            cbFavorite.setChecked(false);
                            Toast.makeText(getApplicationContext(), movie.getTitle()+" dihapus dari Favorite Movie", Toast.LENGTH_SHORT).show();

                        }else{ // not exist
                            movieDatabase.insertFavMovies(movie);
                            cbFavorite.setChecked(true);
                            Toast.makeText(getApplicationContext(), movie.getTitle()+" ditambahkan ke Favorite Movie", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            case "series":
                series = getIntent().getParcelableExtra("PARCEL");
//
                tvDesc.setText(series.getOverview());
                tvTitle.setText(series.getName());
                tvRating.setText(series.getVoteAverage().toString());
                if (series.getVoteAverage()>7){
                    tvRating.setTextColor(getResources().getColor(R.color.highRating));
                }else if(series.getVoteAverage()>5){
                    tvRating.setTextColor(getResources().getColor(R.color.midRating));
                }else{
                    tvRating.setTextColor(getResources().getColor(R.color.lowRating));
                }
                Picasso.get().load("https://image.tmdb.org/t/p/w185"+series.getPosterPath())
                        .into(ivPoster);
                Picasso.get().load("https://image.tmdb.org/t/p/w500"+series.getBackdropPath())
                        .into(ivBackground);
//                tvRelease.setText("("+series.getRelease()+")");
                tvPopular.setText(series.getPopularity().toString());

                //               add Favorite button
                if (movieDatabase.isFavSeriesExist(series)){
                    cbFavorite.setChecked(true);
                }else{
                    cbFavorite.setChecked(false);
                }

                cbFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!isChecked){ // exist in database
                            movieDatabase.deleteFavSeries(series);
                            cbFavorite.setChecked(false);
                            Toast.makeText(getApplicationContext(), series.getName()+" dihapus dari Favorite Series", Toast.LENGTH_SHORT).show();

                        }else{ // not exist
                            movieDatabase.insertFavSeries(series);
                            cbFavorite.setChecked(true);
                            Toast.makeText(getApplicationContext(), series.getName()+" ditambahkan ke Favorite Series", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;

        }


        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
