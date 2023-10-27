package com.kursatmemis.firebasekotlin.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kursatmemis.firebasekotlin.model.FirebaseResponse

class FirebaseRepository {

    private val auth = Firebase.auth
    private val generateFirebaseResponse = GenerateFirebaseResponse()

    fun createAccount(
        email: String,
        password: String,
        firebaseOperationCallback: FirebaseOperationCallback
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            signOut()
            val response = generateFirebaseResponse.generateResponse(task)
            firebaseOperationCallback.callback(response)
        }
    }

    fun signIn(email: String, password: String, firebaseOperationCallback: FirebaseOperationCallback) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            val response = generateFirebaseResponse.generateResponse(task)
            firebaseOperationCallback.callback(response)
        }
    }

    private fun signOut() {
        if (isSignIn()) {
            auth.signOut()
        }
    }

    private fun isSignIn(): Boolean {
        return auth.currentUser != null
    }

    fun updateProfile(
        profileUpdates: UserProfileChangeRequest,
        user: FirebaseUser,
        firebaseOperationCallback: FirebaseOperationCallback
    ) {
        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                val response = generateFirebaseResponse.generateResponse(task)
                firebaseOperationCallback.callback(response)
            }
    }

    fun sendPasswordResetEmail(email: String, firebaseOperationCallback: FirebaseOperationCallback) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            val response = generateFirebaseResponse.generateResponse(task)
            firebaseOperationCallback.callback(response)
        }
    }

    fun updatePassword(newPassword: String, firebaseOperationCallback: FirebaseOperationCallback) {
        val user = auth.currentUser!!
        user.updatePassword(newPassword).addOnCompleteListener { task ->
            val response = generateFirebaseResponse.generateResponse(task)
            firebaseOperationCallback.callback(response)
        }
    }

    fun sendEmailVerification(firebaseOperationCallback: FirebaseOperationCallback) {
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener { task ->
                val response = generateFirebaseResponse.generateResponse(task)
                firebaseOperationCallback.callback(response)
            }
    }

}

class GenerateFirebaseResponse() {

    fun <T> generateResponse(task: Task<T>): FirebaseResponse {
        val response: FirebaseResponse
        val isSuccessful = task.isSuccessful

        if (isSuccessful) {
            response = FirebaseResponse(true, "Your transaction was completed successfully!")
        } else {
            val errorMessage = task.exception?.message ?: "An error occurred!"
            response = FirebaseResponse(false, errorMessage)
        }
        return response
    }

}

interface FirebaseOperationCallback {
    fun callback(response: FirebaseResponse)
}

