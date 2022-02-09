package com.pawan.jetpackandroidapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pawan.jetpackandroidapp.db.dao.ArticleDao
import com.pawan.jetpackandroidapp.db.entity.ArticleEntity

@Database(
    version = 1,
    entities = [ArticleEntity::class],
)
@TypeConverters(Converter::class)
abstract class AppDatabase :RoomDatabase(){

    abstract fun articleDao(): ArticleDao
}