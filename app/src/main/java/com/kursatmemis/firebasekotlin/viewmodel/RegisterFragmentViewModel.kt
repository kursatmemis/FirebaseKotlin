package com.kursatmemis.firebasekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatmemis.firebasekotlin.repository.AuthenticationCallBack
import com.kursatmemis.firebasekotlin.repository.FirebaseRepository
import com.kursatmemis.firebasekotlin.repository.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    val responseLiveData = MutableLiveData<Response>()
    private val authenticationCallBack = object : AuthenticationCallBack {
        override fun callback(response: Response) {
            responseLiveData.value = response
        }
    }

    fun createAccount(email: String, password: String) {
        firebaseRepository.createAccount(email, password, authenticationCallBack)
    }

}