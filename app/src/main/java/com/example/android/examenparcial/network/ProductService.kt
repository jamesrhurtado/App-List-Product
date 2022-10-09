package com.example.android.examenparcial.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductService {
    @GET("search")
    fun getProduct(@Header("x-api-key") apiKey: String, @Query("query") product: String): Call<ApiResponseDetails>

}