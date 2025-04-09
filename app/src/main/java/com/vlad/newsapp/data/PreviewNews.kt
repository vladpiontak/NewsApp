package com.vlad.newsapp.data

data class PreviewNews (
    val status: String,
    val totalResults: Int,
    val articles: ItemPreviewNews
)