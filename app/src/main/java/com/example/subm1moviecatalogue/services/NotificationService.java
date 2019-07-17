package com.example.subm1moviecatalogue.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.subm1moviecatalogue.MainHomeActivity;
import com.example.subm1moviecatalogue.MovieDetailActivity;
import com.example.subm1moviecatalogue.R;
import com.example.subm1moviecatalogue.models.Movie;
import com.example.subm1moviecatalogue.models.MovieResult;
import com.example.subm1moviecatalogue.viewmodels.MovieViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class NotificationService extends BroadcastReceiver {

    public static final String TYPE_REMINDER = "Hey";
    public static final String TYPE_RELEASE = "New Movie Release";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_OBJECT = "object";
    public final static String TIME_REMINDER = "07:00";
    public final static String TIME_RELEASE = "08:00";
    // Siapkan 2 id untuk 2 macam alarm, onetime dna repeating
    private final static int ID_REMINDER = 900;
    private final static int ID_RELEASE = 901;
    private final static String TIME_FORMAT = "HH:mm:ss";
    MovieViewModel movieViewModel;
    Movie mMovie;
    ArrayList<Movie> movies = new ArrayList<>();
    String title;
    int notifId;
    String message;
    Context context;

    public NotificationService() {
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        this.context = context;
        String type = intent.getStringExtra(EXTRA_TYPE);
        message = intent.getStringExtra(EXTRA_MESSAGE);
        Movie movie = intent.getParcelableExtra(EXTRA_OBJECT);

        if (Objects.equals(type, TYPE_REMINDER)) {
            title = TYPE_REMINDER;
        } else {
            title = TYPE_RELEASE;
        }

        if (Objects.equals(type, TYPE_REMINDER)) {
            notifId = ID_REMINDER;

            showAlarmNotification(context, title, message, notifId);
        } else {
            notifId = ID_RELEASE;

            new AsyncTask<Void, Void, Movie>() {
                @Override
                protected void onPostExecute(Movie movie) {
                    super.onPostExecute(movie);
                    Log.d("__mainhome", "onPostExecute: " + movie.getTitle());
                    mMovie = movie;
                    showAlarmNotification(context, title, message, notifId);
                }

                @Override
                protected Movie doInBackground(Void... voids) {
                    ArrayList<Movie> movies = new ArrayList<>();
                    MovieViewModel movieViewModel = new MovieViewModel();
                    movies.addAll(movieViewModel.getReleasedMovies());
                    return movies.get(0);
                }
            }.execute();
//            Log.d("__wtf", "onReceive: "+movies.get(0).getTitle());
        }


    }

    // Gunakan metode ini untuk menampilkan notifikasi
    private void showAlarmNotification(Context context, String title, String message, int notifId) {
        String CHANNEL_ID = "Movie_1";
        String CHANNEL_NAME = "Movie Move";


        Intent resultIntent;

        if (notifId == ID_REMINDER) {
            // go to main home
            resultIntent = new Intent(context, MainHomeActivity.class);
        } else {
            // go to Detail Activity
            resultIntent = new Intent(context, MovieDetailActivity.class);
            resultIntent.putExtra("type", "Movie");
            resultIntent.putExtra("PARCEL", mMovie);
            message += ' ' + mMovie.getTitle();
        }

        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_favorite_24px)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(new long[]{1000, 20, 20, 20, 1000})
                .setSound(alarmSound)
                .setContentIntent(resultPendingIntent);

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 20, 20, 20, 1000});

            builder.setChannelId(CHANNEL_ID);

            if (notificationManagerCompat != null) {
                notificationManagerCompat.createNotificationChannel(channel);
            }
        }

        Notification notification = builder.build();

        if (notificationManagerCompat != null) {
            notificationManagerCompat.notify(notifId, notification);
        }

    }

    // Metode ini digunakan untuk menjalankan alarm repeating
    public void setReminderNotif(Context context, String type, String time, String message) {

        // Validasi inputan waktu terlebih dahulu
        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationService.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);

        String timeArray[] = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(timeArray[2]));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REMINDER, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        Toast.makeText(context, "Reminder alarm set up " + time, Toast.LENGTH_SHORT).show();
        Log.d("__notif", "Reminder alarm set up " + time);

    }

    public void setReleaseNotif(Context context, String type, String time, String message) {

        // Validasi inputan waktu terlebih dahulu
        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationService.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_TYPE, type);

        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(timeArray[2]));

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }

        Toast.makeText(context, "Release alarm set up " + time + " ", Toast.LENGTH_SHORT).show();
        Log.d("__notif", "Release alarm set up " + time);
    }

    public void cancelAlarm(Context context, String type) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationService.class);
        int requestCode = type.equalsIgnoreCase(TYPE_RELEASE) ? ID_RELEASE : ID_REMINDER;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

        Toast.makeText(context, "Repeating alarm dibatalkan", Toast.LENGTH_SHORT).show();
    }

    // Gunakan metode ini untuk mengecek apakah alarm tersebut sudah terdaftar di alarm manager
    public boolean isAlarmSet(Context context, String type) {
        Intent intent = new Intent(context, NotificationService.class);
        int requestCode = type.equalsIgnoreCase(TYPE_REMINDER) ? ID_REMINDER : ID_RELEASE;

        return PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    // Metode ini digunakan untuk validasi date dan time
    public boolean isDateInvalid(String date, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.setLenient(false);
            df.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }
}
