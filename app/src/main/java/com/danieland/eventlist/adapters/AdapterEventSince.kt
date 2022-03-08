package com.danieland.eventlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danieland.eventlist.database.entities.Event
import danieland.eventlist.R
import danieland.eventlist.databinding.CardviewEventSinceBinding

class AdapterEventSince(private var eventList: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<AdapterEventSince.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_event_since, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = eventList[position]
        with(holder) {
            bind(item)
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun getItemCount(): Int = eventList.size

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = CardviewEventSinceBinding.bind(view)

        fun bind(event: Event) {
            with(binding) {
                cvTitle.text = event.name
                cvDate.text = event.getStringDateCreationAndDate()
                cvTimeDifferent.text = event.getTimeDifferentBetweenDates()
            }
            checkDescriptionIsEmpty(event.description)
        }

        //Validar si una descripción es vacia, para poder mostrar el dato o esconder el componente.
        private fun checkDescriptionIsEmpty(description: String) {
            if (description == "") {
                binding.cvDescription.visibility = View.GONE
            } else {
                binding.cvDescription.text = description
            }
        }
    }
}