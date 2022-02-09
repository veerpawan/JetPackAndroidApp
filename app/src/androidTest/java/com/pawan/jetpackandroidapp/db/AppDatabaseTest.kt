package com.pawan.jetpackandroidapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.pawan.jetpackandroidapp.db.dao.ArticleDao
import com.pawan.jetpackandroidapp.db.entity.ArticleEntity
import com.pawan.jetpackandroidapp.db.entity.SourceEntity
import com.pawan.jetpackandroidapp.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        articleDao = database.articleDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArticles() = runBlockingTest {
        val  sourceEntity = SourceEntity("1","SVD")
        val articleEntity = ArticleEntity(id = 1,"name", "2", "10f", "url",sourceEntity,"Dsd","dsds","Dssd" )
        articleDao.insert(articleEntity)

        val allArticlesItems = articleDao.getAllOfflineArticles().getOrAwaitValue()

        assertThat(allArticlesItems).contains(articleEntity)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val  sourceEntity = SourceEntity("1","SVD")
        val articleEntity = ArticleEntity(id = 1,"name", "2", "10f", "url",sourceEntity,"Dsd","dsds","Dssd" )
        articleDao.insert(articleEntity)
        articleDao.delete(articleEntity)

        val allArticlesItems = articleDao.getAllOfflineArticles().getOrAwaitValue()

        assertThat(allArticlesItems).doesNotContain(articleEntity)
    }

    @Test
    fun observeAllOfflineArticles() = runBlockingTest {

        val  sourceEntity = SourceEntity("1","SVD")
        val articleEntity1 = ArticleEntity(id = 1,"name", "2", "10f", "url",sourceEntity,"Dsd","dsds","Dssd" )
        val articleEntity2 = ArticleEntity(id = 1,"name", "2", "10f", "url",sourceEntity,"Dsd","dsds","Dssd" )
        val articleEntity3 = ArticleEntity(id = 1,"name", "2", "10f", "url",sourceEntity,"Dsd","dsds","Dssd" )
        articleDao.insert(articleEntity1)
        articleDao.insert(articleEntity2)
        articleDao.insert(articleEntity3)

        val totalPriceSum = articleDao.getAllOfflineArticles()

        assertThat(totalPriceSum.getOrAwaitValue().isNotEmpty()).isTrue()
    }

    @Test
    fun observeAllOfflineArticlesIfNull() = runBlockingTest {
        val totalPriceSum = articleDao.getAllOfflineArticles()
        assertThat(totalPriceSum.getOrAwaitValue().isNotEmpty()).isFalse()
    }
}