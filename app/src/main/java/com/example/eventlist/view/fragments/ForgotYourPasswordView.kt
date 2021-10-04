package com.example.eventlist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.eventlist.databinding.FragmentForgotYourPasswordViewBinding

class ForgotYourPasswordView : Fragment() {

    //Declaration binding
    private var _binding: FragmentForgotYourPasswordViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentForgotYourPasswordViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Navigation
        binding.btnBefore.setOnClickListener{
            findNavController().popBackStack()

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {

        super.onDestroyView()
        _binding = null
    }
}