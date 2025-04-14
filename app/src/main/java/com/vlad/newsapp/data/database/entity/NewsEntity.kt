package com.vlad.newsapp.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.data.model.Source

@Entity("news",
    indices = [Index(value = ["queryPage", "queryName"])]
)
data class NewsEntity(
    val author: String?,
    @PrimaryKey val title: String,
    val description: String?,
    val url: String?,
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,
    val name: String?,
    val queryName: String,
    val queryPage: Int
)
