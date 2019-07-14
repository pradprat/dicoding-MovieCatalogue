package com.example.subm1moviecatalogue.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "series")
public class Series implements Parcelable {

    public Series(){

    }

    @SerializedName("backdrop_path")
    @ColumnInfo(name="backdrop_path")
    private String backdropPath;
    @SerializedName("first_air_date")
    @ColumnInfo(name="first_air_date")
    private String firstAirDate;
//    @SerializedName("genre_ids")
//    @ColumnInfo(name="genre_ids")
//    private ArrayList<Long> genreIds;
    @SerializedName("id")
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = false)
    private Long id;
    @SerializedName("name")
    @ColumnInfo(name="name")
    private String name;
//    @SerializedName("origin_country")
//    @ColumnInfo(name="origin_country")
//    private ArrayList<String> originCountry;
    @SerializedName("original_language")
    @ColumnInfo(name="original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    @ColumnInfo(name="original_name")
    private String originalName;
    @SerializedName("overview")
    @ColumnInfo(name="overview")
    private String overview;
    @SerializedName("popularity")
    @ColumnInfo(name="popularity")
    private Double popularity;
    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    @ColumnInfo(name="vote_average")
    private Double voteAverage;
    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    private Long voteCount;

    protected Series(Parcel in) {
        backdropPath = in.readString();
        firstAirDate = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
//        originCountry = in.createStringArrayList();
        originalLanguage = in.readString();
        originalName = in.readString();
        overview = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        posterPath = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        if (in.readByte() == 0) {
            voteCount = null;
        } else {
            voteCount = in.readLong();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdropPath);
        dest.writeString(firstAirDate);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
//        dest.writeStringList(originCountry);
        dest.writeString(originalLanguage);
        dest.writeString(originalName);
        dest.writeString(overview);
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        dest.writeString(posterPath);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(voteAverage);
        }
        if (voteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(voteCount);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<String> getOriginCountry() {
//        return originCountry;
//    }
//
//    public void setOriginCountry(ArrayList<String> originCountry) {
//        this.originCountry = originCountry;
//    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
