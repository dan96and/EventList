package com.example.eventlist.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eventlist.R
import com.example.eventlist.adapters.AdapterEventUntil
import com.example.eventlist.databinding.FragmentDaysUntilBinding
import com.example.eventlist.interfaces.EventUntilInterface
import com.example.eventlist.objects.Event
import com.example.eventlist.presenter.EventUntilPresenter
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.EditEventView

class DaysUntilView : Fragment(), EventUntilInterface.EventUntilView {

    private var _binding: FragmentDaysUntilBinding? = null
    private val binding get() = _binding!!
    private val presenter = EventUntilPresenter(this)
    private lateinit var adapterUtil: AdapterEventUntil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDaysUntilBinding.inflate(inflater, container, false)

        presenter.uploadEventUntilFirestore()
        return binding.root
    }

    override fun showEventUntil(listEventUntil: MutableList<Event>) {

        Log.v(Util.TAG_SHOW_EVENTUNTIL, "Mostrando recyclerview en la vista..")
        adapterUtil = AdapterEventUntil(listEventUntil)
        binding.rvDaysUntil.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvDaysUntil.adapter = adapterUtil

        //Pulsar en un evento y que te lleve a la pantalla EditEventView
        adapterUtil.setOnItemClickListener(object : AdapterEventUntil.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.v(Util.TAG_SHOW_EVENTSINCE, "Accediendo a la pantalla EditEventView..")
                val intent = Intent(context, EditEventView::class.java)
                intent.putExtra("KEY", listEventUntil[position])
                startActivity(intent)
            }
        })
    }

    //Mostrar FragmentNoEvents
    override fun showScreenNoEvents() {
        view?.findNavController()?.navigate(R.id.emptyEventsView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}