package com.vlad.newsapp.data.model

data class StatusNewsResponse (
    val status: String?,
    val totalResults: Int?,
    val articles: List<ItemPreviewNews>
)