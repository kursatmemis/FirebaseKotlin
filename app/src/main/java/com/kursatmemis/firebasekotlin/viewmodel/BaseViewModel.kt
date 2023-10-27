package com.kursatmemis.firebasekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kursatmemis.firebasekotlin.model.FirebaseResponse
import com.kursatmemis.firebasekotlin.repository.FirebaseOperationCallback
import com.kursatmemis.firebasekotlin.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
) : ViewModel() {

    val firebaseResponseLiveData = MutableLiveData<FirebaseResponse>()
    val firebaseOperationCallback = object : FirebaseOperationCallback {
        override fun callback(response: FirebaseResponse) {
            firebaseResponseLiveData.value = response
        }
    }

}