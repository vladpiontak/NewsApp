package com.vlad.newsapp.views.main_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.vlad.newsapp.data.model.ItemPreviewNews
import com.vlad.newsapp.data.repository.NewsRepository
import com.vlad.newsapp.utils.constants.DEFAULT_SEARCH
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository): ViewModel() {
    private val _queryText = MutableStateFlow<String>(DEFAULT_SEARCH)
    val queryText: StateFlow<String> = _queryText

    private val _data = MutableStateFlow<List<ItemPreviewNews>>(listOf())
    val data: StateFlow<List<ItemPreviewNews>> = _data

    private var isPressSearch: Boolean = false

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false
    init {
        getDataBySearch()
        changeQueryText()
    }

    fun getDataBySearch(query: String = DEFAULT_SEARCH) {
        viewModelScope.launch {
            if (isLoading || isLastPage) return@launch
                isLoading = true
                try {
                    val response = repository.getArticlesBySearch(query, page = currentPage)
                    val currentList = _data.value.toMutableList()
                    currentList.addAll(response.articles)
                    _data.emit(currentList)


                    currentPage++

                } catch (e: Exception) {

                } finally {
                    isLoading = false
                }

        }
    }

    fun updateQueryText(query: String, isPressSearch: Boolean = false){
        _queryText.tryEmit(query)
        this.isPressSearch = isPressSearch

    }

    private fun changeQueryText(){
        viewModelScope.launch{
            queryText.debounce(0).collect{
                _data.value = listOf()
                currentPage=1
                isLoading = false
               isLastPage = false
                getDataBySearch(it)
            }
        }
    }

}