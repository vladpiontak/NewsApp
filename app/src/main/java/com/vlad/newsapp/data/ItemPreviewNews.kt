package com.vlad.newsapp.data

import android.media.Image

data class ItemPreviewNews(
    val id: Int?,
    val source: Source?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlImage: String?,
    val publishedAt: String?,
    val content: String?,

)

data class Source(
    val id: Int?,
    val name: String?
)