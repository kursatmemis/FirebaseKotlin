package com.kursatmemis.firebasekotlin.viewmodel

import com.kursatmemis.firebasekotlin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PasswordResetFragmentViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) : BaseViewModel() {

    fun sendPasswordResetEmail(email: String) {
        firebaseRepository.sendPasswordResetEmail(email, firebaseOperationCallback)
    }

}