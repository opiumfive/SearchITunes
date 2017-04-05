package com.opiumfive.searchitunes.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {

    private static final String BASE_URL = "https://itunes.apple.com/";

    private static volatile IApi mApiServiceInstance;

    // making quick lazy retrofit singleton with Double Checked Locking & volatile
    // volatile is recommended but not necessary since java 5
    public static IApi getApiService() {
        IApi localInstance = mApiServiceInstance;
        if (localInstance == null) {
            synchronized (IApi.class) {
                localInstance = mApiServiceInstance;
                if (localInstance == null) {
                    Retrofit retrofit = Api.getRetrofit();
                    mApiServiceInstance = localInstance = retrofit.create(IApi.class);
                }
            }
        }
        return localInstance;
    }

    private static Retrofit getRetrofit() {
        OkHttpClient okClient = new OkHttpClient.Builder().build();
        Gson gson = new GsonBuilder().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okClient)
                .build();
    }
}
