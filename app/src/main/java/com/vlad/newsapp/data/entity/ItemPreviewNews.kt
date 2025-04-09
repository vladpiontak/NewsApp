package com.vlad.newsapp.data.entity

import com.google.gson.annotations.SerializedName

data class ItemPreviewNews(
    val id: Int?,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    @SerializedName("urlToImage")
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,
    )

data class Source(
    val id: String?,
    val name: String?
)