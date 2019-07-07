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

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

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
    }

    public SeriesAdapter(Context context, ArrayList<Series> listSeries) {
        this.context = context;
        this.listSeries = listSeries;
    }

    @NonNull
    @Override
    public SeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_series, parent, false);
        return new SeriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesAdapter.ViewHolder viewHolder, int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185"+getListSeries().get(position).getPosterPath())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(getListSeries().get(position).getName());
        viewHolder.tvSeason.setText(getListSeries().get(position).getOriginalLanguage());
    }

    @Override
    public int getItemCount() {
        return getListSeries().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvSeason;

        ViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_list_series_poster);
            tvTitle = itemView.findViewById(R.id.tv_list_series_title);
            tvSeason = itemView.findViewById(R.id.tv_list_series_season);
        }
    }
}
