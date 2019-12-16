package es.izaguirre.examen_izaguirre_alejandra.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.izaguirre.examen_izaguirre_alejandra.model.EventRepository.AddEventRepositoryCallback

class EventViewModel : ViewModel(){

    val listLiveData = MutableLiveData<List<Event>>()
    val addLiveData = MutableLiveData<Event>()

    fun getAllEvents(){
        EventRepository.getAllEvents(object : EventRepository.ListRepositoryCallback{
            override fun onResponse(events: List<Event>) {
                listLiveData.value = events
            }

            override fun onError(msg: String?) {
                Log.i("ERROR", msg)
            }

        })
    }

    fun addEvent(event: Event){
        EventRepository.addEvent(event, object : AddEventRepositoryCallback{
            override fun onResponse(event: Event?) {
                addLiveData.value = event
                getAllEvents()
            }

            override fun onError(msg: String?) {
                //Toast
            }

        })
    }
}