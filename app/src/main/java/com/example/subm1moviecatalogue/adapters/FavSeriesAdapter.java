package com.example.subm1moviecatalogue.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.subm1moviecatalogue.R;
import com.example.subm1moviecatalogue.models.Series;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavSeriesAdapter extends RecyclerView.Adapter<FavSeriesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Series> listSeries;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Series> getListSeries() {
        return listSeries;
    }

    public void setListSeries(ArrayList<Series> listSeries) {
        this.listSeries = listSeries;
        notifyDataSetChanged();
    }

    public FavSeriesAdapter(Context context, ArrayList<Series> listSeries) {
        this.context = context;
        this.listSeries = listSeries;
    }

    @NonNull
    @Override
    public FavSeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fav_movie, parent, false);
        return new FavSeriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavSeriesAdapter.ViewHolder viewHolder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+getListSeries().get(position).getBackdropPath())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(getListSeries().get(position).getName());
    }
    public void removeItem(Series series){
        int index = listSeries.indexOf(series);
        this.listSeries.remove(series);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index,listSeries.size());
    }
    @Override
    public int getItemCount() {
        return listSeries.size();
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
