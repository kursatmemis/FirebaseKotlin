package com.kursatmemis.firebasekotlin.view.home.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kursatmemis.firebasekotlin.databinding.FragmentHomeBinding
import com.kursatmemis.firebasekotlin.view.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val user =  Firebase.auth.currentUser

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        user?.let {
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl
            val emailVerified = it.isEmailVerified
            val uid = it.uid
            Log.w("mKm - homeFragment", name.toString())
            Log.w("mKm - homeFragment", email.toString())
            Log.w("mKm - homeFragment", photoUrl.toString())
            Log.w("mKm - homeFragment", emailVerified.toString())
            Log.w("mKm - homeFragment", uid.toString())
        }

    }

}