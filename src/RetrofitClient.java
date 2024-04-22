// RetrofitClient.java
package com.snapsales.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.util.Log;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.upcitemdb.com/prod/trial/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            try {
                retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            } catch (Exception e) {
                Log.e("RetrofitClient", "Error creating Retrofit instance: " + e.getMessage());
            }
        }
        return retrofit;
    }
}