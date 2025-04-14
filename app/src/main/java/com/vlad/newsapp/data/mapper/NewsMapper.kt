package com.vlad.newsapp.data.mapper

import com.vlad.newsapp.data.database.entity.NewsEntity
import com.vlad.newsapp.data.database.entity.QueryEntity
import com.vlad.newsapp.data.model.ItemPreviewNews

interface NewsMapper {
    fun newsToItemPreviewNews(item: NewsEntity): ItemPreviewNews
    fun itemPreviewNewsToNewsEntity(item: ItemPreviewNews, queryName: String, queryPage: Int): NewsEntity

    fun queryEntityToItemPreviewNews(item: QueryEntity): ItemPreviewNews
    fun itemPreviewNewsToQueryEntity(
        item: ItemPreviewNews,
        queryCategory: String,
        queryLanguage: String,
        queryCountry: String,
        queryPage: Int
    ): QueryEntity
}