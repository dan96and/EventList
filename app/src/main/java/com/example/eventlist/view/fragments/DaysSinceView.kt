package com.example.eventlist.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventlist.adapters.AdapterEventSince
import com.example.eventlist.databinding.FragmentDaysSinceBinding
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.EventSincePresenter
import com.example.eventlist.util.Util

class DaysSinceView : Fragment(), EventSinceInterface.EventSinceView {

    private var _binding: FragmentDaysSinceBinding? = null
    private lateinit var adapterSince: AdapterEventSince
    var presenter = EventSincePresenter(this)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDaysSinceBinding.inflate(inflater, container, false)

        presenter.uploadEventsSince()

        return binding.root
    }

    override fun showEventSince(eventSinceList: MutableList<Event>) {
        Log.v(Util.TAG_SHOW_EVENTSINCE,"Mostrando recyclerview en la vista..")

        adapterSince = AdapterEventSince(eventSinceList)

        binding.rvDaysSince.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvDaysSince.adapter = adapterSince
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}