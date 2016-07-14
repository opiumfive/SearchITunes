package com.opiumfive.searchitunes;

/**
 * Created by allsw on 14.07.2016.
 */
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ItunesService {
    @GET("search")
    Call<Results> listTracks(@Query("term") String q, @Query("limit") int limit);
}
