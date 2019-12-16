package es.izaguirre.examen_izaguirre_alejandra.model

import es.izaguirre.examen_izaguirre_alejandra.api.ApiConfig
import es.izaguirre.examen_izaguirre_alejandra.api.EventApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object EventRepository {

    val api : EventApi

    init{
        val retrofit = Retrofit.Builder().baseUrl(ApiConfig.URL_API_EVENTOS)
            .addConverterFactory(GsonConverterFactory.create()).build()

        api = retrofit.create(EventApi::class.java)
    }

    fun getAllEvents(callback: ListRepositoryCallback){
        val call = api.getAllEvents()
        call.enqueue(object: Callback<List<Event>>{
            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
               callback.onResponse(response.body().orEmpty())
            }

        })
    }

    fun addEvent(event: Event, callback: AddEventRepositoryCallback){
        val call = api.addEvent(event)
        call.enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if(response.isSuccessful) {

                    callback.onResponse(response.body())
                }
            }

        })
    }



    interface ListRepositoryCallback{
        fun onResponse(events: List<Event>)
        fun onError(msg: String?)

    }

    interface AddEventRepositoryCallback{
        fun onResponse(event:Event?)
        fun onError(msg: String?)
    }
}