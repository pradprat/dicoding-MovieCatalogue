package com.example.subm1moviecatalogue;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.subm1moviecatalogue.adapters.FavoritesTabAdapter;

public class FavoritesActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        FavoritesTabAdapter adapter = new FavoritesTabAdapter(getSupportFragmentManager());

        adapter.addFragment(new MovieFavoriteFragment(), "Movie");
        adapter.addFragment(new SeriesFavoriteFragment(), "Series");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


}
