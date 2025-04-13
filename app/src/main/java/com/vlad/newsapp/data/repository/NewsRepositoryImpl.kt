package com.vlad.newsapp.data.repository

import com.vlad.newsapp.data.network.api_service.NewsService
import com.vlad.newsapp.data.model.StatusNewsResponse
import com.vlad.newsapp.utils.constants.DEFAULT_CATEGORY
import com.vlad.newsapp.utils.constants.DEFAULT_SEARCH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl( private val apiService: NewsService): NewsRepository{
    override suspend fun getArticlesBySearch(query: String, page: Int): StatusNewsResponse {
        return withContext(Dispatchers.IO){
            apiService.getEverythingBySearch(query, page)
        }
    }

    override suspend fun getHeadlinesByCategory(category: String?, language: String?, country: String?, page: Int): StatusNewsResponse {
        return withContext(Dispatchers.IO){
            apiService.getHeadlinesByCategory(category ?: DEFAULT_CATEGORY, language, country, page)
        }
    }



}