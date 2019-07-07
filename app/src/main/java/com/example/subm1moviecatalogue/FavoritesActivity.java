package com.example.subm1moviecatalogue;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.subm1moviecatalogue.adapters.FavoritesTabAdapter;

public class FavoritesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        FavoritesTabAdapter adapter = new FavoritesTabAdapter(getSupportFragmentManager());

        adapter.addFragment(new MovieFavoriteFragment(), "Movie");
        adapter.addFragment(new SeriesFavoriteFragment(), "Series");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
