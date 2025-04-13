package com.vlad.newsapp.data.network.api_service

import com.vlad.newsapp.data.model.StatusNewsResponse
import com.vlad.newsapp.utils.constants.DEFAULT_CATEGORY
import com.vlad.newsapp.utils.constants.DEFAULT_SEARCH
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getEverythingBySearch(@Query("q") query: String = DEFAULT_SEARCH,
                                      @Query("page") page: Int = 1,
                                      @Query("pageSize") pageSize: Int = 20
    ): StatusNewsResponse

    @GET("top-headlines")
    suspend fun getHeadlinesByCategory(@Query("category") category: String = DEFAULT_CATEGORY,
                                       @Query("language") language: String? = null,
                                       @Query("country") country: String? = null,
                                       @Query("page") page: Int = 1,
                                       @Query("pageSize") pageSize: Int = 20
                                        ): StatusNewsResponse
}