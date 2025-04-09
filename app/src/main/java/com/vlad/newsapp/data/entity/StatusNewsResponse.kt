package com.vlad.newsapp.data.entity

data class StatusNewsResponse (
    val status: String?,
    val totalResults: Int?,
    val articles: List<ItemPreviewNews>
)