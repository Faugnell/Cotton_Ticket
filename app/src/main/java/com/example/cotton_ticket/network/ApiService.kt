package com.example.cotton_ticket.network

import com.example.cotton_ticket.models.Ticket
import retrofit2.http.*

interface ApiService {
    @GET("id_utilisateur")
    fun lire_ticket(): List<Ticket>

}