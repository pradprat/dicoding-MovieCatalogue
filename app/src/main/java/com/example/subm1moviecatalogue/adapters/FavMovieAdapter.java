package com.example.subm1moviecatalogue.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subm1moviecatalogue.R;
import com.example.subm1moviecatalogue.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> listMovie;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    public FavMovieAdapter(Context context, ArrayList<Movie> listMovie) {
        this.context = context;
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public FavMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fav_movie, parent, false);
        return new FavMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMovieAdapter.ViewHolder viewHolder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+getListMovie().get(position).getBackdropPath())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(getListMovie().get(position).getTitle());
    }
    public void removeItem(Movie movie){
        int index = listMovie.indexOf(movie);
        this.listMovie.remove(movie);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index,listMovie.size());
    }
    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.iv_list_fav_movie_poster);
            tvTitle = itemView.findViewById(R.id.tv_list_fav_movie_title);
        }
    }
}
