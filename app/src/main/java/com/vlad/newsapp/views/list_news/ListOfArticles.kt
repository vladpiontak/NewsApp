package com.vlad.newsapp.views.list_news

import com.vlad.newsapp.data.entity.ItemPreviewNews

interface ListOfArticles {
    fun updateListOfArticles(articles: List<ItemPreviewNews>)
}