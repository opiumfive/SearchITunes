package com.opiumfive.searchitunes.model;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;


public class Songs implements Parcelable {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;

    @SerializedName("results")
    @Expose
    private List<Song> results = new ArrayList<>();

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Song> getResults() {
        return results;
    }

    public void setResults(List<Song> results) {
        this.results = results;
    }

    public Songs() {}

    public Songs(Parcel in) {
        int size = in.readInt();
        for(int i = 0; i < size; i++) {
            results.add( ((Song) in.readParcelable(((Object) this).getClass().getClassLoader())));
        }
    }

    public static Creator<Songs> CREATOR = new Creator<Songs>() {
        public Songs createFromParcel(Parcel source) {
            return new Songs(source);
        }

        public Songs[] newArray(int size) {
            return new Songs[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(results == null ? 0 : results.size());
        for (Song item: results) parcel.writeParcelable(item, i);
    }
}