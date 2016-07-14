package com.opiumfive.searchitunes;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Result implements Parcelable {

    @SerializedName("wrapperType")
    @Expose
    private String wrapperType;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("artistId")
    @Expose
    private Integer artistId;
    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;
    @SerializedName("trackId")
    @Expose
    private Integer trackId;
    @SerializedName("artistName")
    @Expose
    private String artistName;
    @SerializedName("collectionName")
    @Expose
    private String collectionName;
    @SerializedName("trackName")
    @Expose
    private String trackName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String collectionCensoredName;
    @SerializedName("trackCensoredName")
    @Expose
    private String trackCensoredName;
    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String collectionViewUrl;
    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String previewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;
    @SerializedName("artworkUrl60")
    @Expose
    private String artworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String artworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double collectionPrice;
    @SerializedName("trackPrice")
    @Expose
    private Double trackPrice;
    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("collectionExplicitness")
    @Expose
    private String collectionExplicitness;
    @SerializedName("trackExplicitness")
    @Expose
    private String trackExplicitness;
    @SerializedName("discCount")
    @Expose
    private Integer discCount;
    @SerializedName("discNumber")
    @Expose
    private Integer discNumber;
    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;
    @SerializedName("trackNumber")
    @Expose
    private Integer trackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer trackTimeMillis;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("isStreamable")
    @Expose
    private Boolean isStreamable;
    @SerializedName("collectionHdPrice")
    @Expose
    private Double collectionHdPrice;
    @SerializedName("trackHdPrice")
    @Expose
    private Double trackHdPrice;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String contentAdvisoryRating;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("longDescription")
    @Expose
    private String longDescription;
    @SerializedName("collectionArtistId")
    @Expose
    private Integer collectionArtistId;
    @SerializedName("collectionArtistName")
    @Expose
    private String collectionArtistName;

    /**
     *
     * @return
     *     The wrapperType
     */
    public String getWrapperType() {
        return wrapperType;
    }

    /**
     *
     * @param wrapperType
     *     The wrapperType
     */
    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    /**
     *
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     *
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     *
     * @return
     *     The artistId
     */
    public Integer getArtistId() {
        return artistId;
    }

    /**
     *
     * @param artistId
     *     The artistId
     */
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    /**
     *
     * @return
     *     The collectionId
     */
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     *
     * @param collectionId
     *     The collectionId
     */
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    /**
     *
     * @return
     *     The trackId
     */
    public Integer getTrackId() {
        return trackId;
    }

    /**
     *
     * @param trackId
     *     The trackId
     */
    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    /**
     *
     * @return
     *     The artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     *
     * @param artistName
     *     The artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     *
     * @return
     *     The collectionName
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     *
     * @param collectionName
     *     The collectionName
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     *
     * @return
     *     The trackName
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     *
     * @param trackName
     *     The trackName
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     *
     * @return
     *     The collectionCensoredName
     */
    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    /**
     *
     * @param collectionCensoredName
     *     The collectionCensoredName
     */
    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    /**
     *
     * @return
     *     The trackCensoredName
     */
    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    /**
     *
     * @param trackCensoredName
     *     The trackCensoredName
     */
    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    /**
     *
     * @return
     *     The artistViewUrl
     */
    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    /**
     *
     * @param artistViewUrl
     *     The artistViewUrl
     */
    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    /**
     *
     * @return
     *     The collectionViewUrl
     */
    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    /**
     *
     * @param collectionViewUrl
     *     The collectionViewUrl
     */
    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    /**
     *
     * @return
     *     The trackViewUrl
     */
    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    /**
     *
     * @param trackViewUrl
     *     The trackViewUrl
     */
    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    /**
     *
     * @return
     *     The previewUrl
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     *
     * @param previewUrl
     *     The previewUrl
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     *
     * @return
     *     The artworkUrl30
     */
    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    /**
     *
     * @param artworkUrl30
     *     The artworkUrl30
     */
    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    /**
     *
     * @return
     *     The artworkUrl60
     */
    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    /**
     *
     * @param artworkUrl60
     *     The artworkUrl60
     */
    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    /**
     *
     * @return
     *     The artworkUrl100
     */
    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    /**
     *
     * @param artworkUrl100
     *     The artworkUrl100
     */
    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    /**
     *
     * @return
     *     The collectionPrice
     */
    public Double getCollectionPrice() {
        return collectionPrice;
    }

    /**
     *
     * @param collectionPrice
     *     The collectionPrice
     */
    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    /**
     *
     * @return
     *     The trackPrice
     */
    public Double getTrackPrice() {
        return trackPrice;
    }

    /**
     *
     * @param trackPrice
     *     The trackPrice
     */
    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }

    /**
     *
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     *
     * @param releaseDate
     *     The releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     *
     * @return
     *     The collectionExplicitness
     */
    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    /**
     *
     * @param collectionExplicitness
     *     The collectionExplicitness
     */
    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    /**
     *
     * @return
     *     The trackExplicitness
     */
    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    /**
     *
     * @param trackExplicitness
     *     The trackExplicitness
     */
    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    /**
     *
     * @return
     *     The discCount
     */
    public Integer getDiscCount() {
        return discCount;
    }

    /**
     *
     * @param discCount
     *     The discCount
     */
    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    /**
     *
     * @return
     *     The discNumber
     */
    public Integer getDiscNumber() {
        return discNumber;
    }

    /**
     *
     * @param discNumber
     *     The discNumber
     */
    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    /**
     *
     * @return
     *     The trackCount
     */
    public Integer getTrackCount() {
        return trackCount;
    }

    /**
     *
     * @param trackCount
     *     The trackCount
     */
    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    /**
     *
     * @return
     *     The trackNumber
     */
    public Integer getTrackNumber() {
        return trackNumber;
    }

    /**
     *
     * @param trackNumber
     *     The trackNumber
     */
    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     *
     * @return
     *     The trackTimeMillis
     */
    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    /**
     *
     * @param trackTimeMillis
     *     The trackTimeMillis
     */
    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    /**
     *
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return
     *     The primaryGenreName
     */
    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    /**
     *
     * @param primaryGenreName
     *     The primaryGenreName
     */
    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    /**
     *
     * @return
     *     The isStreamable
     */
    public Boolean getIsStreamable() {
        return isStreamable;
    }

    /**
     *
     * @param isStreamable
     *     The isStreamable
     */
    public void setIsStreamable(Boolean isStreamable) {
        this.isStreamable = isStreamable;
    }

    /**
     *
     * @return
     *     The collectionHdPrice
     */
    public Double getCollectionHdPrice() {
        return collectionHdPrice;
    }

    /**
     *
     * @param collectionHdPrice
     *     The collectionHdPrice
     */
    public void setCollectionHdPrice(Double collectionHdPrice) {
        this.collectionHdPrice = collectionHdPrice;
    }

    /**
     *
     * @return
     *     The trackHdPrice
     */
    public Double getTrackHdPrice() {
        return trackHdPrice;
    }

    /**
     *
     * @param trackHdPrice
     *     The trackHdPrice
     */
    public void setTrackHdPrice(Double trackHdPrice) {
        this.trackHdPrice = trackHdPrice;
    }

    /**
     *
     * @return
     *     The contentAdvisoryRating
     */
    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    /**
     *
     * @param contentAdvisoryRating
     *     The contentAdvisoryRating
     */
    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    /**
     *
     * @return
     *     The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     *
     * @param shortDescription
     *     The shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     *
     * @return
     *     The longDescription
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     *
     * @param longDescription
     *     The longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     *
     * @return
     *     The collectionArtistId
     */
    public Integer getCollectionArtistId() {
        return collectionArtistId;
    }

    /**
     *
     * @param collectionArtistId
     *     The collectionArtistId
     */
    public void setCollectionArtistId(Integer collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }

    /**
     *
     * @return
     *     The collectionArtistName
     */
    public String getCollectionArtistName() {
        return collectionArtistName;
    }

    /**
     *
     * @param collectionArtistName
     *     The collectionArtistName
     */
    public void setCollectionArtistName(String collectionArtistName) {
        this.collectionArtistName = collectionArtistName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                wrapperType,
                kind,
                String.valueOf(artistId),
                String.valueOf(collectionId),
                String.valueOf(trackId),
                artistName,
                collectionName,
                trackName,
                collectionCensoredName,
                trackCensoredName,
                artistViewUrl,
                collectionViewUrl,
                trackViewUrl,
                previewUrl,
                artworkUrl30,
                artworkUrl60,
                artworkUrl100,
                String.valueOf(collectionPrice),
                String.valueOf(trackPrice),
                releaseDate,
                collectionExplicitness,
                trackExplicitness,
                String.valueOf(discCount),
                String.valueOf(discNumber),
                String.valueOf(trackCount),
                String.valueOf(trackNumber),
                String.valueOf(trackTimeMillis),
                country,
                currency,
                primaryGenreName,
                String.valueOf(isStreamable),
                String.valueOf(collectionHdPrice),
                String.valueOf(trackHdPrice),
                contentAdvisoryRating,
                shortDescription,
                longDescription,
                String.valueOf(collectionArtistId),
                collectionArtistName });
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        // распаковываем объект из Parcel
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
    private Result(Parcel parcel) {
        String[] data = new String[38];
        parcel.readStringArray(data);
        wrapperType = data[0];
        kind = data[1];
        artistId = Integer.parseInt(data[2]);
        collectionId = Integer.parseInt(data[3]);
        trackId = Integer.parseInt(data[4]);
        artistName = data[5];
        collectionName = data[6];
        trackName = data[7];
        collectionCensoredName = data[8];
        trackCensoredName = data[9];
        artistViewUrl = data[10];
        collectionViewUrl = data[11];
        trackViewUrl = data[12];
        previewUrl = data[13];
        artworkUrl30 = data[14];
        artworkUrl60 = data[15];
        artworkUrl100 = data[16];
        collectionPrice = Double.parseDouble(data[17]);
        trackPrice = Double.parseDouble(data[18]);
        releaseDate = data[19];
        collectionExplicitness = data[20];
        trackExplicitness = data[21];
        discCount = Integer.parseInt(data[22]);
        discNumber = Integer.parseInt(data[23]);
        trackCount = Integer.parseInt(data[24]);
        trackNumber = Integer.parseInt(data[25]);
        trackTimeMillis = Integer.parseInt(data[26]);
        country = data[27];
        currency = data[28];
        primaryGenreName = data[29];
        isStreamable = Boolean.parseBoolean(data[30]);
        collectionHdPrice = Double.parseDouble(data[31]);
        trackHdPrice = Double.parseDouble(data[32]);
        contentAdvisoryRating = data[33];
        shortDescription = data[34];
        longDescription = data[35];
        collectionArtistId = Integer.parseInt(data[36]);
        collectionArtistName = data[37];
    }
}