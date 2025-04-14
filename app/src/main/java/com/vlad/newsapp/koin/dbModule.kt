package com.vlad.newsapp.koin

import android.app.Application
import androidx.room.Room
import com.vlad.newsapp.data.database.AppDatabase
import com.vlad.newsapp.utils.constants.DATABASE_NAME
import org.koin.dsl.module

val dbModule = module {

    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    single { get<AppDatabase>().newsDao() }
}