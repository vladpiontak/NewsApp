package com.vlad.newsapp.data.network

import com.vlad.newsapp.data.network.api_service.NewsService
import com.vlad.newsapp.utils.constants.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { createOkkHttp() }
    single { getRetrofit(get()) }
    single { getApiService(get()) }
}

private fun createOkkHttp(): OkHttpClient {
    val okHttp = OkHttpClient.Builder()
        .addInterceptor(MainInterceptor())

    return okHttp.build()
}

private fun getRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
}


private fun getApiService(retrofit: Retrofit): NewsService {
    return retrofit.create(NewsService::class.java)
}



class MainInterceptor: Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader("X-Api-Key", API_KEY)
            .build()
        val response = chain.proceed(request)
        return response
    }
}



