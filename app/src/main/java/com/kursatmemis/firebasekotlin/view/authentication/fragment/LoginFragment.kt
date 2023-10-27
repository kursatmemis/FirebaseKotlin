package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kursatmemis.firebasekotlin.R
import com.kursatmemis.firebasekotlin.databinding.FragmentLoginBinding
import com.kursatmemis.firebasekotlin.view.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun createBindingObject(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {

        binding.registerTextView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginButton.setOnClickListener {
            // Home Activity is not available yet!
        }

        binding.resetPasswordTextView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_passwordResetFragment)
        }


    }

}