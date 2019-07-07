
package com.example.subm1moviecatalogue.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class MovieResult {

    @SerializedName("dates")
    @Expose
    private Dates mDates;
    @SerializedName("page")
    @Expose
    private Long mPage;
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> mResults;
    @SerializedName("total_pages")
    @Expose
    private Long mTotalPages;
    @SerializedName("total_results")
    @Expose
    private Long mTotalResults;

    public Dates getDates() {
        return mDates;
    }

    public void setDates(Dates dates) {
        mDates = dates;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public ArrayList<Movie> getResults() {
        return mResults;
    }

    public void setResults(ArrayList<Movie> results) {
        mResults = results;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
