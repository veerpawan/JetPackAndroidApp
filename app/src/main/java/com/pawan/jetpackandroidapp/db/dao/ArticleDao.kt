package com.pawan.jetpackandroidapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pawan.jetpackandroidapp.db.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: ArticleEntity)

    // NOTE - Since we are already using LIVE-DATA no need to use suspend function
    @Query("SELECT * FROM ARTICLE")
    fun getAllOfflineArticles():LiveData<List<ArticleEntity>>

    @Delete
    suspend fun delete(articleEntity: ArticleEntity)
}