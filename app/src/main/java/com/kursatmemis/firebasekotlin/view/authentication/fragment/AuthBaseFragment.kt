package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kursatmemis.firebasekotlin.model.UserLoginData
import com.kursatmemis.firebasekotlin.view.BaseFragment
import com.kursatmemis.firebasekotlin.view.home.activity.MainActivity

abstract class AuthBaseFragment<T: ViewBinding> : BaseFragment<T>() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        // Kullanıcının oturumu açık mı diye kontrol eder ve ui'ı ona göre günceller.
        // Not: Kullanıcının oturumu açık değilse, currentUser değeri 'null' dır.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            goToMainActivity()
        }
    }

    fun goToMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }
}

interface UserLoginDataProvider {
    fun getUserLoginDataFromEditText() : UserLoginData
}

interface UserLoginDataValidator {
    fun isUserLoginDataEmpty(user: UserLoginData): Boolean {
        val email = user.email
        val password = user.password
        return (email.isEmpty() || password.isEmpty())
    }
}
