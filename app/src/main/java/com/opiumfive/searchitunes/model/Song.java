package com.opiumfive.searchitunes.model;


import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Song implements Parcelable {

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;

    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;

    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                artistName,
                trackName,
                previewUrl,
                artworkUrl100,
                String.valueOf(trackTimeMillis),
        });
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    private Song(Parcel parcel) {
        String[] data = new String[5];
        parcel.readStringArray(data);
        artistName = data[0];
        trackName = data[1];
        previewUrl = data[2];
        artworkUrl100 = data[3];
        trackTimeMillis = Integer.parseInt(data[4]);
    }
}