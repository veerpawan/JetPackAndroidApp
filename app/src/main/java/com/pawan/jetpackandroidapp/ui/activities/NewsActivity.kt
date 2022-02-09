package com.pawan.jetpackandroidapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pawan.jetpackandroidapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_new)
    }
}