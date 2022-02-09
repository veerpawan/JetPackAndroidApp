package com.pawan.jetpackandroidapp.network

import com.pawan.jetpackandroidapp.models.NewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopHeadlinesApi {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Response<NewResponse>
}