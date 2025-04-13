package com.ua.net.apteka.di.koin

import com.vlad.newsapp.views.category_page.CategoryViewModel
import com.vlad.newsapp.views.main_page.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CategoryViewModel(get()) }

}