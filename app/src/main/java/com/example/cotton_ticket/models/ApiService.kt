package com.example.cotton_ticket.models

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("ticket")
    suspend fun lire_ticket(): Response<MutableList<Ticket>>
}