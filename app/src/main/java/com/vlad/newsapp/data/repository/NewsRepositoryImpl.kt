package com.vlad.newsapp.data.repository

import com.vlad.newsapp.data.Network
import com.vlad.newsapp.data.NewsRequest
import com.vlad.newsapp.data.entity.StatusNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NewsRepositoryImpl: NewsRepository{
    private val network = Network().retrofitBuilder.create(NewsRequest::class.java)

    override suspend fun getArticlesBySearch(query: String): StatusNewsResponse {
        return withContext(Dispatchers.IO){
            network.getEverythingBySearch()
        }
    }

    override suspend fun getHeadlinesByCategory(category: String?, language: String?, country: String?): StatusNewsResponse {
        return withContext(Dispatchers.IO){
            network.getHeadlinesByCategory(category ?: "entertainment", language, country)
        }
    }
}