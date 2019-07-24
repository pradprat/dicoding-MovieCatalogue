package com.example.subm1moviecatalogue.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.subm1moviecatalogue.databases.MovieContentProvider;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "series")
public class Series implements Parcelable {
    public static final String TABLE_NAME = "series";

    public Series(){

    }

    @SerializedName("backdrop_path")
    @ColumnInfo(name="backdrop_path")
    private String backdropPath;
    public static final String COLUMN_backdropPath = "backdrop_path";

    @SerializedName("first_air_date")
    @ColumnInfo(name="first_air_date")
    private String firstAirDate;
    public static final String COLUMN_firstAirDate = "first_air_date";

    @SerializedName("id")
    @ColumnInfo(name="id")
    @PrimaryKey(autoGenerate = false)
    private Long id;
    public static final String COLUMN_id = "id";

    @SerializedName("name")
    @ColumnInfo(name="name")
    private String name;
    public static final String COLUMN_name = "name";

    @SerializedName("original_language")
    @ColumnInfo(name="original_language")
    private String originalLanguage;
    public static final String COLUMN_originalLanguage = "original_language";

    @SerializedName("original_name")
    @ColumnInfo(name="original_name")
    private String originalName;
    public static final String COLUMN_originalName = "original_name";

    @SerializedName("overview")
    @ColumnInfo(name="overview")
    private String overview;
    public static final String COLUMN_overview = "overview";

    @SerializedName("popularity")
    @ColumnInfo(name="popularity")
    private Double popularity;
    public static final String COLUMN_popularity = "popularity";

    @SerializedName("poster_path")
    @ColumnInfo(name="poster_path")
    private String posterPath;
    public static final String COLUMN_posterPath = "poster_path";

    @SerializedName("vote_average")
    @ColumnInfo(name="vote_average")
    private Double voteAverage;
    public static final String COLUMN_voteAverage = "vote_average";

    @SerializedName("vote_count")
    @ColumnInfo(name="vote_count")
    private Long voteCount;
    public static final String COLUMN_voteCount = "vote_count";

    public static final ContentValues getContentValuesFromSeries(Series series) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_backdropPath, series.getBackdropPath());
        values.put(COLUMN_firstAirDate, series.getFirstAirDate());
        values.put(COLUMN_id, series.getId());
        values.put(COLUMN_name, series.getName());
        values.put(COLUMN_originalLanguage, series.getOriginalLanguage());
        values.put(COLUMN_originalName, series.getOriginalName());
        values.put(COLUMN_overview, series.getOverview());
        values.put(COLUMN_popularity, series.getPopularity());
        values.put(COLUMN_posterPath, series.getPosterPath());
        values.put(COLUMN_voteAverage, series.getVoteAverage());
        values.put(COLUMN_voteCount, series.getVoteCount());
        return values;
    }

    public static final Series getSeriesFromCursor(Cursor cursor) {
        Series series = new Series();

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_backdropPath) != -1) {
            series.backdropPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_backdropPath));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_firstAirDate) != -1) {
            series.firstAirDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_firstAirDate));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_id) != -1) {
            series.id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_id));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_name) != -1) {
            series.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_name));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_originalLanguage) != -1) {
            series.originalLanguage = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_originalLanguage));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_originalName) != -1) {
            series.originalName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_originalName));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_overview) != -1) {
            series.overview = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_overview));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_popularity) != -1) {
            series.popularity = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_popularity));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_posterPath) != -1) {
            series.posterPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_posterPath));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_voteAverage) != -1) {
            series.voteAverage = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_voteAverage));
        }

        if (MovieContentProvider.getIndexColumn(cursor, COLUMN_voteCount) != -1) {
            series.voteCount = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_voteCount));
        }
        return series;
    }

    public static Series fromContentValues(ContentValues values) {
        Series series = new Series();
        if (values.containsKey(COLUMN_backdropPath)) {
            series.backdropPath = values.getAsString(COLUMN_backdropPath);
        }

        if (values.containsKey(COLUMN_firstAirDate)) {
            series.firstAirDate = values.getAsString(COLUMN_firstAirDate);
        }

        if (values.containsKey(COLUMN_id)) {
            series.id = values.getAsLong(COLUMN_id);
        }

        if (values.containsKey(COLUMN_name)) {
            series.name = values.getAsString(COLUMN_name);
        }

        if (values.containsKey(COLUMN_originalLanguage)) {
            series.originalLanguage = values.getAsString(COLUMN_originalLanguage);
        }

        if (values.containsKey(COLUMN_originalName)) {
            series.originalName = values.getAsString(COLUMN_originalName);
        }

        if (values.containsKey(COLUMN_overview)) {
            series.overview = values.getAsString(COLUMN_overview);
        }

        if (values.containsKey(COLUMN_popularity)) {
            series.popularity = values.getAsDouble(COLUMN_popularity);
        }

        if (values.containsKey(COLUMN_posterPath)) {
            series.posterPath = values.getAsString(COLUMN_posterPath);
        }

        if (values.containsKey(COLUMN_voteAverage)) {
            series.voteAverage = values.getAsDouble(COLUMN_voteAverage);
        }

        if (values.containsKey(COLUMN_voteCount)) {
            series.voteCount = values.getAsLong(COLUMN_voteCount);
        }
        return series;
    }


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
