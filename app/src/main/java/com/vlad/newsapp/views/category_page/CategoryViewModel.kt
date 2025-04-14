package com.vlad.newsapp.views.category_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.data.repository.NewsRepository
import com.vlad.newsapp.utils.constants.DEFAULT_CATEGORY
import com.vlad.newsapp.utils.constants.DEFAULT_NONE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: NewsRepository): ViewModel() {
    var filterByCategory: MutableStateFlow<String> = MutableStateFlow(DEFAULT_CATEGORY)
    var filterByLanguage: MutableStateFlow<String> = MutableStateFlow(DEFAULT_NONE)
    var filterByCountry: MutableStateFlow<String> = MutableStateFlow(DEFAULT_NONE)
    val combinedFlow = combine(filterByCategory, filterByLanguage, filterByCountry) { category, language, country ->
        arrayListOf(category, language, country)
    }

    private val _data = MutableStateFlow<List<ItemPreviewNews>>(listOf())
    val data: StateFlow<List<ItemPreviewNews>> = _data

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    init {
        registerChangeFilter()
        getDataByCategory()
    }


    fun getDataByCategory(category: String? = DEFAULT_CATEGORY, language: String? = null, country: String? = null) {
        viewModelScope.launch {
            if (isLoading || isLastPage) return@launch
            isLoading = true
            try {
                val response = repository.getHeadlinesByCategory(category, language, country, page = currentPage)
                val currentList = _data.value.toMutableList()
                currentList.addAll(response.articles)
                _data.emit(currentList)

                if (response.articles.size < 10) {
                    isLastPage = true
                } else {
                    currentPage++
                }
            } catch (e: Exception) {

            } finally {
                isLoading = false
            }

        }
    }

    private  fun registerChangeFilter(){
        viewModelScope.launch {
            combinedFlow.collect{
                _data.value = listOf()
                currentPage=1
                isLoading = false
                isLastPage = false
                val category = remNone(it[0])
                val language = remNone(it[1])
                val country = remNone(it[2])
                    getDataByCategory(category, language, country)
            }
        }
    }

    private fun remNone(value: String): String?{
        return if (value == DEFAULT_NONE) null else value
    }
}