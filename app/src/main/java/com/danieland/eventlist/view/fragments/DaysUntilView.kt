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
import com.danieland.eventlist.adapters.AdapterEventUntil
import com.danieland.eventlist.database.entities.Event
import com.danieland.eventlist.interfaces.DaysUntilInterface
import com.danieland.eventlist.presenter.DaysUntilPresenter
import com.danieland.eventlist.util.Util
import com.danieland.eventlist.view.activities.EditEventView
import danieland.eventlist.R
import danieland.eventlist.databinding.FragmentDaysUntilBinding

class DaysUntilView : Fragment(), DaysUntilInterface.DaysUntilView {

    private var _binding: FragmentDaysUntilBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterUtil: AdapterEventUntil
    private val presenter = DaysUntilPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysUntilBinding.inflate(inflater, container, false)

        binding.rvDaysUntil.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        Log.v(Util.TAG_SHOW_EVENTUNTIL, "Accediendo al presenter, solicitando datos de eventos Until.")
        presenter.getItems()

        return binding.root
    }

    override fun showEvents(listEvents: List<Event>) {
        adapterUtil = AdapterEventUntil(listEvents) {
            //Pulsar en un evento y que te lleve a la pantalla EditEventView
            Log.v(Util.TAG_SHOW_EVENTUNTIL, "Accediendo a la pantalla EditEventView..")
            val intent = Intent(context, EditEventView::class.java)
            intent.putExtra("KEY", it)
            startActivity(intent)
            activity?.finish()
        }
        binding.rvDaysUntil.adapter = adapterUtil
    }

    override fun showEmptyScreen() {
        view?.findNavController()
            ?.navigate(R.id.action_daysUntilView_to_emptyEventsView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}