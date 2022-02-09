package com.pawan.jetpackandroidapp.utils

import android.os.Build
import android.util.Log

object Constants {

    val API_KEY = "da28c06011f84b4e87b5f520d4013a03" //GET YOUR API KEY BY SIGNING to https://newsapi.org
    val BASE_URL = "https://newsapi.org/"
    val COUNTRY_CODE = "in"

    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        if (model.startsWith(manufacturer)) {
            Log.e("phoneModel", model)
            return model
        }
        Log.e("PhoneModel", "$manufacturer $model")
        return "$manufacturer $model"
    }

}