package com.example.subm1moviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

public class MainHomeActivity extends AppCompatActivity{
    private Fragment fragment;
    private FragmentManager fm;
    private FragmentTransaction ft;


    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieFragment();
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.title_movie));
                    break;
                case R.id.navigation_series:
                    fragment = new SeriesFragment();
                    Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.title_series));
                    break;
            }
            setFragment();
            return true;
        }
    };

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mIntent = null;
        if (item.getItemId() == R.id.action_change_settings){
            mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        }else if (item.getItemId() == R.id.navigation_favorites){
            mIntent = new Intent(getApplicationContext(),FavoritesActivity.class);
        }
        if (mIntent!=null){
            startActivity(mIntent);
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
            getSupportActionBar().setTitle(getResources().getString(R.string.title_movie));
        }
        fragment = new MovieFragment();
        setFragment();
    }

    private void setFragment(){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fl_main_home, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }



}
