package com.pawan.jetpackandroidapp.ui.onlinenews

import androidx.lifecycle.*
import com.pawan.jetpackandroidapp.di.Transformer
import com.pawan.jetpackandroidapp.models.Article
import com.pawan.jetpackandroidapp.repository.DBRepository
import com.pawan.jetpackandroidapp.ui.viewmodels.BaseViewModel
import com.pawan.jetpackandroidapp.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineViewModel @Inject constructor(private val dbRepository: DBRepository) : BaseViewModel() {


    /*Transformation converts the LiveData article entity to LiveData article model class
    * and LiveData Datahandler  is observed from fragment
    */
    var article = Transformations.map(dbRepository.getAllArticles()) { list ->

        val temp = list.map {
            Transformer.convertArticleEntityToArticleModel(it)
        }
        if (temp.isNullOrEmpty()) {
            DataHandler.ERROR(null, "LIST IS EMPTY OR NULL")
        } else {
            DataHandler.SUCCESS(temp)
        }
    }


    fun insertArticle(article: Article) {
        viewModelScope.launch {

            dbRepository.insertArticle(article)
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {

            dbRepository.delete(article)
        }
    }

    fun getAllArticles() = dbRepository.getAllArticles()


}