package com.example.eventlist.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.eventlist.R
import com.example.eventlist.databinding.FragmentMenuInformationViewBinding

class MenuInformationView() : Fragment() {

    private var _binding: FragmentMenuInformationViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuInformationViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContact.setOnClickListener {
            view.findNavController().navigate(R.id.action_menuInformationView_to_contactView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}