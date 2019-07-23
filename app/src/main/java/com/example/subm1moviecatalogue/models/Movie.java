package com.example.subm1moviecatalogue.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.subm1moviecatalogue.databases.MovieContentProvider;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = Movie.TABLE_NAME)
public class Movie implements Parcelable {
    public static final String TABLE_NAME = "movie";

    public Movie(){

    }
    @SerializedName("adult")
    @ColumnInfo(name="adult")
    @Expose
    private Boolean Adult;
    public static final String COLUMN_original_language = "original_language";
    public static final String COLUMN_popularity = "popularity";
    public static final String COLUMN_backdrop_path = "backdrop_path";
    public static final String COLUMN_title = "title";
    public static final String COLUMN_id = "id";

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    @Expose
    private String OriginalLanguage;
    public static final String COLUMN_vote_count = "vote_count";
    private static final String COLUMN_adult = "adult";
    public static final String COLUMN_original_title = "original_title";
    @SerializedName("backdrop_path")
    @ColumnInfo(name="backdrop_path")
    @Expose
    private String BackdropPath;
    public static final String COLUMN_overview = "overview";

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    @Expose
    private Double Popularity;
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name="id")
    @Expose
    private Long Id;
    @SerializedName("original_title")
    @ColumnInfo(name="original_title")
    @Expose
    private String OriginalTitle;
    public static final String COLUMN_poster_path = "poster_path";
    @SerializedName("overview")
    @ColumnInfo(name="overview")
    @Expose
    private String Overview;
    public static final String COLUMN_release_date = "release_date";

    @SerializedName("title")
    @ColumnInfo(name = "title")
    @Expose
    private String Title;
    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    @Expose
    private String PosterPath;
    @SerializedName("release_date")
    @ColumnInfo(name="release_date")
    @Expose
    private String ReleaseDate;
    public static final String COLUMN_video = "video";
    @SerializedName("video")
    @ColumnInfo(name="video")
    @Expose
    private Boolean Video;
    public static final String COLUMN_vote_average = "vote_average";

    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    @Expose
    private Long VoteCount;
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    @Expose
    private Double VoteAverage;

    public static final ContentValues getContentValuesFromMovie(Movie movie) {
        ContentValues values = new ContentValues();
        values.put(Movie.COLUMN_adult, movie.getAdult());
        values.put(Movie.COLUMN_backdrop_path, movie.getBackdropPath());
        values.put(Movie.COLUMN_id, movie.getId());
        values.put(Movie.COLUMN_original_language, movie.getOriginalLanguage());
        values.put(Movie.COLUMN_original_title, movie.getOriginalTitle());
        values.put(Movie.COLUMN_overview, movie.getOverview());
        values.put(Movie.COLUMN_popularity, movie.getPopularity());
        values.put(Movie.COLUMN_poster_path, movie.getPosterPath());
        values.put(Movie.COLUMN_release_date, movie.getReleaseDate());
        values.put(Movie.COLUMN_title, movie.getTitle());
        values.put(Movie.COLUMN_video, movie.getVideo());
        values.put(Movie.COLUMN_vote_average, movie.getVoteAverage());
        values.put(Movie.COLUMN_vote_count, movie.getVoteCount());
        return values;
    }

    public static final Movie getMovieFromCursor(Cursor cursor) {
        Movie movie = new Movie();

        if (MovieContentProvider.getIndexColumn(cursor, "adult") != -1) {
            int bool = cursor.getInt(cursor.getColumnIndexOrThrow("adult"));
            if (bool == 1) {
                movie.Adult = true;
            } else {
                movie.Adult = false;
            }
        }
        if (MovieContentProvider.getIndexColumn(cursor, "backdrop_path") != -1) {
            movie.BackdropPath = cursor.getString(cursor.getColumnIndexOrThrow("backdrop_path"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "id") != -1) {
            movie.Id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "original_language") != -1) {
            movie.OriginalLanguage = cursor.getString(cursor.getColumnIndexOrThrow("original_language"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "original_title") != -1) {
            movie.OriginalTitle = cursor.getString(cursor.getColumnIndexOrThrow("original_title"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "overview") != -1) {
            movie.Overview = cursor.getString(cursor.getColumnIndexOrThrow("overview"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "popularity") != -1) {
            movie.Popularity = cursor.getDouble(cursor.getColumnIndexOrThrow("popularity"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "poster_path") != -1) {
            movie.PosterPath = cursor.getString(cursor.getColumnIndexOrThrow("poster_path"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "release_date") != -1) {
            movie.ReleaseDate = cursor.getString(cursor.getColumnIndexOrThrow("release_date"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "title") != -1) {
            movie.Title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "video") != -1) {
            int bool = cursor.getInt(cursor.getColumnIndexOrThrow("video"));
            if (bool == 1) {
                movie.Video = true;
            } else {
                movie.Video = false;
            }
        }
        if (MovieContentProvider.getIndexColumn(cursor, "vote_average") != -1) {
            movie.VoteAverage = cursor.getDouble(cursor.getColumnIndexOrThrow("vote_average"));
        }
        if (MovieContentProvider.getIndexColumn(cursor, "vote_count") != -1) {
            movie.VoteCount = cursor.getLong(cursor.getColumnIndexOrThrow("vote_count"));
        }
        return movie;
    }

    public static Movie fromContentValues(ContentValues values) {
        final Movie movie = new Movie();
        if (values.containsKey("adult")) {
            movie.Adult = values.getAsBoolean("adult");
        }
        if (values.containsKey("backdrop_path")) {
            movie.BackdropPath = values.getAsString("backdrop_path");
        }
        if (values.containsKey("id")) {
            movie.Id = values.getAsLong("id");
        }
        if (values.containsKey("original_language")) {
            movie.OriginalLanguage = values.getAsString("original_language");
        }
        if (values.containsKey("original_title")) {
            movie.OriginalTitle = values.getAsString("original_title");
        }
        if (values.containsKey("overview")) {
            movie.Overview = values.getAsString("overview");
        }
        if (values.containsKey("popularity")) {
            movie.Popularity = values.getAsDouble("popularity");
        }
        if (values.containsKey("poster_path")) {
            movie.PosterPath = values.getAsString("poster_path");
        }
        if (values.containsKey("release_date")) {
            movie.ReleaseDate = values.getAsString("release_date");
        }
        if (values.containsKey("title")) {
            movie.Title = values.getAsString("title");
        }
        if (values.containsKey("video")) {
            movie.Video = values.getAsBoolean("video");
        }
        if (values.containsKey("vote_average")) {
            movie.VoteAverage = values.getAsDouble("vote_average");
        }
        if (values.containsKey("vote_count")) {
            movie.VoteCount = values.getAsLong("vote_count");
        }
        return movie;
    }

    public Boolean getAdult() {
        return Adult;
    }

    public void setAdult(Boolean adult) {
        Adult = adult;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        BackdropPath = backdropPath;
    }

//    public ArrayList<Long> getGenreIds() {
//        return GenreIds;
//    }
//
//    public void setGenreIds(ArrayList<Long> genreIds) {
//        GenreIds = genreIds;
//    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOriginalLanguage() {
        return OriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        OriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return OriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        OriginalTitle = originalTitle;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public Double getPopularity() {
        return Popularity;
    }

    public void setPopularity(Double popularity) {
        Popularity = popularity;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Boolean getVideo() {
        return Video;
    }

    public void setVideo(Boolean video) {
        Video = video;
    }

    public Double getVoteAverage() {
        return VoteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        VoteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return VoteCount;
    }

    public void setVoteCount(Long voteCount) {
        VoteCount = voteCount;
    }

    protected Movie(Parcel in) {
        byte tmpAdult = in.readByte();
        Adult = tmpAdult == 0 ? null : tmpAdult == 1;
        BackdropPath = in.readString();
        if (in.readByte() == 0) {
            Id = null;
        } else {
            Id = in.readLong();
        }
        OriginalLanguage = in.readString();
        OriginalTitle = in.readString();
        Overview = in.readString();
        if (in.readByte() == 0) {
            Popularity = null;
        } else {
            Popularity = in.readDouble();
        }
        PosterPath = in.readString();
        ReleaseDate = in.readString();
        Title = in.readString();
        byte tmpVideo = in.readByte();
        Video = tmpVideo == 0 ? null : tmpVideo == 1;
        if (in.readByte() == 0) {
            VoteAverage = null;
        } else {
            VoteAverage = in.readDouble();
        }
        if (in.readByte() == 0) {
            VoteCount = null;
        } else {
            VoteCount = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (Adult == null ? 0 : Adult ? 1 : 2));
        dest.writeString(BackdropPath);
        if (Id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(Id);
        }
        dest.writeString(OriginalLanguage);
        dest.writeString(OriginalTitle);
        dest.writeString(Overview);
        if (Popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Popularity);
        }
        dest.writeString(PosterPath);
        dest.writeString(ReleaseDate);
        dest.writeString(Title);
        dest.writeByte((byte) (Video == null ? 0 : Video ? 1 : 2));
        if (VoteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(VoteAverage);
        }
        if (VoteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(VoteCount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

//    public static Movie fromContentValues(ContentValues values) {
//        final Movie cheese = new Movie();
//        if (values.containsKey(COLUMN_ID)) {
//            cheese.id = values.getAsLong(COLUMN_ID);
//        }
//        if (values.containsKey(COLUMN_NAME)) {
//            cheese.name = values.getAsString(COLUMN_NAME);
//        }
//        return cheese;
//    }
}
