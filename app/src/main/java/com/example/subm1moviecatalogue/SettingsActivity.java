package com.example.subm1moviecatalogue;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.services.NotificationService;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        public static final String REMINDER = "notif_reminder";
        public static final String RELEASE = "notif_release";
        NotificationService notificationService;
        SwitchPreferenceCompat swNotifReminder, swNotifRelease;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            notificationService = new NotificationService();

            swNotifRelease = findPreference(RELEASE);
            swNotifReminder = findPreference(REMINDER);

            swNotifRelease.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean checked = (boolean) newValue;
                    if (checked) {
//                        Log.d("__notif", "checked");
                        String repeatTime = getCurrentTimeWithOffset();
//                        String repeatTime = "00:05";

                        String repeatMessage = "Movie released today";

                        notificationService.setReleaseNotif(getActivity(), NotificationService.TYPE_RELEASE,
                                repeatTime, repeatMessage);
                    } else {
//                        Log.d("__notif", "unchecked");
                        notificationService.cancelAlarm(getContext(), NotificationService.TYPE_RELEASE);
                    }
                    return true;
                }
            });
            swNotifReminder.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    boolean checked = (boolean) newValue;
                    if (checked) {
                        Log.d("__notif", "checked");
                        String repeatTime = getCurrentTimeWithOffset();
//                        String repeatTime = "00:05";
//                        Log.d("__time", repeatTime);
                        String repeatMessage = "Catalogue Movie Missing you";

                        notificationService.setReminderNotif(getActivity(), NotificationService.TYPE_REMINDER,
                                repeatTime, repeatMessage);
                    } else {
                        Log.d("__notif", "unchecked");
                        notificationService.cancelAlarm(getContext(), NotificationService.TYPE_REMINDER);
                    }
                    return true;
                }
            });
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @SuppressLint("SimpleDateFormat")
        public String getCurrentTimeWithOffset() {
            return new SimpleDateFormat("HH:mm:ss").format(new Date());
        }
    }
}