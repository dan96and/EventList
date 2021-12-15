package com.example.eventlist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventlist.adapters.AdapterEventSince
import com.example.eventlist.databinding.FragmentDaysSinceBinding
import com.example.eventlist.interfaces.EventSinceInterface
import com.example.eventlist.database.entities.Event
import com.example.eventlist.presenter.EventSincePresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.EditEventView
import androidx.navigation.findNavController
import com.example.eventlist.R
import com.example.eventlist.objects.EventApp

class DaysSinceView : Fragment(), EventSinceInterface.EventSinceView {

    private var _binding: FragmentDaysSinceBinding? = null
    private lateinit var adapterSince: AdapterEventSince
    var presenter = EventSincePresenter(this)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysSinceBinding.inflate(inflater, container, false)

        binding.rvDaysSince.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        presenter.uploadEventsSince()
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

    override fun showEventSince(eventSinceList: LiveData<List<Event>>) {
        Log.v(Util.TAG_SHOW_EVENTSINCE, "Mostrando recyclerview en la vista..")
        
    }

    //Mostrar FragmentNoEvents
    override fun showScreenNoEvents() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}