package com.vlad.newsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vlad.newsapp.data.database.entity.NewsEntity
import com.vlad.newsapp.data.database.entity.QueryEntity


@Dao
interface NewsDao {
    @Query("SELECT * FROM news WHERE queryName = :name AND queryPage = :page")
    suspend fun getNewsBySearch(name: String, page: Int): List<NewsEntity>?

    @Query("SELECT * FROM `query` WHERE queryCategory = :category AND queryLanguage = :language AND queryCountry = :country AND queryPage = :page")
    suspend fun getNewsByCategory(category: String, language: String, country: String, page: Int): List<QueryEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(news: List<QueryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsEntity>)
}

