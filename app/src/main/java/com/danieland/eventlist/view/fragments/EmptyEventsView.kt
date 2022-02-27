package com.danieland.eventlist.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.NewEventView
import danieland.eventlist.databinding.FragmentEmptyEventsViewBinding

class EmptyEventsView : Fragment() {

    private var _binding: FragmentEmptyEventsViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyEventsViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Abrir la actividad
        binding.btnAddEvent.setOnClickListener {
            Util.openActivity(context, NewEventView::class.java)
        }
    }
}