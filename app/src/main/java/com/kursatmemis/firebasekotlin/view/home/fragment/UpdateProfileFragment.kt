package com.kursatmemis.firebasekotlin.view.home.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.kursatmemis.firebasekotlin.databinding.FragmentUpdateProfileBinding
import com.kursatmemis.firebasekotlin.helper.areEditTextsEmpty
import com.kursatmemis.firebasekotlin.helper.closeProgressBar
import com.kursatmemis.firebasekotlin.helper.showProgressBar
import com.kursatmemis.firebasekotlin.helper.showToast
import com.kursatmemis.firebasekotlin.view.BaseFragment
import com.kursatmemis.firebasekotlin.viewmodel.UpdateProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProfileFragment : BaseFragment<FragmentUpdateProfileBinding>() {

    private val updateProfileViewModel: UpdateProfileViewModel by viewModels()
    private val user = Firebase.auth.currentUser

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUpdateProfileBinding {
        return FragmentUpdateProfileBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {

        binding.nameEditText.setText(user?.displayName)
        binding.emailTextView.text = user?.email

        binding.updateProfileButton.setOnClickListener {
            showProgressBar(binding.progressBar)
            val profileUpdates = userProfileChangeRequest {
                displayName = binding.nameEditText.text.toString()
            }
            updateProfileViewModel.updateProfile(profileUpdates, user!!)
        }

        binding.updatePasswordButton.setOnClickListener {
            val isEmpty = areEditTextsEmpty(binding.passwordEditText, binding.confirmPasswordEditText)
            if (!isEmpty) {
                showProgressBar(binding.progressBar)
                val newPassword1 = binding.passwordEditText.text.toString()
                val newPassword2 = binding.confirmPasswordEditText.text.toString()
                val isMatching = arePasswordsMatching(newPassword1, newPassword2)
                if (isMatching) {
                    updateProfileViewModel.updatePassword(newPassword1)
                } else {
                    showToast(requireContext(), "Passwords do not match.")
                    closeProgressBar(binding.progressBar)
                }
            } else {
                showToast(requireContext(), "Please fill in the fields.")
                closeProgressBar(binding.progressBar)
            }


        }

        binding.sendEmailVerificationButton.setOnClickListener {
            showProgressBar(binding.progressBar)
            updateProfileViewModel.sendEmailVerification()
        }

        observeLiveData()

    }

    private fun observeLiveData() {
        updateProfileViewModel.firebaseResponseLiveData.observe(viewLifecycleOwner) {
            closeProgressBar(binding.progressBar)
            showToast(requireContext(), it.message)
        }
    }

    private fun arePasswordsMatching(password1: String, password2: String) : Boolean {
        return password1 == password2
    }

}