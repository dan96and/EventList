package com.example.eventlist.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eventlist.R
import com.example.eventlist.databinding.FragmentLoginViewBinding


class LoginView : Fragment() {
    //Declaration binding
    private var _binding: FragmentLoginViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //lblForgotPassword Navigation
        binding.lblForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_ForgotPasswordFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}