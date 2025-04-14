package com.vlad.newsapp.application

import android.app.Application
import com.ua.net.apteka.di.koin.managersModule
import com.ua.net.apteka.di.koin.viewModelModule
import com.vlad.newsapp.data.network.networkModule
import com.vlad.newsapp.data.repository.NewsRepository
import com.vlad.newsapp.koin.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }
    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                managersModule,
                networkModule,
                viewModelModule,
                dbModule
            )
        }
    }
}
