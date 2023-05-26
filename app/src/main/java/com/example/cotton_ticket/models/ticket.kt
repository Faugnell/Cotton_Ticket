package com.example.cotton_ticket.models

data class Ticket(
    val idUtilisateur: Int? = null,
    val idTicket: Int? = null,
    val dateOuverture: String? = null,
    val resolution: String? = null,
    val clos: Int? = null,
    val dateClos: String? = null
)