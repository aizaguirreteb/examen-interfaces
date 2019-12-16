package es.izaguirre.examen_izaguirre_alejandra


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.izaguirre.examen_izaguirre_alejandra.model.Event
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(arguments != null){
            val event = arguments!!.get("EVENT") as Event
            setEvent(event)
        }
    }

    private fun setEvent(event: Event){
        detailName.text = event.name
        detailDateTime.text = event.dateTime
        detailPlace.text = event.place
        detailSpeaker.text = event.speaker
        detailDescription.text = event.description
    }


}
