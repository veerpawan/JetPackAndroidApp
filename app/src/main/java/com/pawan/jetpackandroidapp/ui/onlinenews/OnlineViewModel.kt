package com.pawan.jetpackandroidapp.ui.onlinenews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pawan.jetpackandroidapp.models.NewResponse
import com.pawan.jetpackandroidapp.repository.NetworkRepository
import com.pawan.jetpackandroidapp.ui.viewmodels.BaseViewModel
import com.pawan.jetpackandroidapp.utils.Constants.API_KEY
import com.pawan.jetpackandroidapp.utils.Constants.COUNTRY_CODE
import com.pawan.jetpackandroidapp.utils.DataHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
 class OnlineViewModel @Inject constructor(private val networkRepository: NetworkRepository) : BaseViewModel() {

    private val _topHeadlines = MutableLiveData<DataHandler<NewResponse>>()
    val topHeadlines: LiveData<DataHandler<NewResponse>> = _topHeadlines

    fun getTopHeadlines() {
        _topHeadlines.postValue(DataHandler.LOADING())
        viewModelScope.launch {
            val response = networkRepository.getTopHeadlines(COUNTRY_CODE, API_KEY)
            _topHeadlines.postValue(handleResponse(response))
        }
    }

    private fun handleResponse(response: Response<NewResponse>): DataHandler<NewResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return DataHandler.SUCCESS(it)
            }
        }
        return DataHandler.ERROR(message = response.errorBody().toString())
    }
}