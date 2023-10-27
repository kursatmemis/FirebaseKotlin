package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.kursatmemis.firebasekotlin.R
import com.kursatmemis.firebasekotlin.databinding.FragmentLoginBinding
import com.kursatmemis.firebasekotlin.helper.closeProgressBar
import com.kursatmemis.firebasekotlin.helper.showProgressBar
import com.kursatmemis.firebasekotlin.model.UserLoginData
import com.kursatmemis.firebasekotlin.helper.showToast
import com.kursatmemis.firebasekotlin.viewmodel.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AuthBaseFragment<FragmentLoginBinding>(), UserLoginDataProvider, UserLoginDataValidator {

    private val loginViewModel: LoginFragmentViewModel by viewModels()

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {

        binding.registerTextView.setOnClickListener {
            goToRegisterFragment(it)
        }

        binding.loginButton.setOnClickListener {
            val userLoginData = getUserLoginDataFromEditText()
            val isUserLoginDataEmpty = isUserLoginDataEmpty(userLoginData)
            if (!isUserLoginDataEmpty) {
                showProgressBar(binding.progressBar)
                loginViewModel.signIn(userLoginData.email, userLoginData.password)
            } else {
                showToast(requireContext(), "Please fill in the fields.")
            }
        }

        binding.resetPasswordTextView.setOnClickListener {
            goToPasswordResetFragment(it)
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        loginViewModel.firebaseResponseLiveData.observe(viewLifecycleOwner) {
            showToast(requireContext(), it.message)
            closeProgressBar(binding.progressBar)
            if (it.isSuccessful) {
                goToMainActivity()
                activity?.finish()
            }
        }
    }

    private fun goToPasswordResetFragment(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_loginFragment_to_passwordResetFragment)
    }

    private fun goToRegisterFragment(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
    }

    override fun getUserLoginDataFromEditText(): UserLoginData {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return UserLoginData(email, password)
    }

}

