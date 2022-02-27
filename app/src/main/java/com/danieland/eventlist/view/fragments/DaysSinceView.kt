package com.danieland.eventlist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danieland.eventlist.adapters.AdapterEventSince

import com.danieland.eventlist.objects.EventApp
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.EditEventView
import danieland.eventlist.R
import danieland.eventlist.databinding.FragmentDaysSinceBinding

class DaysSinceView : Fragment() {

    private var _binding: FragmentDaysSinceBinding? = null
    private lateinit var adapterSince: AdapterEventSince
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysSinceBinding.inflate(inflater, container, false)

        binding.rvDaysSince.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        EventApp.getDB().eventDao().showSinceEvents()
            .observe(viewLifecycleOwner, Observer { event ->
                if (event.isEmpty()) {
                    view?.findNavController()?.navigate(R.id.emptyEventsView)

                } else {
                    adapterSince = AdapterEventSince(event)
                    binding.rvDaysSince.adapter = adapterSince

                    //Pulsar en un evento y que te lleve a la pantalla EditEventView
                    adapterSince.setOnItemClickListener(object :
                        AdapterEventSince.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            Log.v(
                                Util.TAG_SHOW_EVENTSINCE,
                                "Accediendo a la pantalla EditEventView.."
                            )
                            val intent = Intent(context, EditEventView::class.java)
                            intent.putExtra("KEY", event[position])
                            startActivity(intent)
                        }
                    })
                }
            })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}