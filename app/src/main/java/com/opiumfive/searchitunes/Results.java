package com.opiumfive.searchitunes;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class Results implements Parcelable {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     *     The resultCount
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     *
     * @param resultCount
     *     The resultCount
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     *
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Results() {}

    public Results(Parcel in) {
        int size = in.readInt();
        for(int i = 0; i < size; i++) {
            results.add( ((Result) in.readParcelable(((Object) this).getClass().getClassLoader())));
        }

    }

    public static Creator<Results> CREATOR = new Creator<Results>() {
        public Results createFromParcel(Parcel source) {
            return new Results(source);
        }

        public Results[] newArray(int size) {
            return new Results[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(results.size());
        for(Result item: results) {
            parcel.writeParcelable(item, i);
        }

    }
}