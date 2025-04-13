package com.vlad.newsapp.data

import com.vlad.newsapp.data.entity.StatusNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRequest {
    @GET("everything")
    suspend fun getEverythingBySearch(@Query("q") query: String = "bitcoin",
                                      @Query("page") page: Int = 1,
                                      @Query("pageSize") pageSize: Int = 20
    ): StatusNewsResponse

    @GET("top-headlines")
    suspend fun getHeadlinesByCategory(@Query("category") category: String = "entertainment",
                                       @Query("language") language: String? = null,
                                       @Query("country") country: String? = null,
                                        ): StatusNewsResponse
}