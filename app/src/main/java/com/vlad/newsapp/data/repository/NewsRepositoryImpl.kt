package com.vlad.newsapp.data.repository

import android.util.Log
import com.vlad.newsapp.data.database.AppDatabase
import com.vlad.newsapp.data.database.NewsDao
import com.vlad.newsapp.data.database.entity.NewsEntity
import com.vlad.newsapp.data.database.entity.QueryEntity
import com.vlad.newsapp.data.mapper.NewsMapper
import com.vlad.newsapp.data.network.api_service.NewsService
import com.vlad.newsapp.data.model.StatusNewsResponse
import com.vlad.newsapp.utils.constants.DEFAULT_CATEGORY
import com.vlad.newsapp.utils.constants.DEFAULT_SEARCH
import com.vlad.newsapp.utils.constants.STATUS_OK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl( private val apiService: NewsService, private val db: NewsDao, private val mapper: NewsMapper): NewsRepository{

//    override suspend fun getArticlesBySearch(query: String, page: Int): StatusNewsResponse {
//        return withContext(Dispatchers.IO){
//            apiService.getEverythingBySearch(query, page)
//
//        }
//    }
    override suspend fun getArticlesBySearch(query: String, page: Int): StatusNewsResponse {
        return withContext(Dispatchers.IO){
            val cachedNews: List<NewsEntity>? = try {
                db.getNewsBySearch(query, page)
            }catch (e: Exception){
                Log.d("ee", "Load error", e)
                listOf()
            }

            if (cachedNews?.isNotEmpty() ?: false) {
                return@withContext StatusNewsResponse(
                    status = STATUS_OK,
                    totalResults = cachedNews?.size,
                    articles = cachedNews?.map {
                        mapper.newsToItemPreviewNews(it)
                    } ?: listOf()
                )
            }

            val response =
                apiService.getEverythingBySearch(query, page)

            if (response.status == STATUS_OK) {
                val newsEntities = response.articles.map { article ->
                    mapper.itemPreviewNewsToNewsEntity(article, query, page)
                }
                try {
                    db.insertNews(newsEntities)
                }catch (e: Exception){
                    Log.d("ee", "Insert error", e)
                }
            }
            response
        }
    }
    override suspend fun getHeadlinesByCategory(category: String?, language: String?, country: String?, page: Int): StatusNewsResponse {
        return withContext(Dispatchers.IO){
            val cachedNews: List<QueryEntity>? = try {
                db.getNewsByCategory(category ?: "", language ?: "", country ?: "", page)
            }catch (e: Exception){
                Log.d("ee", "Load error", e)
                listOf()
            }

            if (cachedNews?.isNotEmpty() ?: false) {
                return@withContext StatusNewsResponse(
                    status = STATUS_OK,
                    totalResults = cachedNews?.size,
                    articles = cachedNews?.map {
                        mapper.queryEntityToItemPreviewNews(it)
                    } ?: listOf()
                )
            }

            val response =
                apiService.getHeadlinesByCategory(category ?: DEFAULT_CATEGORY, language, country, page)

            if (response.status == STATUS_OK) {
                val newsEntities = response.articles.map { article ->
                    mapper.itemPreviewNewsToQueryEntity(article,category ?: "", language ?: "", country ?: "", page)
                }
                try {
                    db.insertQuery(newsEntities)
                }catch (e: Exception){
                    Log.d("ee", "Insert error", e)
                }
            }
            response
        }
    }



}