package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kursatmemis.firebasekotlin.databinding.FragmentRegisterBinding
import com.kursatmemis.firebasekotlin.helper.closeProgressBar
import com.kursatmemis.firebasekotlin.helper.showProgressBar
import com.kursatmemis.firebasekotlin.helper.showToast
import com.kursatmemis.firebasekotlin.model.UserLoginData
import com.kursatmemis.firebasekotlin.viewmodel.RegisterFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : AuthBaseFragment<FragmentRegisterBinding>(), UserLoginDataProvider,
    UserLoginDataValidator {

    private val registerFragmentViewModel: RegisterFragmentViewModel by viewModels()

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        binding.registerButton.setOnClickListener {
            val userLoginData = getUserLoginDataFromEditText()
            val isUserLoginDataEmpty = isUserLoginDataEmpty(userLoginData)
            if (!isUserLoginDataEmpty) {
                showProgressBar(binding.progressBar)
                registerFragmentViewModel.createAccount(userLoginData.email, userLoginData.password)
            } else {
                showToast(requireContext(), "Please fill in the fields.")
            }
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        registerFragmentViewModel.firebaseResponseLiveData.observe(viewLifecycleOwner, Observer {
            closeProgressBar(binding.progressBar)
            showToast(requireContext(), it.message)
        })
    }

    override fun getUserLoginDataFromEditText(): UserLoginData {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return UserLoginData(email, password)
    }

}