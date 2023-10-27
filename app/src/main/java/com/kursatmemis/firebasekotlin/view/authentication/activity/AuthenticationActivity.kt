package com.kursatmemis.firebasekotlin.view.authentication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kursatmemis.firebasekotlin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }
}