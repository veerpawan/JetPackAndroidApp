package com.pawan.jetpackandroidapp.repository

import android.util.Log
import com.pawan.jetpackandroidapp.network.TopHeadlinesApi
import com.pawan.jetpackandroidapp.models.NewResponse
import retrofit2.Response
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val topHeadlinesApi: TopHeadlinesApi
) {

    suspend fun getTopHeadlines(country: String, apiKey: String): Response<NewResponse> {
        Log.e("DVVDS",country)
        return topHeadlinesApi.getTopHeadlines(country, apiKey)
    }

}