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
import com.example.subm1moviecatalogue.models.Series;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Series> listSeries;
    private ArrayList<Movie> listMovies;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Series> getListSeries() {
        return listSeries;
    }

    public ArrayList<Movie> getListMovie() {
        return listMovies;
    }

    public void setListSeries(ArrayList<Series> listSeries) {
        this.listSeries = listSeries;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovies = listMovie;
    }

    public SearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int position) {
        if (getListMovie()==null){
            Picasso.get().load("https://image.tmdb.org/t/p/w185"+getListSeries().get(position).getPosterPath())
                    .into(viewHolder.ivPoster);
            viewHolder.tvTitle.setText(getListSeries().get(position).getName());
        }else{
            Picasso.get().load("https://image.tmdb.org/t/p/w185"+getListMovie().get(position).getPosterPath())
                    .into(viewHolder.ivPoster);
            viewHolder.tvTitle.setText(getListMovie().get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if(getListSeries()==null){
            return getListMovie().size();
        }else{
            return getListSeries().size();
        }
    }

    public void clearList(String type){
        int range;
        if (type.equals("Movie")){
            range = listMovies.size();
            listMovies.clear();
        }else{
            range = listSeries.size();
            listSeries.clear();
        }
        notifyItemRangeRemoved(0,range);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.iv_list_movie_poster);
            tvTitle = itemView.findViewById(R.id.tv_list_movie_title);
        }
    }
}
