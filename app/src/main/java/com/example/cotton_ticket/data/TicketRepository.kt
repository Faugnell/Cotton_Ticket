package com.example.cotton_ticket.data

import com.example.cotton_ticket.models.Ticket
import com.example.cotton_ticket.network.ApiService

/**
 * Repository qui gère les tickets depuis l'Api
 */
interface TicketRepository {
    /**
     * function de récupération des tickets
     */
    fun lire_ticket(): List<Ticket>
}

/**
 * Network Implementation de repository qui gère les tickets depuis l'Api
 */
class NetworkTicketRepository(private val apiService: ApiService) : TicketRepository {
    override fun lire_ticket(): List<Ticket> = apiService.lire_ticket()
}