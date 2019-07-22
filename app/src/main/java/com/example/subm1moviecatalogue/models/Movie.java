package com.example.subm1moviecatalogue.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie")
public class Movie implements Parcelable {
    public static final String TABLE_NAME = "movie";

    public Movie(){

    }
    @SerializedName("adult")
    @ColumnInfo(name="adult")
    @Expose
    public static final String COLUMN_adult = "adult";
    private Boolean Adult;

    @SerializedName("backdrop_path")
    @ColumnInfo(name="backdrop_path")
    @Expose
    public static final String COLUMN_backdrop_path = "backdrop_path";
    private String BackdropPath;

    @SerializedName("id")
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = false)
    @Expose
    public static final String COLUMN_id = "id";
    private Long Id;

    @SerializedName("original_language")
    @ColumnInfo(name="original_language")
    @Expose
    public static final String COLUMN_original_language = "original_language";
    private String OriginalLanguage;

    @SerializedName("original_title")
    @ColumnInfo(name="original_title")
    @Expose
    public static final String COLUMN_original_title = "original_title";
    private String OriginalTitle;

    @SerializedName("overview")
    @ColumnInfo(name="overview")
    @Expose
    public static final String COLUMN_overview = "overview";
    private String Overview;

    @SerializedName("popularity")
    @ColumnInfo(name="popularity")
    @Expose
    public static final String COLUMN_popularity = "popularity";
    private Double Popularity;

    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    @Expose
    public static final String COLUMN_poster_path = "poster_path";
    private String PosterPath;

    @SerializedName("release_date")
    @ColumnInfo(name="release_date")
    @Expose
    public static final String COLUMN_release_date = "release_date";
    private String ReleaseDate;

    @SerializedName("title")
    @ColumnInfo(name="title")
    @Expose
    public static final String COLUMN_title = "title";
    private String Title;

    @SerializedName("video")
    @ColumnInfo(name="video")
    @Expose
    public static final String COLUMN_video = "video";
    private Boolean Video;

    @SerializedName("vote_average")
    @ColumnInfo(name="vote_average")
    @Expose
    public static final String COLUMN_vote_average = "vote_average";
    private Double VoteAverage;

    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    @Expose
    public static final String COLUMN_vote_count = "vote_count";
    private Long VoteCount;

    public static Movie fromContentValues(ContentValues values) {
        final Movie movie = new Movie();
        if (values.containsKey("adult")) {
            movie.Adult = values.getAsBoolean("adult");
        }
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
