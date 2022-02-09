package com.pawan.jetpackandroidapp.repository

import androidx.lifecycle.LiveData
import com.pawan.jetpackandroidapp.db.AppDatabase
import com.pawan.jetpackandroidapp.db.entity.ArticleEntity
import com.pawan.jetpackandroidapp.di.Transformer.convertArticleModelToArticleEntity
import com.pawan.jetpackandroidapp.models.Article
import javax.inject.Inject

class DBRepository @Inject constructor(val appDatabase: AppDatabase) {

    suspend fun insertArticle(article: Article) {
        return appDatabase.articleDao()
            .insert(convertArticleModelToArticleEntity(article))
    }

    suspend fun delete(article: Article) {
        appDatabase.articleDao().delete(convertArticleModelToArticleEntity(article))
    }

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    fun getAllArticles(): LiveData<List<ArticleEntity>> {
        return appDatabase.articleDao().getAllOfflineArticles()
    }
}