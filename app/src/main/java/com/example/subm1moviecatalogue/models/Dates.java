
package com.example.subm1moviecatalogue.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Dates {

    @SerializedName("maximum")
    @Expose
    private String mMaximum;
    @SerializedName("minimum")
    @Expose
    private String mMinimum;

    public String getMaximum() {
        return mMaximum;
    }

    public void setMaximum(String maximum) {
        mMaximum = maximum;
    }

    public String getMinimum() {
        return mMinimum;
    }

    public void setMinimum(String minimum) {
        mMinimum = minimum;
    }

}
