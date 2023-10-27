package com.kursatmemis.firebasekotlin.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseRepository() {

    private val auth = Firebase.auth

    fun createAccount(
        email: String,
        password: String,
        authenticationCallBack: AuthenticationCallBack
    ) {
        var response: Response
        var user: FirebaseUser?

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            user = auth.currentUser
            if (task.isSuccessful) {
                response = Response(true, "Your account was created!", user)
            } else {
                val errorMessage = task.exception?.message ?: "An error occurred!"
                response = Response(false, errorMessage, user)
            }
            authenticationCallBack.callback(response)
        }
    }

    fun signIn(email: String, password: String, authenticationCallBack: AuthenticationCallBack) {
        var response: Response
        var user: FirebaseUser?

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            user = auth.currentUser
            if (task.isSuccessful) {
                response = Response(true, "User signed in!", user)
            } else {
                val errorMessage = task.exception?.message ?: "An error occurred!"
                response = Response(false, errorMessage, user)
            }
            authenticationCallBack.callback(response)
        }
    }

}


interface AuthenticationCallBack {
    fun callback(response: Response)
}

data class Response(val isSuccess: Boolean, val message: String, val user: FirebaseUser?)