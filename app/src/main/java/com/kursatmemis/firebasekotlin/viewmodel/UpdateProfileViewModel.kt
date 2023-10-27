package com.kursatmemis.firebasekotlin.viewmodel

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.kursatmemis.firebasekotlin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) : BaseViewModel() {

    fun updateProfile(profileUpdates: UserProfileChangeRequest, user: FirebaseUser) {
        firebaseRepository.updateProfile(profileUpdates, user, firebaseOperationCallback)
    }

    fun updatePassword(newPassword: String) {
        firebaseRepository.updatePassword(newPassword, firebaseOperationCallback)
    }

    fun sendEmailVerification() {
        firebaseRepository.sendEmailVerification(firebaseOperationCallback)
    }

}