package com.example.eventlist.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eventlist.databinding.FragmentForgotYourPasswordViewBinding
import com.example.eventlist.interfaces.ForgotYourPasswordInterface
import com.example.eventlist.presenter.ForgotYourPasswordPresenter
import com.example.eventlist.util.Util

class ForgotYourPasswordView : Fragment(), ForgotYourPasswordInterface.ForgotYourPasswordView {

    private val presenter = ForgotYourPasswordPresenter(this)

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
        binding.btnBefore.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSendEmail.setOnClickListener {
            Log.v(Util.TAG_FORGOTPASSWORD,"Comunicando la view con el presenter..")
            presenter.sendPassword(binding.etEmail.text.toString().trim())
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMessage(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    override fun showMessageCloseFragment(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }
}