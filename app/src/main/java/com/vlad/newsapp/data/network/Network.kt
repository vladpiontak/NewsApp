package com.vlad.newsapp.data.network

import com.vlad.newsapp.data.network.api_service.NewsService
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
        val request = chain.request().newBuilder().addHeader("X-Api-Key", TOKEN_2)
            .build()
        val response = chain.proceed(request)
        return response
    }
}

private const val TOKEN_1 = "3192a1bdf3954c7780708cf1611a4567"
private const val TOKEN_2 = "885eaadcecc24ac3a357d792cf986fbb"
private const val TOKEN_3 ="a109bc03fb0e4d6cbf00112a914d027b"
private const val TOKEN_4 ="b9a1d75ec42c462398e05106b1f8ac76"