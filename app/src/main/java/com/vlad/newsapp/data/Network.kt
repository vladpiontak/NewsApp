package com.vlad.newsapp.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network() {
    private val okHttp = OkHttpClient.Builder().addInterceptor(MainInterceptor()).build()

    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)
        .build()
}


class MainInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("X-Api-Key", "3192a1bdf3954c7780708cf1611a4567")
            .build()
        val response = chain.proceed(request)
        return response
    }
}