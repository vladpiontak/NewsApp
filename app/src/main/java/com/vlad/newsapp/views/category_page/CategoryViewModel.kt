package com.vlad.newsapp.views.category_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlad.newsapp.data.entity.ItemPreviewNews
import com.vlad.newsapp.data.repository.NewsRepository
import com.vlad.newsapp.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {
    private val repository: NewsRepository = NewsRepositoryImpl

    var filterByCategory: MutableStateFlow<String> = MutableStateFlow("None")

    var filterByLanguage: MutableStateFlow<String> = MutableStateFlow("None")

    var filterByCountry: MutableStateFlow<String> = MutableStateFlow("None")

    val combinedFlow = combine(filterByCategory, filterByLanguage, filterByCountry) { category, language, country ->
        arrayListOf(category, language, country)
    }

    private val _data = MutableStateFlow<List<ItemPreviewNews>>(listOf())
    val data: StateFlow<List<ItemPreviewNews>> = _data

    init {
        registerChangeFilter()
        getDataByCategory()
    }


    fun getDataByCategory(category: String? = null, language: String? = null, country: String? = null) {
        viewModelScope.launch {
            repository.getHeadlinesByCategory(category, language, country).let {
                _data.tryEmit(it.articles)
                Log.d("ee", "data " + it.toString())
            }
        }
    }


    private  fun registerChangeFilter(){
        viewModelScope.launch {
            combinedFlow.collect{
                val category = remNone(it[0])
                val language = remNone(it[1])
                val country = remNone(it[2])
                    getDataByCategory(category, language, country)
            }
//            filterByCategory.collect(){
//
//            }
        }
    }

    private fun remNone(value: String): String?{
        return if (value == "None") null else value
    }
}