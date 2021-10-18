package com.example.eventlist.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.eventlist.adapters.AdapterEventUntil
import com.example.eventlist.databinding.FragmentDaysUntilBinding
import com.example.eventlist.interfaces.EventUntilInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.EventUntilPresenter
import com.example.eventlist.util.Util

class DaysUntilView : Fragment(), EventUntilInterface.EventUntilView {

    private var _binding: FragmentDaysUntilBinding? = null
    private val binding get() = _binding!!
    private val presenter = EventUntilPresenter(this)
    private lateinit var adapterUtil: AdapterEventUntil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDaysUntilBinding.inflate(inflater, container, false)

        presenter.uploadEventUntilFirestore()
        return binding.root
    }

    override fun showEventUntil(listEventUntil: MutableList<Event>) {
        Log.v(Util.TAG_SHOW_EVENTUNTIL,"Mostrando recyclerview en la vista..")

        adapterUtil = AdapterEventUntil(listEventUntil)

        binding.rvDaysUntil.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvDaysUntil.adapter = adapterUtil
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}