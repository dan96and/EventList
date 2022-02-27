package com.danieland.eventlist.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import danieland.eventlist.databinding.FragmentContactViewBinding

class ContactView : Fragment() {

    private var _binding: FragmentContactViewBinding? = null
    private val binding get() = _binding!!

    private val uriGithub: Uri = Uri.parse("https://github.com/dan96and")
    private val uriLinkedin: Uri = Uri.parse("https://www.linkedin.com/in/daniel-andr%C3%A9s-bacho-868a35175/")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactViewBinding.inflate(inflater, container, false)

        binding.btnGitHub.setOnClickListener {
            openURLInBrowser(uriGithub)
        }

        binding.btnLinkedin.setOnClickListener {
            openURLInBrowser(uriLinkedin)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Abrir una URL en el navegador predeterminado
    private fun openURLInBrowser(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}