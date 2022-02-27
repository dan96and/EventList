package com.example.eventlist.view.fragments

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
import com.example.eventlist.R
import com.example.eventlist.adapters.AdapterEventUntil
import com.example.eventlist.databinding.FragmentDaysUntilBinding
import com.example.eventlist.objects.EventApp
import com.example.eventlist.util.Util
import com.example.eventlist.view.activities.EditEventView

class DaysUntilView : Fragment() {

    private var _binding: FragmentDaysUntilBinding? = null
    private val binding get() = _binding!!
    //private val presenter = EventUntilPresenter(this)
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
                    view?.findNavController()?.navigate(R.id.emptyEventsView)

                } else {
                    adapterUtil = AdapterEventUntil(event)
                    binding.rvDaysUntil.adapter = adapterUtil

                    //Pulsar en un evento y que te lleve a la pantalla EditEventView
                    adapterUtil.setOnItemClickListener(object :
                        AdapterEventUntil.OnItemClickListener {
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