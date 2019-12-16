package es.izaguirre.examen_izaguirre_alejandra.api

import es.izaguirre.examen_izaguirre_alejandra.model.Event
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi{

    @GET("events")
    fun getAllEvents(): Call<List<Event>>

    @POST("events")
    fun addEvent(@Body event: Event): Call<Event>

}