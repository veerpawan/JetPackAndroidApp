package com.pawan.jetpackandroidapp.models

import android.os.Parcelable
import com.pawan.jetpackandroidapp.models.Article
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
):Parcelable