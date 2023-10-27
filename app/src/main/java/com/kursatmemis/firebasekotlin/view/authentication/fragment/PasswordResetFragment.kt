package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kursatmemis.firebasekotlin.databinding.FragmentPasswordResetBinding
import com.kursatmemis.firebasekotlin.helper.areEditTextsEmpty
import com.kursatmemis.firebasekotlin.helper.closeProgressBar
import com.kursatmemis.firebasekotlin.helper.showProgressBar
import com.kursatmemis.firebasekotlin.helper.showToast
import com.kursatmemis.firebasekotlin.viewmodel.PasswordResetFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordResetFragment : AuthBaseFragment<FragmentPasswordResetBinding>() {

    private val passwordResetFragmentViewModel: PasswordResetFragmentViewModel by viewModels()

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPasswordResetBinding {
        return FragmentPasswordResetBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {

        binding.resetPasswordButton.setOnClickListener {
            val isEmpty = areEditTextsEmpty(binding.emailEditText)
            if (!isEmpty) {
                showProgressBar(binding.progressBar)
                val email = binding.emailEditText.text.toString()
                passwordResetFragmentViewModel.sendPasswordResetEmail(email)
            } else {
                showToast(requireContext(), "Please fill in the fields.")
            }
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        passwordResetFragmentViewModel.firebaseResponseLiveData.observe(viewLifecycleOwner) {
            closeProgressBar(binding.progressBar)
            showToast(requireContext(), it.message)
        }
    }

}