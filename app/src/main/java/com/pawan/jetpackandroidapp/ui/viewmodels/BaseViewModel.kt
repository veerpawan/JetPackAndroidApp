package com.pawan.jetpackandroidapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel() {

    val job = Job()
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}