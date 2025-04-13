package com.vlad.newsapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vlad.newsapp.data.network.api_service.NewsService
import com.vlad.newsapp.data.model.ItemPreviewNews

class ItemPagingSource(private val apiService: NewsService, private val query: String): PagingSource<Int, ItemPreviewNews>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemPreviewNews> {
        return try {
            val currentPage = params.key ?: 1
            val items = apiService.getEverythingBySearch(query = query, page = currentPage, pageSize = params.loadSize).articles
            LoadResult.Page(
                data = items,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (items.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemPreviewNews>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}