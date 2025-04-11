package com.vlad.newsapp.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network() {
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(MainInterceptor())

        .build()


    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp)
        .build()
}


class MainInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("X-Api-Key", TOKEN_2)
            .build()
        val response = chain.proceed(request)
        return response
    }
}

private const val TOKEN_1 = "885eaadcecc24ac3a357d792cf986fbb"
private const val TOKEN_2 = "885eaadcecc24ac3a357d792cf986fbb"