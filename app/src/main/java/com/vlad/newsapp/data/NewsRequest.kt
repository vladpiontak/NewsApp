package com.vlad.newsapp.data

import com.vlad.newsapp.data.entity.StatusNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRequest {
    @GET("everything")
    fun getEverythingBySearch(@Query("q") q: String = "bitcoin", @Query("apiKey") apiKey: String = "3192a1bdf3954c7780708cf1611a4567"): Call<StatusNewsResponse>
}