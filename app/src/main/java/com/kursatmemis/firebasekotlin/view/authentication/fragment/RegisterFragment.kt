package com.kursatmemis.firebasekotlin.view.authentication.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kursatmemis.firebasekotlin.databinding.FragmentRegisterBinding
import com.kursatmemis.firebasekotlin.view.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override fun createBindingObject(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(inflater, container, false)
    }

    override fun setupUI() {
        binding.registerButton.setOnClickListener {

        }
    }


}