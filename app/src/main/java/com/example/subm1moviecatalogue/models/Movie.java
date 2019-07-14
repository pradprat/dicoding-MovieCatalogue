package com.example.subm1moviecatalogue.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie")
public class Movie implements Parcelable {

    public Movie(){

    }
    @SerializedName("adult")
    @ColumnInfo(name="adult")
    @Expose
    private Boolean Adult;

    @SerializedName("backdrop_path")
    @ColumnInfo(name="backdrop_path")
    @Expose
    private String BackdropPath;

//    @SerializedName("genre_ids")
//    @ColumnInfo(name="genre_ids")
//    @Expose
//    private ArrayList<Long> GenreIds;

    @SerializedName("id")
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = false)
    @Expose
    private Long Id;

    @SerializedName("original_language")
    @ColumnInfo(name="original_language")
    @Expose
    private String OriginalLanguage;

    @SerializedName("original_title")
    @ColumnInfo(name="original_title")
    @Expose
    private String OriginalTitle;

    @SerializedName("overview")
    @ColumnInfo(name="overview")
    @Expose
    private String Overview;

    @SerializedName("popularity")
    @ColumnInfo(name="popularity")
    @Expose
    private Double Popularity;

    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    @Expose
    private String PosterPath;

    @SerializedName("release_date")
    @ColumnInfo(name="release_date")
    @Expose
    private String ReleaseDate;

    @SerializedName("title")
    @ColumnInfo(name="title")
    @Expose
    private String Title;

    @SerializedName("video")
    @ColumnInfo(name="video")
    @Expose
    private Boolean Video;

    @SerializedName("vote_average")
    @ColumnInfo(name="vote_average")
    @Expose
    private Double VoteAverage;

    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    @Expose
    private Long VoteCount;

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
}
