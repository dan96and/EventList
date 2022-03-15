package com.danieland.eventlist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danieland.eventlist.adapters.AdapterEventSince
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.interfaces.DaysSinceInterface
import com.danieland.eventlist.presenter.DaysSincePresenter
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.EditEventView
import danieland.eventlist.R
import danieland.eventlist.databinding.FragmentDaysSinceBinding

class DaysSinceView : Fragment(), DaysSinceInterface.DaysSinceView {

    private var _binding: FragmentDaysSinceBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterSince: AdapterEventSince
    private val presenter = DaysSincePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysSinceBinding.inflate(inflater, container, false)

        binding.rvDaysSince.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        Log.v(Util.TAG_SHOW_EVENTSINCE, "Accediendo al presenter para hacer la peticion..")
        presenter.getItems()

        return binding.root
    }

    override fun showEvents(listEvent: List<Event>) {
        adapterSince = AdapterEventSince(listEvent) {
            //Pulsar en un evento y que te lleve a la pantalla EditEventView
            Log.v(Util.TAG_SHOW_EVENTSINCE, "Accediendo a la pantalla EditEventView..")
            val intent = Intent(context, EditEventView::class.java)
            intent.putExtra("KEY", it)
            startActivity(intent)
            activity?.finish()
        }
        binding.rvDaysSince.adapter = adapterSince
    }

    override fun showEmptyEvents() {
        view?.findNavController()
            ?.navigate(R.id.action_daysSinceView_to_emptyEventsView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}