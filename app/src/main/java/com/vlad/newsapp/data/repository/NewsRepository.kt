package com.vlad.newsapp.data.repository

import com.vlad.newsapp.data.model.StatusNewsResponse

interface NewsRepository{
    suspend fun getArticlesBySearch(query: String, page: Int): StatusNewsResponse
    suspend fun getHeadlinesByCategory(category: String?, language: String?, country: String?, page: Int): StatusNewsResponse

}