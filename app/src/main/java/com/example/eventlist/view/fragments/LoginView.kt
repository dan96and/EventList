package com.example.eventlist.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eventlist.view.activities.HomeView
import com.example.eventlist.R
import com.example.eventlist.databinding.FragmentLoginViewBinding
import com.example.eventlist.interfaces.LoginInterface
import com.example.eventlist.presenter.LoginPresenter
import com.example.eventlist.util.Util

class LoginView : Fragment(), LoginInterface.LoginView {

    //Declaration
    private var _binding: FragmentLoginViewBinding? = null
    private val binding get() = _binding!!
    private var presenter = LoginPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //NAVIGATION
        binding.lblForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_ForgotPasswordFragment)
        }

        binding.lblSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginView_to_registerView)
        }


        //LOGIN
        binding.btnSignIn.setOnClickListener {
            Log.v(Util.TAG_LOGIN, "Vista llamando al Presenter..")
            presenter.signWithEmailAndPassword(
                binding.etEmail.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun goHomeView() {
        activity?.finish()
        Util.openActivity(context, HomeView::class.java)
    }

    override fun showMessageErrorLogin(message: String) {
        Util.showToast(context, message)
    }

    override fun onStart() {
        super.onStart()
        presenter.autoLogin()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}