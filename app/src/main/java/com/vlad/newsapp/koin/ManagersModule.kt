package com.ua.net.apteka.di.koin


import com.vlad.newsapp.data.repository.NewsRepository
import com.vlad.newsapp.data.repository.NewsRepositoryImpl
import org.koin.dsl.module

val managersModule = module {

    single<NewsRepository> { NewsRepositoryImpl(get()) }

}