package com.vlad.newsapp.data.repository

import com.vlad.newsapp.data.entity.StatusNewsResponse

interface NewsRepository{
    suspend fun getArticlesBySearch(query: String): StatusNewsResponse
    suspend fun getHeadlinesByCategory(category: String?, language: String?, country: String?): StatusNewsResponse
}