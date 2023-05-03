package com.example.cotton_ticket.models

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("ticket/lire.php")
    fun lire_ticket(@Query("id_utilisateur") id_utilisateur: Int): Call<Ticket>
}