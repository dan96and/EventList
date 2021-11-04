package com.example.eventlist.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.eventlist.databinding.FragmentRegisterViewBinding
import com.example.eventlist.interfaces.RegisterInterface
import com.example.eventlist.presenter.RegisterPresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.HomeView

class RegisterView : Fragment(), RegisterInterface.RegisterView {

    //Declaration binding
    private var _binding: FragmentRegisterViewBinding? = null
    private val binding get() = _binding!!

    private var presenter = RegisterPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Navigation
        binding.btnBefore.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRegister.setOnClickListener {
            Log.v(Util.TAG_REGISTER, "View comunicando con el Presenter..")
            presenter.registerWithEmailAndPassword(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim(),
                binding.etConfirmPassword.text.toString().trim()
            )
        }
    }

    override fun goHome() {
        Util.openActivity(context, HomeView::class.java)
        activity?.finish()
    }

    override fun showMessageErrorRegister(message: String) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}