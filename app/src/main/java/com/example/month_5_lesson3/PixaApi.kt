package com.example.month_5_lesson3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {

    @GET("api/")
    fun getImage(
        @Query("key") key: String = "33930577-2776bed645d029e147edf245a",
        @Query("q") pictureWord: String,
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int,
    ): Call<PixaModel>
}