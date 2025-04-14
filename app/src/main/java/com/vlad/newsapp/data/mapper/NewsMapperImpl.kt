package com.vlad.newsapp.data.mapper

import com.vlad.newsapp.data.database.entity.NewsEntity
import com.vlad.newsapp.data.database.entity.QueryEntity
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.data.model.Source

class NewsMapperImpl: NewsMapper {
    override fun newsToItemPreviewNews(item: NewsEntity) = ItemPreviewNews(
        source = Source(null, name = item.name),
        author = item.author,
        title = item.title,
        description = item.description,
        url = item.url,
        urlImage = item.urlImage,
        publishedAt = item.publishedAt,
        content = item.content,
    )

    override fun itemPreviewNewsToNewsEntity(item: ItemPreviewNews, queryName: String, queryPage: Int) = NewsEntity(
        author = item.author,
        title = item.title ?:"",
        description = item.description,
        url = item.url,
        urlImage = item.urlImage,
        publishedAt = item.publishedAt,
        content = item.content,
        name = item.source?.name,
        queryName = queryName,
        queryPage = queryPage
    )


    override fun queryEntityToItemPreviewNews(item: QueryEntity) = ItemPreviewNews(
        source = Source(null, name = item.name),
        author = item.author,
        title = item.title,
        description = item.description,
        url = item.url,
        urlImage = item.urlImage,
        publishedAt = item.publishedAt,
        content = item.content,
    )

    override fun itemPreviewNewsToQueryEntity(item: ItemPreviewNews,   queryCategory: String, queryLanguage: String, queryCountry: String, queryPage: Int) = QueryEntity(
        author = item.author,
        title = item.title ?:"",
        description = item.description,
        url = item.url,
        urlImage = item.urlImage,
        publishedAt = item.publishedAt,
        content = item.content,
        name = item.source?.name,
        queryCategory = queryCategory,
        queryLanguage = queryLanguage,
        queryCountry = queryCountry,
        queryPage = queryPage
    )
}