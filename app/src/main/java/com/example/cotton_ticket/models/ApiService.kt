package com.example.cotton_ticket.models

import retrofit2.http.*

interface ApiService {
    @GET("ticket/lire.php")
    fun lire_ticket(id_utilisateur : Int): List<Ticket>

}