package com.kursatmemis.firebasekotlin.viewmodel

import com.kursatmemis.firebasekotlin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) :
    BaseViewModel() {

    fun signIn(email: String, password: String) {
        firebaseRepository.signIn(email, password, firebaseOperationCallback)
    }

}