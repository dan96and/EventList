package com.danieland.eventlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danieland.eventlist.database.entities.Event
import danieland.eventlist.R
import danieland.eventlist.databinding.CardviewEventUntilBinding

class AdapterEventUntil(private var eventList: List<Event>, private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<AdapterEventUntil.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_event_until, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = eventList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount(): Int = eventList.size

    class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private var binding = CardviewEventUntilBinding.bind(view)

        fun bind(event: Event) {
            with(binding) {
                cvTitle.text = event.name
                cvDate.text = event.getStringDateCreationAndDate()
                cvTimeDifferent.text = event.getTimeDifferentBetweenDates()
            }
            checkDescriptionIsEmpty(event.description)
            //checkNotification(event)
        }

        //Validar si el Evento tiene notificacion
        private fun checkNotification(event: Event) {
            if (!event.notification) {
                binding.ivNotification.setBackgroundResource(R.drawable.ic_notification_desactive)
            } else {
                binding.ivNotification.setBackgroundResource(R.drawable.ic_notification_active)
            }
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