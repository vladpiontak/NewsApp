package com.vlad.newsapp.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.data.model.Source

@Entity("query",
    indices = [Index(value = ["queryCategory", "queryLanguage", "queryCountry", "queryPage"])]
)
data class QueryEntity(
    val author: String?,
    @PrimaryKey val title: String,
    val description: String?,
    val url: String?,
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,
    val name: String?,
    val queryCategory: String,
    val queryLanguage: String,
    val queryCountry: String,
    val queryPage: Int
)