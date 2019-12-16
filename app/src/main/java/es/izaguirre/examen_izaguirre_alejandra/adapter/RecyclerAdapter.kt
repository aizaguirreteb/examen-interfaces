package es.izaguirre.examen_izaguirre_alejandra.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import es.izaguirre.examen_izaguirre_alejandra.R
import es.izaguirre.examen_izaguirre_alejandra.model.Event

class RecyclerAdapter(events: List<Event>, onItemClickedListener: OnItemClickListener) : Adapter<RecyclerAdapter.EventViewHolder>(){

    var eventos = events
    var listener = onItemClickedListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_item, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventos[position]
        holder.bind(event, listener)
    }


    inner class EventViewHolder(view: View): RecyclerView.ViewHolder(view){

        //Elements on the item layout
        val txtName = itemView.findViewById<TextView>(R.id.eventName)
        val txtDate = itemView.findViewById<TextView>(R.id.dateTimeEvent)
        val txtSpeaker = itemView.findViewById<TextView>(R.id.speaker)

        fun bind(event: Event, onItemClickedListener: OnItemClickListener){
            txtName.text = event.name
            txtDate.text = event.dateTime
            txtSpeaker.text = event.speaker

            itemView.setOnClickListener{
                onItemClickedListener.onClicked(event)
            }
        }

    }

    interface OnItemClickListener{
        fun onClicked(event: Event)
    }
}