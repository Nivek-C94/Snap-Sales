// ProductApiService.java
package com.snapsales.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApiService {
    @GET("lookup")
    Call<ProductResponse> getProductInfo(@Query("upc") String upcCode);
}