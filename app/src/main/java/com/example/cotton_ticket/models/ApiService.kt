package com.example.cotton_ticket.models

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("id_utilisateur")
    fun lire_ticket(): Call<List<Ticket>>
}