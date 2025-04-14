package com.vlad.newsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlad.newsapp.data.database.entity.NewsEntity
import com.vlad.newsapp.data.database.entity.QueryEntity

@Database(
    entities = [QueryEntity::class, NewsEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

}