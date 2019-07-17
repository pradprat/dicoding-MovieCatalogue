package com.example.subm1moviecatalogue;

import android.annotation.SuppressLint;
import android.app.SearchManager;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.subm1moviecatalogue.adapters.MovieAdapter;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class MainHomeActivity extends AppCompatActivity{
    private Fragment fragment;
    private String MenuSelect;
    private MovieViewModel mMovieViewModel;
    ArrayList<Movie> movies2 = new ArrayList<>();

    private ArrayList<Movie> Movies=new ArrayList<>();
    private MovieAdapter mMovieAdapter;
    MovieViewModel movieViewModel = new MovieViewModel();

    private Context context = this;



    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.title_movie));
                    MenuSelect = "Movie";
                    break;
                case R.id.navigation_series:
                    fragment = new SeriesFragment();
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.title_series));
                    MenuSelect = "Series";
                    break;
            }
            setFragment();
            return true;
        }
    };

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                /*
                Gunakan method ini ketika search selesai atau OK
                 */
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                    intent.putExtra("QUERY",query);
                    intent.putExtra("KIND",MenuSelect);
                    startActivity(intent);
                    return true;
                }

                /*
                Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
                 */
                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });
        }

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent = null;

        switch (item.getItemId()){
            case R.id.action_change_settings:
                mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                return super.onOptionsItemSelected(item);

            case R.id.navigation_favorites:
                mIntent = new Intent(getApplicationContext(),FavoritesActivity.class);
                startActivity(mIntent);
                return super.onOptionsItemSelected(item);

            case R.id.action_settings:
                mIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(mIntent);


                return super.onOptionsItemSelected(item);


        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        init();

    }

    private void init(){
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.title_series));
        }
        MenuSelect = "Series";
        fragment = new SeriesFragment();
        setFragment();
    }

    private void setFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_main_home, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }



}
