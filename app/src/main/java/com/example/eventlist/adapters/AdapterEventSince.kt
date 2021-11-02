package com.example.eventlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventlist.R
import com.example.eventlist.objects.Event

class AdapterEventSince(private var eventList: MutableList<Event>) : RecyclerView.Adapter<AdapterEventSince.MyViewHolder>() {

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_event_since, parent, false)

        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val arrayEvents = eventList[position]
        holder.bind(arrayEvents)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    class MyViewHolder(view: View, listener: OnItemClickListener) :RecyclerView.ViewHolder(view){
        private val title = view.findViewById(R.id.cvTitle) as TextView
        private val date = view.findViewById(R.id.cvDate) as TextView
        fun bind(event: Event) {
            title.text = event.title
            date.text = event.getStringDateCreationAndDate()
        }
        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}

