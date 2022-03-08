package com.danieland.eventlist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danieland.eventlist.adapters.AdapterEventUntil
import com.danieland.eventlist.objects.EventApp
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.EditEventView
import danieland.eventlist.R
import danieland.eventlist.databinding.FragmentDaysUntilBinding

class DaysUntilView : Fragment() {

    private var _binding: FragmentDaysUntilBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterUtil: AdapterEventUntil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysUntilBinding.inflate(inflater, container, false)

        binding.rvDaysUntil.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        EventApp.getDB().eventDao().showUntilEvents()
            .observe(viewLifecycleOwner, Observer { event ->
                if (event.isEmpty()) {
                    view?.findNavController()
                        ?.navigate(R.id.action_daysUntilView_to_emptyEventsView)
                } else {
                    adapterUtil = AdapterEventUntil(event) {
                        //Pulsar en un evento y que te lleve a la pantalla EditEventView
                        Log.v(
                            Util.TAG_SHOW_EVENTSINCE,
                            "Accediendo a la pantalla EditEventView.."
                        )
                        val intent = Intent(context, EditEventView::class.java)
                        intent.putExtra("KEY", it)
                        startActivity(intent)
                    }
                    binding.rvDaysUntil.adapter = adapterUtil
                }
            })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}