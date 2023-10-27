package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kursatmemis.firebasekotlin.R
import com.kursatmemis.firebasekotlin.databinding.FragmentPasswordResetBinding
import com.kursatmemis.firebasekotlin.view.BaseFragment

class PasswordResetFragment : BaseFragment<FragmentPasswordResetBinding>() {

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPasswordResetBinding {
        return FragmentPasswordResetBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        binding.resetPasswordButton.setOnClickListener {

        }
    }


}