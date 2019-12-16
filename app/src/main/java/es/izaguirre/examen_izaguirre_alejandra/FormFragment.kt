package es.izaguirre.examen_izaguirre_alejandra


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import es.izaguirre.examen_izaguirre_alejandra.model.Event
import es.izaguirre.examen_izaguirre_alejandra.model.EventViewModel
import kotlinx.android.synthetic.main.fragment_form.*


class FormFragment : Fragment() , View.OnClickListener{

    val arraySpinner = arrayOf("Martin Scorsese", "Steven Spielberg", "Katherine Bigelow", "Sofia Coppola", "Christopher Nolan", "Greta Gerwig")
    lateinit var spinner: Spinner
    val eventViewModel: EventViewModel by lazy {
        ViewModelProviders.of(this).get(EventViewModel::class.java)
    }

    lateinit var button: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        button = view.findViewById(R.id.buttonAdd)
        button.setOnClickListener(this)

        spinner = view.findViewById<Spinner>(R.id.editTextSpeaker)
        spinner.adapter = ArrayAdapter<String>(activity!!.baseContext,android.R.layout.simple_spinner_item,arraySpinner)

    }

    private fun retrieveEvent(): Event{

            val eventName = editTextName.text.toString()
            val eventPlace = editTextPlace.text.toString()
            val eventDateTime = editTextDateTime.text.toString()
            val eventSpeaker = editTextSpeaker.selectedItem.toString()
            val eventDescription = editTextDescription.text.toString()
            return Event(eventName,eventPlace,eventDateTime,eventSpeaker, eventDescription)

    }

    override fun onClick(v: View?) {
        if(!checkNotEmpty()) {
            Toast.makeText(context, "FIELDS CAN NOT BE EMPTY", Toast.LENGTH_SHORT).show()

        } else {
            val eventRetrieved = retrieveEvent()
            eventViewModel.addEvent(eventRetrieved)
            observeAddEvent()
        }

    }

    private fun observeAddEvent(){
        eventViewModel.addLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Added ${it.name}", Toast.LENGTH_SHORT).show()
        })
        eventViewModel.getAllEvents()
    }

    private fun checkNotEmpty(): Boolean{
        if(editTextName.text.isEmpty()
            || editTextDateTime.text.isEmpty()
            || editTextPlace.text.isEmpty()
            || editTextSpeaker.selectedItem == null
            || editTextDescription.text.isEmpty()){
            return false
        }
            return true


    }


}
