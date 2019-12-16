package es.izaguirre.examen_izaguirre_alejandra


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.izaguirre.examen_izaguirre_alejandra.adapter.RecyclerAdapter
import es.izaguirre.examen_izaguirre_alejandra.model.Event
import es.izaguirre.examen_izaguirre_alejandra.model.EventViewModel


class ListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    val eventViewModel: EventViewModel by lazy {
        ViewModelProviders.of(this).get(EventViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //listener
        val listener = object : RecyclerAdapter.OnItemClickListener{
            override fun onClicked(event: Event) {
                val args = Bundle()
                args.putParcelable("EVENT", event)
                val detailFragment = DetailFragment()
                detailFragment.arguments = args
                fragmentManager!!.beginTransaction()
                    .replace(R.id.container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }

        }
        recyclerAdapter = RecyclerAdapter(emptyList(), listener)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply{
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(context,2, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()
        eventViewModel.getAllEvents()
        listObserver()


    }

    fun listObserver(){
        eventViewModel.listLiveData.observe(viewLifecycleOwner, Observer {
            events ->
            recyclerAdapter.eventos = events
            recyclerAdapter.notifyDataSetChanged()
        })
    }


}
