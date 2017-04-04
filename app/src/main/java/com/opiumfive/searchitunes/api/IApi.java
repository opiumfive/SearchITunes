package com.opiumfive.searchitunes.api;


import com.opiumfive.searchitunes.model.Songs;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IApi {

    @GET("search")
    Call<Songs> listTracks(@Query("term") String q);
}
